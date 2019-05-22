package server.commands;

import client.MapleCharacter;
import client.MapleClient;
import client.MapleQuestStatus;
import client.MapleStat;
import client.Skill;
import client.SkillEntry;
import client.SkillFactory;
import client.inventory.Item;
import client.inventory.MapleInventory;
import client.inventory.MapleInventoryType;
import constants.GameConstants;
import constants.ServerConstants;
import constants.ServerConfig;
import constants.ServerConstants.PlayerGMRank;
import handling.channel.ChannelServer;
import static java.lang.System.getProperty;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import scripting.EventInstanceManager;
import scripting.EventManager;
import scripting.NPCScriptManager;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.commands.CommandExecute.TradeExecute;
//import server.life.ChangeableStats;
import server.life.Element;
import server.life.ElementalEffectiveness;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.MapleMonsterStats;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.maps.SavedLocationType;
import server.quest.MapleQuest;
import tools.FileoutputUtil;
import tools.StringUtil;
import tools.packet.CField;
import tools.packet.CField.NPCPacket;
import tools.packet.CWvsContext;
//Custom 
import static handling.channel.handler.InterServerHandler.AutoItem;
import static handling.channel.handler.InterServerHandler.AutoMeso;
import static handling.channel.handler.InterServerHandler.PickAll;
/**
 *
 * @author Emilyx3
 */
public class PlayerCommand {

    public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.NORMAL;
    }
    
     public static class Dispose extends CommandExecute {

     @Override
     public int execute(MapleClient c, String[] splitted) {
     c.removeClickedNPC();
     NPCScriptManager.getInstance().dispose(c);
     c.getSession().write(CWvsContext.enableActions());
     return 1;
     }
     }
     
     public static class Song extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().getMap().broadcastMessage(CField.musicChange(splitted[1]));
            return 1;
        }
    }
     
     public static class PickMesos extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            AutoMeso = 1;
            c.getPlayer().dropMessage(5, "Auto meso loot has been enabled.");
            return 1;
        }
    }
     /*
    public static class PickItems extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            AutoItem = 1;
            c.getPlayer().dropMessage(5, "Auto item loot has been enabled.");
            return 1;
        }
    }
    */
    public static class PickAll extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            //AutoMeso = 0;
            //AutoItem = 0;
            PickAll = 1;
            c.getPlayer().getStat().pickupRange = Double.POSITIVE_INFINITY;
            c.getPlayer().dropMessage(5, "Auto item/meso loot has been enabled.");
            return 1;
        }
    }
    
    public static class PickOffMesos extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            AutoMeso = 0;
            c.getPlayer().dropMessage(5, "Auto meso loot has been disabled.");
            return 1;
        }
    }
    /*
    public static class PickOffItems extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            AutoItem = 0;
            c.getPlayer().dropMessage(5, "Auto item loot has been disabled.");
            return 1;
        }
    }*/
    
     public static class PickOffAll extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            //AutoMeso = 0;
            //AutoItem = 0;
            PickAll = 0;
            c.getPlayer().getStat().pickupRange = 0;
            c.getPlayer().dropMessage(5, "Auto item/meso loot has been disabled.");
            return 1;
        }
    }
     
          public static class PinkZak extends CommandExecute {

     @Override
     public int execute(MapleClient c, String[] splitted) {
                final EventManager eem = c.getChannelServer().getEventSM().getEventManager("PinkZakumEntrance");
                final EventInstanceManager eim = eem.getInstance(("PinkZakumEntrance"));
                    if  (eem.getProperty("entryPossible").equals("true")) {    
                       NPCScriptManager.getInstance().start(c, 9201160, null);
                    }
                                        if  (eem.getProperty("entryPossible").equals("false")) {    
                        c.getPlayer().dropMessage(5, "Entry to the [Pink Zakum] Event is currently closed please wait patiently for the next entrance!");
                    }
     return 1;
     }
     }
          
          public static class ClearInv extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            MapleCharacter player = c.getPlayer();
            if (splitted.length < 2 || player.hasBlockedInventory()) {
                c.getPlayer().dropMessage(5, "!clearinv <eq / use / setup / etc / cash / all >");
                return 0;
            } else {
                MapleInventoryType type;
                if (splitted[1].equalsIgnoreCase("eq")) {
                    type = MapleInventoryType.EQUIP;
                } else if (splitted[1].equalsIgnoreCase("use")) {
                    type = MapleInventoryType.USE;
                } else if (splitted[1].equalsIgnoreCase("setup")) {
                    type = MapleInventoryType.SETUP;
                } else if (splitted[1].equalsIgnoreCase("etc")) {
                    type = MapleInventoryType.ETC;
                } else if (splitted[1].equalsIgnoreCase("cash")) {
                    type = MapleInventoryType.CASH;
                } else if (splitted[1].equalsIgnoreCase("all")) {
                    type = null;
                } else {
                    c.getPlayer().dropMessage(5, "Invalid. @clearslot <eq / use / setup / etc / cash / all >");
                    return 0;
                }
                if (type == null) { //All, a bit hacky, but it's okay 
                    MapleInventoryType[] invs = {MapleInventoryType.EQUIP, MapleInventoryType.USE, MapleInventoryType.SETUP, MapleInventoryType.ETC, MapleInventoryType.CASH};
                    for (MapleInventoryType t : invs) {
                        type = t;
                        MapleInventory inv = c.getPlayer().getInventory(type);
                        byte start = -1;
                        for (byte i = 0; i < inv.getSlotLimit(); i++) {
                            if (inv.getItem(i) != null) {
                                start = i;
                                break;
                            }
                        }
                        if (start == -1) {
                            c.getPlayer().dropMessage(5, "There are no items in that inventory.");
                            return 0;
                        }
                        int end = 0;
                        for (byte i = start; i < inv.getSlotLimit(); i++) {
                            if (inv.getItem(i) != null) {
                                MapleInventoryManipulator.removeFromSlot(c, type, i, inv.getItem(i).getQuantity(), true);
                            } else {
                                end = i;
                                break;//Break at first empty space. 
                            }
                        }
                        c.getPlayer().dropMessage(5, "Cleared slots " + start + " to " + end + ".");
                    }
                } else {
                    MapleInventory inv = c.getPlayer().getInventory(type);
                    byte start = -1;
                    for (byte i = 0; i < inv.getSlotLimit(); i++) {
                        if (inv.getItem(i) != null) {
                            start = i;
                            break;
                        }
                    }
                    if (start == -1) {
                        c.getPlayer().dropMessage(5, "There are no items in that inventory.");
                        return 0;
                    }
                    byte end = 0;
                    for (byte i = start; i < inv.getSlotLimit(); i++) {
                        if (inv.getItem(i) != null) {
                            MapleInventoryManipulator.removeFromSlot(c, type, i, inv.getItem(i).getQuantity(), true);
                        } else {
                            end = i;
                            break;//Break at first empty space. 
                        }
                    }
                    c.getPlayer().dropMessage(5, "Cleared slots " + start + " to " + end + ".");
                }
                return 1;
            }
        }
    }

     public static class ExpFix extends CommandExecute {

     @Override
     public int execute(MapleClient c, String[] splitted) {
     c.getPlayer().setExp(c.getPlayer().getExp() - GameConstants.getExpNeededForLevel(c.getPlayer().getLevel()) >= 0 ? GameConstants.getExpNeededForLevel(c.getPlayer().getLevel()) : 0);
     return 1;
     }
     }
     
     public static class ResetExp extends CommandExecute {

     @Override
     public int execute(MapleClient c, String[] splitted) {
     c.getPlayer().setExp(0);
     return 1;
     }
     }
     
    public static class STR extends DistributeStatCommands {

        public STR() {
            stat = MapleStat.STR;
        }
    }

    public static class DEX extends DistributeStatCommands {

        public DEX() {
            stat = MapleStat.DEX;
        }
    }

    public static class INT extends DistributeStatCommands {

        public INT() {
            stat = MapleStat.INT;
        }
    }

    public static class LUK extends DistributeStatCommands {

        public LUK() {
            stat = MapleStat.LUK;
        }
    }

    public static class HP extends DistributeStatCommands {

        public HP() {
            stat = MapleStat.MAXHP;
        }
    }

    public static class MP extends DistributeStatCommands {

        public MP() {
            stat = MapleStat.MAXMP;
        }
    }
    
        public static class Hair extends DistributeStatCommands {

        public Hair() {
            stat = MapleStat.HAIR;
        }
    }

    public abstract static class DistributeStatCommands extends CommandExecute {

        protected MapleStat stat = null;
        private static final int statLim = 5000;
        private static final int hpMpLim = 500000;

        private void setStat(MapleCharacter player, int current, int amount) {
            switch (stat) {
                case STR:
                    player.getStat().setStr((short) (current + amount), player);
                    player.updateSingleStat(MapleStat.STR, player.getStat().getStr());
                    break;
                case DEX:
                    player.getStat().setDex((short) (current + amount), player);
                    player.updateSingleStat(MapleStat.DEX, player.getStat().getDex());
                    break;
                case INT:
                    player.getStat().setInt((short) (current + amount), player);
                    player.updateSingleStat(MapleStat.INT, player.getStat().getInt());
                    break;
                case LUK:
                    player.getStat().setLuk((short) (current + amount), player);
                    player.updateSingleStat(MapleStat.LUK, player.getStat().getLuk());
                    break;
                case MAXHP:
                    long maxhp = Math.min(500000, Math.abs(current + amount * 30));
              //      player.getStat().setMaxHp((short) (current + amount * 30), player);
                    player.getStat().setMaxHp((short) maxhp, player);
                    player.updateSingleStat(MapleStat.HP, player.getStat().getHp());
                    break;
                case MAXMP:
                    long maxmp = Math.min(500000, Math.abs(current + amount));
                    player.getStat().setMaxMp((short) maxmp, player);
                    player.updateSingleStat(MapleStat.MP, player.getStat().getMp());
                    break;     
                case HAIR:
                    int hair = amount;
                    player.setSecondHair(hair);
                    player.updateSingleStat(MapleStat.HAIR, player.getSecondHair());
                    break;
            }
        }

        private int getStat(MapleCharacter player) {
            switch (stat) {
                case STR:
                    return player.getStat().getStr();
                case DEX:
                    return player.getStat().getDex();
                case INT:
                    return player.getStat().getInt();
                case LUK:
                    return player.getStat().getLuk();
                case MAXHP:
                    return player.getStat().getMaxHp();
                case MAXMP:
                    return player.getStat().getMaxMp();
                default:
                    throw new RuntimeException(); //Will never happen.
            }
        }

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(5, "Invalid number entered.");
                return 0;
            }
            int change;
            try {
                change = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException nfe) {
                c.getPlayer().dropMessage(5, "Invalid number entered.");
                return 0;
            }
            int hpUsed = 0;
            int mpUsed = 0;
         //   if (stat == MapleStat.MAXHP) {
        //        hpUsed = change;
         //       short job = c.getPlayer().getJob();
         //       change *= GameConstants.getHpApByJob(job);
         //   }
            if (stat == MapleStat.MAXMP) {
                mpUsed = change;
                short job = c.getPlayer().getJob();
                if (GameConstants.isDemonSlayer(job) || GameConstants.isAngelicBuster(job) || GameConstants.isDemonAvenger(job)) {
                    c.getPlayer().dropMessage(5, "You cannot raise MP.");
                    return 0;
                }
                change *= GameConstants.getMpApByJob(job);
            }         

            if (change <= 0) {
                c.getPlayer().dropMessage(5, "You don't have enough AP Resets that.");
                return 0;
            }
            if (c.getPlayer().getRemainingAp() < change) {
                c.getPlayer().dropMessage(5, "You don't have enough AP for that.");
                return 0;
            }
            if (getStat(c.getPlayer()) + change > statLim && stat != MapleStat.MAXHP && stat != MapleStat.MAXMP) {
                c.getPlayer().dropMessage(5, "The stat limit is " + statLim + ".");
                return 0;
            }
            if (getStat(c.getPlayer()) + change > hpMpLim && (stat == MapleStat.MAXHP || stat == MapleStat.MAXMP)) {
                c.getPlayer().dropMessage(5, "The stat limit is " + hpMpLim + ".");
                return 0;
            }
            setStat(c.getPlayer(), getStat(c.getPlayer()), change);
            c.getPlayer().setRemainingAp((short) (c.getPlayer().getRemainingAp() - change));
            c.getPlayer().setHpApUsed((short) (c.getPlayer().getHpApUsed() + hpUsed));
            c.getPlayer().setHpApUsed((short) (c.getPlayer().getHpApUsed() + mpUsed));
            c.getPlayer().updateSingleStat(MapleStat.AVAILABLEAP, c.getPlayer().getRemainingAp());
                       if (stat == MapleStat.MAXHP) {
                           c.getPlayer().dropMessage(5, StringUtil.makeEnumHumanReadable(stat.name()) + " has been raised by " + change * 30 + ".");
                           c.getPlayer().fakeRelog();
           } else
            c.getPlayer().dropMessage(5, StringUtil.makeEnumHumanReadable(stat.name()) + " has been raised by " + change + ".");
            return 1;
        }
    }
    
    public static class Stats extends CommandExecute {
        @Override
        public int execute(MapleClient c, String[] splitted){
            c.getPlayer().dropMessage(6, "Extra stats for character ID " + c.getPlayer().getName() + ":");
            c.getPlayer().dropMessage(6, "Base HP: " + c.getPlayer().getStat().getMaxHp());
            c.getPlayer().dropMessage(6, "Base MP: " + c.getPlayer().getStat().getMaxMp());
            c.getPlayer().dropMessage(6, "Exp: " + c.getPlayer().getStat().getExpBuff()+ "%/ " + "Meso drop: " + c.getPlayer().getStat().getMesoBuff() + "%/ " + "Drop Rate: " + c.getPlayer().getStat().getDropBuff() + "%");
            if (c.getPlayer().getStat().QuestExpBuff > 0) {
            c.getPlayer().dropMessage(6, "Quest Exp Rate: +" + (c.getPlayer().getStat().getQuestExpBuff() + 100) + "%");    
            }
            c.getPlayer().dropMessage(6, "Physical Attack Total: " + c.getPlayer().getStat().getTotalWatk());
            c.getPlayer().dropMessage(6, "Magical Attack Total: " + c.getPlayer().getStat().getTotalMagic());
            return 1;
        }
    }

    public static class Mob extends CommandExecute {

        public int execute(MapleClient c, String[] splitted) {
            MapleMonster mob = null;
            for (MapleMapObject monstermo : c.getPlayer().getMap().getMapObjectsInRange(c.getPlayer().getPosition(), 100000, Arrays.asList(MapleMapObjectType.MONSTER))) {
                mob = (MapleMonster) monstermo;
                if (mob.isAlive()) {
                    final StringBuilder sb = new StringBuilder();
                    final MapleMonsterStats oStats = mob.getStats();
                    sb.append(oStats.getName());
                    if(c.getPlayer().isGM()){
                        sb.append(" ID ");
                        sb.append(oStats.getId());
                    }
                    sb.append(" | Level ");
                    sb.append(oStats.getLevel());
                    sb.append(" with ");
                    sb.append(mob.getHp());
                    sb.append("/");
                    sb.append(mob.getMobMaxHp());
                    sb.append("hp");
                    sb.append(" || Targetting : ");
                    final MapleCharacter chr = mob.getController();
                    sb.append(chr != null ? chr.getName() : "none");
                    sb.append(", gives ");
                    int exp = oStats.getExp();
                    sb.append(exp); //server EXP rate
                    sb.append(" Base EXP | ");
                    sb.append(" Pushed with ");
                    sb.append(oStats.getPushed()); //server EXP rate
                    sb.append(" damage | ");
                    for(Element e : Element.values()){
                        if(mob.getEffectiveness(e) != ElementalEffectiveness.NORMAL){
                            sb.append(mob.getEffectiveness(e).toString());
                            sb.append(" to ");
                            sb.append(e.toString());
                            sb.append(" | ");
                        }
                    }
                    c.getPlayer().dropMessage(6, sb.toString());
                    break;
                }
            }
            if (mob == null) {
                c.getPlayer().dropMessage(6, "No monster was found.");
            }
            return 1;
        }
    }

     public static class Save extends CommandExecute {

     @Override
     
     public int execute(MapleClient c, String[] splitted) {
     c.getPlayer().setExp(c.getPlayer().getExp() - GameConstants.getExpNeededForLevel(c.getPlayer().getLevel()) >= 0 ? GameConstants.getExpNeededForLevel(c.getPlayer().getLevel()) : 0);
     c.getPlayer().saveToDB(false, false);
     c.getPlayer().dropMessage(5,"Your Character has been saved.");
     return 1;
     }
     }

     public static class sgm extends CommandExecute {

     @Override
     
     public int execute(MapleClient c, String[] splitted) {
     if (c.getPlayer().isSuperGM()) {
     c.getPlayer().setGM((byte) 0);  
     return 1;
     }
     c.getPlayer().setGM((byte) 6);
     return 1;
     }
     }
    public static class Event extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (c.getPlayer().isInBlockedMap() || c.getPlayer().hasBlockedInventory()) {
                c.getPlayer().dropMessage(5, "You may not use this command here.");
                return 0;
            }
            NPCScriptManager.getInstance().start(c, 9000000, null);
            return 1;
        }
    }
    
    public static class whatdrops extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().start(c, 9071007, null);
            return 1;
        }
    }
    
    public static class CheckDrop extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
        c.getSession().write(CWvsContext.gmBoard(c.getNextClientIncrenement(), "http://www.hidden-street.net/"));
        return 1;
        }
    }
    
    public static class ranking extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
        c.getSession().write(CWvsContext.gmBoard(c.getNextClientIncrenement(), ServerConfig.rankingURL));
        return 1;
        }
    }

    /*
     public static class CheckDrop extends OpenNPCCommand {

     public CheckDrop() {
     npc = 4;
     }
     }

     public static class Pokedex extends OpenNPCCommand {

     public Pokedex() {
     npc = 5;
     }
     }

     public static class ClearSlot extends CommandExecute {

     private static MapleInventoryType[] invs = {MapleInventoryType.EQUIP, MapleInventoryType.USE, MapleInventoryType.SETUP, MapleInventoryType.ETC, MapleInventoryType.CASH,};

     @Override
     public int execute(MapleClient c, String[] splitted) {
     MapleCharacter player = c.getPlayer();
     if (splitted.length < 2 || player.hasBlockedInventory()) {
     c.getPlayer().dropMessage(5, "@clearslot <eq / use / setup / etc / cash / all >");
     return 0;
     } else {
     MapleInventoryType type;
     if (splitted[1].equalsIgnoreCase("eq")) {
     type = MapleInventoryType.EQUIP;
     } else if (splitted[1].equalsIgnoreCase("use")) {
     type = MapleInventoryType.USE;
     } else if (splitted[1].equalsIgnoreCase("setup")) {
     type = MapleInventoryType.SETUP;
     } else if (splitted[1].equalsIgnoreCase("etc")) {
     type = MapleInventoryType.ETC;
     } else if (splitted[1].equalsIgnoreCase("cash")) {
     type = MapleInventoryType.CASH;
     } else if (splitted[1].equalsIgnoreCase("all")) {
     type = null;
     } else {
     c.getPlayer().dropMessage(5, "Invalid. @clearslot <eq / use / setup / etc / cash / all >");
     return 0;
     }
     if (type == null) { //All, a bit hacky, but it's okay 
     for (MapleInventoryType t : invs) {
     type = t;
     MapleInventory inv = c.getPlayer().getInventory(type);
     byte start = -1;
     for (byte i = 0; i < inv.getSlotLimit(); i++) {
     if (inv.getItem(i) != null) {
     start = i;
     break;
     }
     }
     if (start == -1) {
     c.getPlayer().dropMessage(5, "There are no items in that inventory.");
     return 0;
     }
     int end = 0;
     for (byte i = start; i < inv.getSlotLimit(); i++) {
     if (inv.getItem(i) != null) {
     MapleInventoryManipulator.removeFromSlot(c, type, i, inv.getItem(i).getQuantity(), true);
     } else {
     end = i;
     break;//Break at first empty space. 
     }
     }
     c.getPlayer().dropMessage(5, "Cleared slots " + start + " to " + end + ".");
     }
     } else {
     MapleInventory inv = c.getPlayer().getInventory(type);
     byte start = -1;
     for (byte i = 0; i < inv.getSlotLimit(); i++) {
     if (inv.getItem(i) != null) {
     start = i;
     break;
     }
     }
     if (start == -1) {
     c.getPlayer().dropMessage(5, "There are no items in that inventory.");
     return 0;
     }
     byte end = 0;
     for (byte i = start; i < inv.getSlotLimit(); i++) {
     if (inv.getItem(i) != null) {
     MapleInventoryManipulator.removeFromSlot(c, type, i, inv.getItem(i).getQuantity(), true);
     } else {
     end = i;
     break;//Break at first empty space. 
     }
     }
     c.getPlayer().dropMessage(5, "Cleared slots " + start + " to " + end + ".");
     }
     return 1;
     }
     }
     }
     * */
    public static class home extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            for (int i : GameConstants.blockedMaps) {
                if (c.getPlayer().getMapId() == i) {
                    c.getPlayer().dropMessage(5, "You may not use this command here.");
                    return 0;
                }
            }
            if (c.getPlayer().getLevel() < 50 && c.getPlayer().getJob() != 200) {
                c.getPlayer().dropMessage(5, "You must be over level 50 to use this command.");
                return 0;
            }
            if (c.getPlayer().hasBlockedInventory() || c.getPlayer().getMap().getSquadByMap() != null || c.getPlayer().getEventInstance() != null || c.getPlayer().getMap().getEMByMap() != null || c.getPlayer().getMapId() >= 990000000/* || FieldLimitType.VipRock.check(c.getPlayer().getMap().getFieldLimit())*/) {
                c.getPlayer().dropMessage(5, "You may not use this command here.");
                return 0;
            }

            if ((c.getPlayer().getMapId() >= 680000210 && c.getPlayer().getMapId() <= 680000502) || (c.getPlayer().getMapId() / 1000 == 980000 && c.getPlayer().getMapId() != 980000000) || (c.getPlayer().getMapId() / 100 == 1030008) || (c.getPlayer().getMapId() / 100 == 922010) || (c.getPlayer().getMapId() / 10 == 13003000)) {
                c.getPlayer().dropMessage(5, "You may not use this command here.");
                return 0;
            }

            c.getPlayer().saveLocation(SavedLocationType.FREE_MARKET, c.getPlayer().getMap().getReturnMap().getId());
            MapleMap map = c.getChannelServer().getMapFactory().getMap(ServerConfig.HOME_MAP_ID);

            c.getPlayer()
                    .changeMap(map, map.getPortal(0));

            return 1;
        }
    }
    public static class modes extends CommandExecute {
    
    @Override
        public int execute(MapleClient c, String[] splitted) {
            if (GameConstants.isBeastTamer(c.getPlayer().getJob())) {
                c.getPlayer().changeSingleSkillLevel(SkillFactory.getSkill(110001510), (byte) 1, (byte) 1);
                c.getPlayer().changeSingleSkillLevel(SkillFactory.getSkill(110001501), (byte) 1, (byte) 1);
                c.getPlayer().changeSingleSkillLevel(SkillFactory.getSkill(110001502), (byte) 1, (byte) 1);
                c.getPlayer().changeSingleSkillLevel(SkillFactory.getSkill(110001503), (byte) 1, (byte) 1);
                c.getPlayer().changeSingleSkillLevel(SkillFactory.getSkill(110001504), (byte) 1, (byte) 1);
                HashMap<Skill, SkillEntry> sa = new HashMap<>();
                for (Skill skil : SkillFactory.getAllSkills()) {
                    if (GameConstants.isApplicableSkill(skil.getId()) && skil.canBeLearnedBy(c.getPlayer().getJob()) && !skil.isInvisible()) { //no db/additionals/resistance skills
                        sa.put(skil, new SkillEntry((byte) skil.getMaxLevel(), (byte) skil.getMaxLevel(), SkillFactory.getDefaultSExpiry(skil)));
                    }
                }
                c.getPlayer().changeSkillsLevel(sa);
                c.getPlayer().dropMessage(6, "All modes are added");
                return 1;
            } else {
                c.getPlayer().dropMessage(6, "You are not beast tamer!");
                return 0;
            }
        }
    }
    
    public static class fm extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            for (int i : GameConstants.blockedMaps) {
                if (c.getPlayer().getMapId() == i) {
                    c.getPlayer().dropMessage(5, "You may not use this command here.");
                    return 0;
                }
            }
            if (c.getPlayer().getLevel() < 10 && c.getPlayer().getJob() != 200) {
                c.getPlayer().dropMessage(5, "You must be over level 10 to use this command.");
                return 0;
            }
            if (c.getPlayer().hasBlockedInventory() || c.getPlayer().getMap().getSquadByMap() != null || c.getPlayer().getEventInstance() != null || c.getPlayer().getMap().getEMByMap() != null || c.getPlayer().getMapId() >= 990000000/* || FieldLimitType.VipRock.check(c.getPlayer().getMap().getFieldLimit())*/) {
                c.getPlayer().dropMessage(5, "You may not use this command here.");
                return 0;
            }

            c.getPlayer().saveLocation(SavedLocationType.FREE_MARKET, c.getPlayer().getMap().getReturnMap().getId());
            MapleMap map = c.getChannelServer().getMapFactory().getMap(970000000);

            c.getPlayer().changeMap(map, map.getPortal(0));

            return 1;
        }
    }
    /*
     public static class EA extends CommandExecute {

     public int execute(MapleClient c, String[] splitted) {
     c.removeClickedNPC();
     NPCScriptManager.getInstance().dispose(c);
     c.getSession().write(CWvsContext.enableActions());
     return 1;
     }
     }

     public static class TSmega extends CommandExecute {

     public int execute(MapleClient c, String[] splitted) {
     c.getPlayer().setSmega();
     return 1;
     }
     }

     public static class Ranking extends CommandExecute {

     public int execute(MapleClient c, String[] splitted) {
     if (splitted.length < 4) { //job start end
     c.getPlayer().dropMessage(5, "Use @ranking [job] [start number] [end number] where start and end are ranks of the players");
     final StringBuilder builder = new StringBuilder("JOBS: ");
     for (String b : RankingWorker.getJobCommands().keySet()) {
     builder.append(b);
     builder.append(" ");
     }
     c.getPlayer().dropMessage(5, builder.toString());
     } else {
     int start = 1, end = 20;
     try {
     start = Integer.parseInt(splitted[2]);
     end = Integer.parseInt(splitted[3]);
     } catch (NumberFormatException e) {
     c.getPlayer().dropMessage(5, "You didn't specify start and end number correctly, the default values of 1 and 20 will be used.");
     }
     if (end < start || end - start > 20) {
     c.getPlayer().dropMessage(5, "End number must be greater, and end number must be within a range of 20 from the start number.");
     } else {
     final Integer job = RankingWorker.getJobCommand(splitted[1]);
     if (job == null) {
     c.getPlayer().dropMessage(5, "Please use @ranking to check the job names.");
     } else {
     final List<RankingInformation> ranks = RankingWorker.getRankingInfo(job.intValue());
     if (ranks == null || ranks.size() <= 0) {
     c.getPlayer().dropMessage(5, "Please try again later.");
     } else {
     int num = 0;
     for (RankingInformation rank : ranks) {
     if (rank.rank >= start && rank.rank <= end) {
     if (num == 0) {
     c.getPlayer().dropMessage(6, "Rankings for " + splitted[1] + " - from " + start + " to " + end);
     c.getPlayer().dropMessage(6, "--------------------------------------");
     }
     c.getPlayer().dropMessage(6, rank.toString());
     num++;
     }
     }
     if (num == 0) {
     c.getPlayer().dropMessage(5, "No ranking was returned.");
     }
     }
     }
     }
     }
     return 1;
     }
     }*/

    public static class Check extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(6, "You currently have " + c.getPlayer().getCSPoints(1) + " Cash, " + c.getPlayer().getEPoints() + " Event Points, " + c.getPlayer().getDPoints() + " Donation Points, " + c.getPlayer().getVPoints() + " voting points and " + c.getPlayer().getIntNoRecord(GameConstants.BOSS_PQ) + " Boss Party Quest points.");
            c.getPlayer().dropMessage(6, "The time is currently " + FileoutputUtil.CurrentReadable_TimeGMT() + " GMT. | EXP " + (Math.round(c.getPlayer().getEXPMod()) * 100) * Math.round(c.getPlayer().getStat().expBuff / 100.0) + "%, Drop " + (Math.round(c.getPlayer().getDropMod()) * 100) * Math.round(c.getPlayer().getStat().dropBuff / 100.0) + "%, Meso " + Math.round(c.getPlayer().getStat().mesoBuff / 100.0) * 100 + "%");
            c.getPlayer().dropMessage(6, "EXP: " + c.getPlayer().getExp() + " / " + c.getPlayer().getNeededExp());
            c.removeClickedNPC();
            NPCScriptManager.getInstance().dispose(c);
            c.getSession().write(CWvsContext.enableActions());
            return 1;
        }
    }

    public static class Help extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n@str, @dex, @int, @luk, @hp, @mp <amount to add or subtract>");
            sb.append("\r\n@mob < Information on the closest monster >");
            sb.append("\r\n@check < Displays various information; also use if you are stuck or unable to talk to NPC >");
            sb.append("\r\n@whatdrops < Open the drop item finder NPC");
            sb.append("\r\n@callgm < Send a message to all online GameMasters.");
            sb.append("\r\n@home < Warp to Acernis base >");
            sb.append("\r\n@fm < Warp to the FreeMarket instantly. >");
            sb.append("\r\n@job < Job advancements! >");
            sb.append("\r\n@save < Fixes your experience and saves your character >");
            sb.append("\r\n@PickAll < Enables auto loot from mesos/items when a monster is defeated, you require to have a pet on your side>");
            sb.append("\r\n@PickAllOff < Disables auto loot from mesos/items when a monster is defeated, you require to have a pet on your side>");
            //sb.append("\r\n@joinevent < Join ongoing event >");
             //sb.append("\r\n@crescent < Universal Town Warp / Event NPC>");
             //sb.append("\r\n@dcash < Universal Cash Item Dropper >");
             //sb.append("\r\n@tsmega < Toggle super megaphone on/off >");
             //sb.append("\r\n@ea < If you are unable to attack or talk to NPC >");
             //sb.append("\r\n@clearslot < Cleanup that trash in your inventory >");
             sb.append("\r\n@ranking < Check the rankings on our site >");
             sb.append("\r\n@checkdrop < Check drops on Hidden-Street >");
             //sb.append("\r\n@style < Styler >");
             //sb.append("\r\n@advance < Job Advancer >");
             //sb.append("\r\n@bosswarp < Boss Warper >");
             //sb.append("\r\n@fly < Makes you fly if you're in the Free Market >");
            if (c.canClickNPC()) {
                NPCPacket.getNPCTalk(9010000, (byte) 0, sb.toString(), "00 00", (byte) 0);
            }
            for (String command : sb.toString().split("\r\n")) {
                c.getPlayer().dropMessage(5, command);
            }
            return 1;
        }
    }
    
    public static class job extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (c.getPlayer().isInBlockedMap() || c.getPlayer().hasBlockedInventory()) {
                c.getPlayer().dropMessage(5, "You may not use this command here.");
                return 0;
            }else if (c.getPlayer().getLevel() < 10) {
                c.getPlayer().dropMessage(5, "You need to be at least lvl 10 in order to advance.");
                return 0;
            } else {
            NPCScriptManager.getInstance().start(c, 2300001, null);
            return 1;
            }
        }
    }
    public static class JoinEvent extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getChannelServer().warpToEvent(c.getPlayer());
            return 1;
        }
    }

    public static class SpawnBomb extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (c.getPlayer().getMapId() != 109010100) {
                c.getPlayer().dropMessage(5, "You may only spawn bomb in the event map.");
                return 0;
            }
            if (!c.getChannelServer().bombermanActive()) {
                c.getPlayer().dropMessage(5, "You may not spawn bombs yet.");
                return 0;
            }
            c.getPlayer().getMap().spawnMonsterOnGroudBelow(MapleLifeFactory.getMonster(9300166), c.getPlayer().getPosition());
            return 1;
        }
    }

    public static class CashDrop extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().start(c, 9010000, "CashDrop");
            return 1;
        }
    }

    public static class CallGM extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.broadcastGMMessage(tools.packet.CField.multiChat("[GM Help] " + c.getPlayer().getName(), StringUtil.joinStringFrom(splitted, 1), 6));
            }
            c.getPlayer().dropMessage(5, "Your message had been sent successfully.");
            return 1;
        }
    }
}
