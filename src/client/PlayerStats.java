package client;

import client.MapleTrait.MapleTraitType;
import client.inventory.Equip;
import client.inventory.Item;
import client.inventory.MapleInventoryType;
import client.inventory.MapleWeaponType;
import constants.EventConstants;
import constants.GameConstants;
import handling.channel.handler.InterServerHandler;
import handling.world.World;
import handling.world.guild.MapleGuild;
import handling.world.guild.MapleGuildSkill;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimerTask;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MapleStatEffect;
import server.StructItemOption;
import server.StructSetItem;
import server.StructSetItem.SetItem;
import server.life.Element;
import tools.Pair;
import tools.Triple;
import tools.data.MaplePacketLittleEndianWriter;
import tools.packet.CField.EffectPacket;
import tools.packet.CWvsContext.InventoryPacket;
import tools.packet.JobPacket;

public class PlayerStats implements Serializable {
    public static MonsterBook monsterbook;
    private static final long serialVersionUID = -679541993413738569L;
    private List<Triple<Integer, String, Integer>> psdSkills = new ArrayList<>();
    private final Map<Integer, Integer> setHandling = new HashMap<>(), skillsIncrement = new HashMap<>(), damageIncrease = new HashMap<>();
    private final EnumMap<Element, Integer> elemBoosts = new EnumMap<>(Element.class);
    private final List<Equip> durabilityHandling = new ArrayList<>(), equipLevelHandling = new ArrayList<>();
    public transient float shouldHealHP, shouldHealMP, shouldHealHPOverTime, shouldHealMPOverTime, shouldHealHPFamiliar, shouldHealMPFamiliar;
    private transient short passive_sharpeye_min_percent, passive_sharpeye_percent, crit_rate;
    private transient byte passive_mastery;
    public transient int evaR, localstr, localdex, localluk, localint_, localmaxhp, localmaxmp, magic, watk, hands, accuracy, targetPlus;
    private transient float localmaxbasedamage, localmaxbasepvpdamage, localmaxbasepvpdamageL;
    public transient boolean equippedWelcomeBackRing, hasClone, Berserk;
    public transient double QuestExpBuff, expBuff, expBuff2, dropBuff, mesoBuff, cashBuff, mesoGuard, mesoGuardMeso, expMod, dropMod, pickupRange, dam_r, bossdam_r;
    public transient int HPRestore,ReAttempt,EssenceRecreationProp,HyperCoolDownReduce, HyperCoolDownReduce2,RechargeRate,MonsterKills, recoverHPNebulite, recoverMPNebulite, AutoSteal, AutoStealprop,damage, reduceForceRSpecial,reduceForceR, DemonMP, DemonMPEquip, BurnLevel, BurnPotential, BurnPotentialprop,PoisonLevel, SealLevel, BlindLevel, FreezeLevel, StunLevel, SlowLevel, SlowPotential, SlowPotentialprop, SealPotential, SealPotentialprop, FreezePotential, FreezePotentialprop, BlindPotential, BlindPotentialprop, StunPotential, StunPotentialprop, PoisonPotential, PoisonPotentialprop, nocoolProp, recoverHP, recoverMP, recoverHPsoc, recoverMPsoc, mpconReduce, mpconPercent, incMesoProp, reduceCooltime, coolTimeR, suddenDeathR, expLossReduceR, DAMreflect, DAMreflect_rate, ignoreDAMr, ignoreDAMrSkill, ignoreDAMr_rate, ignoreDAM, ignoreDAM_rate,
            hpRecover, hpRecoverOnKill, mpRecoverOnKill ,hpRecoverSkillProp, hpRecoverSkillPercent, hpRecoverProp, hpRecoverPropOnKill, mpRecoverPropOnKill, hpRecoverPercent, mpRecover, mpRecoverProp, RecoveryUP, BuffUP, RecoveryUP_Skill, BuffUP_Skill,
            incAllskill, combatOrders, ignoreTargetDEF, defRange, BuffUP_Summon, dodgeChance, speed, jump, harvestingTool,
            equipmentBonusExp, cashMod, levelBonus, ASR, TER, pickRate, decreaseDebuff, equippedFairy, equippedSummon, equippedSummon2,
            pvpDamage, hpRecoverTimeFamiliar, mpRecoverTimeFamiliar, hpRecoverTime, mpRecoverTime, dot, dotTime, questBonus, pvpRank, pvpExp, wdef, mdef, trueMastery, damX;
    public transient int def, element_ice, element_fire, element_light, element_psn;
    public int hp, maxhp, mp, maxmp, str, dex, luk, int_;
    private transient int percent_hp, percent_mp, percent_str, percent_dex, percent_int, percent_luk, percent_acc, percent_atk, percent_matk, percent_wdef, percent_mdef,
            add_hp, add_mp, add_str, add_dex, add_int, add_luk, add_acc, add_atk, add_matk, add_wdef, add_mdef;

    public void recalcLocalStats(MapleCharacter chra) {
        recalcLocalStats(false, chra);
    }

    private void resetLocalStats(final int job) {
        HPRestore = 0;
        ReAttempt = 0;
        EssenceRecreationProp = 0;
        HyperCoolDownReduce = 0;
        HyperCoolDownReduce2 = 0;
        RechargeRate = 0;
        MonsterKills = 0;
        mpRecoverOnKill = 0;
        hpRecoverOnKill = 0;
        hpRecoverPropOnKill = 0;
        mpRecoverPropOnKill = 0;
        AutoSteal = 0;
        AutoStealprop = 0;
        hpRecoverSkillProp = 0;
        hpRecoverSkillPercent = 0;
        accuracy = 0;
        wdef = 0;
        mdef = 0;
        damX = 0;
        localdex = getDex();
        localint_ = getInt();
        localstr = getStr();
        localluk = getLuk();
        speed = 100;
        jump = 100;
        //tittleHPMP = 0;
        pickupRange = 0.0;
        decreaseDebuff = 0;
        ASR = 0;
        TER = 0;
        nocoolProp = 0;
        dot = 0;
        questBonus = 1;
        dotTime = 0;
        trueMastery = 0;
        percent_wdef = 0;
        percent_mdef = 0;
        percent_hp = 0;
        percent_mp = 0;
        targetPlus = 0;
        percent_str = 0;
        percent_dex = 0;
        percent_int = 0;
        percent_luk = 0;
        percent_acc = 0;
        percent_atk = 0;
        percent_matk = 0;
        add_wdef = 0;
        add_mdef = 0;
        add_hp = 0;
        add_mp = 0;
        add_str = 0;
        add_dex = 0;
        add_int = 0;
        add_luk = 0;
        add_acc = 0;
        add_atk = 0;
        add_matk = 0;
        crit_rate = 5;
        passive_sharpeye_min_percent = 20;
        passive_sharpeye_percent = 50;
        magic = 0;
        watk = 0;
        dodgeChance = 0;
        pvpDamage = 0;
        mesoGuard = 50.0;
        mesoGuardMeso = 0.0;
        dam_r = 100.0;
        bossdam_r = 100.0;
        expBuff = 100.0 + expBuff2;
        cashBuff = 100.0;
        dropBuff = 100;
        mesoBuff = 100.0;
        recoverHP = 0;
        recoverMP = 0;
        recoverHPsoc = 0;
        recoverMPsoc = 0;
        mpconReduce = 0;
        mpconPercent = 100;
        incMesoProp = 0;
        reduceCooltime = 0;
        coolTimeR = 0;
        suddenDeathR = 0;
        expLossReduceR = 0;
        DAMreflect = 0;
        DAMreflect_rate = 0;
        ignoreDAMr = 0;
        ignoreDAMrSkill = 0;
        ignoreDAMr_rate = 0;
        ignoreDAM = 0;
        ignoreDAM_rate = 0;
        ignoreTargetDEF = 0;
        targetPlus = 0;
        hpRecover = 0;
        hpRecoverProp = 0;
        hpRecoverPercent = 0;
        mpRecover = 0;
        mpRecoverProp = 0;
        pickRate = 0;
        equippedWelcomeBackRing = false;
        equippedFairy = 0;
        equippedSummon = 0;
        equippedSummon2 = 0;
        hasClone = false;
        Berserk = false;
        equipmentBonusExp = 0;
        RecoveryUP = 0;
        BuffUP = 0;
        RecoveryUP_Skill = 0;
        BuffUP_Skill = 0;
        BuffUP_Summon = 0;
        dropMod = 1.0;
        expMod = 1.0;
        cashMod = 1;
        levelBonus = 0;
        incAllskill = 0;
        combatOrders = 0;
        defRange = isRangedJob(job) ? 200 : 0;
        durabilityHandling.clear();
        equipLevelHandling.clear();
        skillsIncrement.clear();
        damageIncrease.clear();
        setHandling.clear();
        harvestingTool = 0;
        element_fire = 100;
        element_ice = 100;
        element_light = 100;
        element_psn = 100;
        def = 100;
        PoisonPotential = 0;
        PoisonPotentialprop = 0;
        StunPotential = 0;
        BurnPotential = 0;
        StunPotentialprop = 0;
        BlindPotential = 0;
        BlindPotentialprop = 0;
        FreezePotential = 0;
        FreezePotentialprop = 0;
        SealPotential = 0;
        SealPotentialprop = 0;
        SlowPotential = 0;
        DemonMP = 0;
        SlowPotentialprop = 0;
        BurnPotentialprop = 0;
        PoisonLevel = 0; 
        SealLevel = 0; 
        BlindLevel = 0; 
        FreezeLevel = 0;
        StunLevel = 0;
        SlowLevel  = 0;
        BurnLevel  = 0;
        reduceForceR = 0;
        reduceForceRSpecial = 0;
        recoverHPNebulite = 0;
        recoverMPNebulite = 0;
    }

    public void recalcLocalStats(boolean first_login, MapleCharacter chra) {
        if (chra.isClone()) {
            return; //clones share PlayerStats objects and do not need to be recalculated
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        int oldmaxhp = localmaxhp;
        int localmaxhp_ = getMaxHp();
        int localmaxmp_ = getMaxMp();
        resetLocalStats(chra.getJob());
        for (MapleTraitType t : MapleTraitType.values()) {
            chra.getTrait(t).clearLocalExp();
        }
        StructItemOption soc;
        final Map<Skill, SkillEntry> sData = new HashMap<>();
        final Iterator<Item> itera = chra.getInventory(MapleInventoryType.EQUIPPED).newList().iterator();
        while (itera.hasNext()) {
            final Equip equip = (Equip) itera.next();
            if (equip.getPosition() == -11) {
                if (GameConstants.isMagicWeapon(equip.getItemId())) {
                    final Map<String, Integer> eqstat = MapleItemInformationProvider.getInstance().getEquipStats(equip.getItemId());
                    if (eqstat != null) { //slow, poison, darkness, seal, freeze
                        if (eqstat.containsKey("incRMAF")) {
                            element_fire = eqstat.get("incRMAF");
                        }
                        if (eqstat.containsKey("incRMAI")) {
                            element_ice = eqstat.get("incRMAI");
                        }
                        if (eqstat.containsKey("incRMAL")) {
                            element_light = eqstat.get("incRMAL");
                        }
                        if (eqstat.containsKey("incRMAS")) {
                            element_psn = eqstat.get("incRMAS");
                        }
                        if (eqstat.containsKey("elemDefault")) {
                            def = eqstat.get("elemDefault");
                        }
                    }
                }
            }
            if ((equip.getItemId() / 10000 == 166 && equip.getAndroid() != null
                    || equip.getItemId() / 10000 == 167) && chra.getAndroid() == null) {
                final Equip android = (Equip) chra.getInventory(MapleInventoryType.EQUIPPED).getItem((short) -27);
                final Equip heart = (Equip) chra.getInventory(MapleInventoryType.EQUIPPED).getItem((short) -28);
                if (android != null && heart != null) {
                    chra.setAndroid(equip.getAndroid());
                }
            }
            if (GameConstants.isDemonSlayer(chra.getJob())) {
            if (equip.getItemId() / 1000 == 1099) {
            if (chra.getSkillLevel(31120038) == 0) {
                DemonMPEquip = equip.getMp() + 10;
            }
            if (chra.getSkillLevel(31120038) > 0) {
                DemonMPEquip = equip.getMp() + 60;
            }
            }
            if (!chra.hasEquipped(1099000) && !chra.hasEquipped(1099001) && !chra.hasEquipped(1099002) && !chra.hasEquipped(1099003) && !chra.hasEquipped(1099004) && chra.getSkillLevel(31120038) == 0) {
            DemonMPEquip = 10; 
            }
            if (!chra.hasEquipped(1099000) && !chra.hasEquipped(1099001) && !chra.hasEquipped(1099002) && !chra.hasEquipped(1099003) && !chra.hasEquipped(1099004) && chra.getSkillLevel(31120038) > 0) {
            DemonMPEquip = 60; 
            }
            DemonMP = DemonMPEquip;    
            }
            chra.getTrait(MapleTraitType.craft).addLocalExp(equip.getHands());
            accuracy += equip.getAcc();
            localmaxhp_ += equip.getHp();
            localmaxmp_ += equip.getMp();
            localdex += equip.getDex();
            localint_ += equip.getInt();
            localstr += equip.getStr();
            localluk += equip.getLuk();
            watk += equip.getWatk();
            magic += equip.getMatk();
            wdef += equip.getWdef();
            mdef += equip.getMdef();
            speed += equip.getSpeed();
            jump += equip.getJump();
            pvpDamage += equip.getPVPDamage();
            bossdam_r += equip.getBossDamage();
            ignoreTargetDEF += equip.getIgnorePDR();
            dam_r *= (Double.valueOf(equip.getTotalDamage()) + 100.0) / 100.0;
            percent_str += equip.getAllStat();
            percent_dex += equip.getAllStat();
            percent_int += equip.getAllStat();
            percent_luk += equip.getAllStat();
            switch (equip.getItemId()) {
                case 1112127:
                    equippedWelcomeBackRing = true;
                    break;
                case 1122017:
                    equippedFairy = 10;
                    break;
                case 1122158:
                    equippedFairy = 5;
                    break;
                case 1112594:
                    equippedSummon = 1085;
                    break;
                case 1112585:
                    equippedSummon = 1085;
                    break;
                case 1112586:
                    equippedSummon = 1087;
                    break;
                case 1112663:
                    equippedSummon = 1179;
                    break;
                case 1113020:
                    equippedSummon = 80001262;
                    break;
                default:
                    for (int eb_bonus : GameConstants.Equipments_Bonus) {
                        if (equip.getItemId() == eb_bonus) {
                            equipmentBonusExp += GameConstants.Equipment_Bonus_EXP(eb_bonus);
                            break;
                        }
                    }
                    break;
            }
            final Integer set = ii.getSetItemID(equip.getItemId());
            if (set != null && set > 0) {
                int value = 1;
                if (setHandling.containsKey(set)) {
                    value += (setHandling.get(set).intValue());
                }
                setHandling.put(set,(value)); //id of Set, number of items to go with the set
            }
            if (equip.getIncSkill() > 0 && ii.getEquipSkills(equip.getItemId()) != null) {
                for (final int zzz : ii.getEquipSkills(equip.getItemId())) {
                    final Skill skil = SkillFactory.getSkill(zzz);
                    if (skil != null && skil.canBeLearnedBy(chra.getJob())) { //dont go over masterlevel :D
                        int value = 1;
                        if (skillsIncrement.get(skil.getId()) != null) {
                            value += skillsIncrement.get(skil.getId());
                        }
                        skillsIncrement.put(skil.getId(), value);
                    }
                }
            }
            final Pair<Integer, Integer> ix = handleEquipAdditions(ii, chra, first_login, sData, equip.getItemId());
            if (ix != null) {
                localmaxhp_ += ix.getLeft();
                localmaxmp_ += ix.getRight();
            }
            final int reqLevel = ii.getReqLevel(equip.getItemId());
            if (equip.getState() >= 17 && reqLevel == 0 || equip.getState() >= 17 && reqLevel == 10 || equip.getState() >= 17 && reqLevel == 20 || equip.getState() >= 17 && reqLevel == 30 || equip.getState() >= 17 && reqLevel == 40 || equip.getState() >= 17 && reqLevel == 50 || equip.getState() >= 17 && reqLevel == 60 || equip.getState() >= 17 && reqLevel == 70 || equip.getState() >= 17 && reqLevel == 80 || equip.getState() >= 17 && reqLevel == 90 || equip.getState() >= 17 && reqLevel == 100 || equip.getState() >= 17 && reqLevel == 110 || equip.getState() >= 17 && reqLevel == 120 || equip.getState() >= 17 && reqLevel == 130 || equip.getState() >= 17 && reqLevel == 140 || equip.getState() >= 17 && reqLevel == 150 || equip.getState() >= 17 && reqLevel == 160 || equip.getState() >= 17 && reqLevel == 170 || equip.getState() >= 17 && reqLevel == 180 || equip.getState() >= 17 && reqLevel == 190 || equip.getState() >= 17 && reqLevel == 200) {
                final int[] potentials = {equip.getPotential1(), equip.getPotential2(), equip.getPotential3(), equip.getBonusPotential1(), equip.getBonusPotential2(), equip.getBonusPotential3()};
                for (final int i : potentials) {
                    if (i > 0) {
                        soc = ii.getPotentialInfo(i).get((int)(ii.getReqLevel(equip.getItemId()) / 10 -.1));
                        if (soc != null) {
                            localmaxhp_ += soc.get("incMHP");
                            localmaxmp_ += soc.get("incMMP");
                            handleItemOption(soc, chra, first_login, sData);
                        }
                    }
                }
            }
            else {
                final int[] potentials = {equip.getPotential1(), equip.getPotential2(), equip.getPotential3(), equip.getBonusPotential1(), equip.getBonusPotential2(), equip.getBonusPotential3()};
                for (final int i : potentials) {
                    if (i > 0) {
                        soc = ii.getPotentialInfo(i).get((ii.getReqLevel(equip.getItemId()) / 10));
                        if (soc != null) {
                            localmaxhp_ += soc.get("incMHP");
                            localmaxmp_ += soc.get("incMMP");
                            handleItemOptions(soc, chra, first_login, sData);
                        }
                    }    
            }
            }
            if (equip.getSocketState() > 15) {
                final int[] sockets = {equip.getSocket1(), equip.getSocket2(), equip.getSocket3()};
                for (final int i : sockets) {
                    if (i > 0) {
                        soc = ii.getSocketInfo(i);
                        if (soc != null) {
                        if (soc.get("RecoveryHPNebulite") > 0) {
                        recoverHPNebulite += soc.get("RecoveryHPNebulite"); // This shouldn't be here, set 4 seconds.
                        }
                        if (soc.get("RecoveryMPNebulite") > 0) {
                        recoverMPNebulite += soc.get("RecoveryMPNebulite"); // This shouldn't be here, set 4 seconds.
                        }
                        if (soc.get("AutoSteal") > 0) {
                         AutoSteal = 1;
                         AutoStealprop += soc.get("prop");
                        }
                        if (soc.get("HPonKill") > 0) {
                         hpRecoverOnKill += soc.get("HPonKill");
                         hpRecoverPropOnKill += soc.get("prop");
                        }
                        if (soc.get("MPonKill") > 0) {
                         mpRecoverOnKill += soc.get("MPonKill");
                         mpRecoverPropOnKill += soc.get("prop");
                        }
                            localmaxhp_ += soc.get("incMHP");
                            localmaxmp_ += soc.get("incMMP");
                            handleItemOption(soc, chra, first_login, sData);
                        }
                    }
                }
            }
            if (equip.getDurability() > 0) {
                durabilityHandling.add((Equip) equip);
            }
            if (GameConstants.getMaxLevel(equip.getItemId()) > 0 && (GameConstants.getStatFromWeapon(equip.getItemId()) == null ? (equip.getEquipLevel() <= GameConstants.getMaxLevel(equip.getItemId())) : (equip.getEquipLevel() < GameConstants.getMaxLevel(equip.getItemId())))) {
                equipLevelHandling.add((Equip) equip);
            }
        }
        final Iterator<Entry<Integer, Integer>> iter = setHandling.entrySet().iterator();
        while (iter.hasNext()) {
            final Entry<Integer, Integer> entry = iter.next();
            final StructSetItem set = ii.getSetItem(entry.getKey());
            if (set != null) {
                final Map<Integer, SetItem> itemz = set.getItems();
                for (Entry<Integer, SetItem> ent : itemz.entrySet()) {
                    if (ent.getKey() <= entry.getValue()) {
                        SetItem se = ent.getValue();
                        localstr += se.incSTR + se.incAllStat;
                        localdex += se.incDEX + se.incAllStat;
                        localint_ += se.incINT + se.incAllStat;
                        localluk += se.incLUK + se.incAllStat;
                        watk += se.incPAD;
                        magic += se.incMAD;
                        speed += se.incSpeed;
                        accuracy += se.incACC;
                        localmaxhp_ += se.incMHP;
                        localmaxmp_ += se.incMMP;
                        percent_hp += se.incMHPr;
                        percent_mp += se.incMMPr;
                        
                        wdef += se.incPDD;
                        mdef += se.incMDD;
                        if (se.option1 > 0 && se.option1Level > 0) {
                            soc = ii.getPotentialInfo(se.option1).get(se.option1Level);
                            if (soc != null) {
                                localmaxhp_ += soc.get("incMHP");
                                localmaxmp_ += soc.get("incMMP");
                                handleItemOption(soc, chra, first_login, sData);
                            }
                        }
                        if (se.option2 > 0 && se.option2Level > 0) {
                            soc = ii.getPotentialInfo(se.option2).get(se.option2Level);
                            if (soc != null) {
                                localmaxhp_ += soc.get("incMHP");
                                localmaxmp_ += soc.get("incMMP");
                                handleItemOption(soc, chra, first_login, sData);
                            }
                        }
                    }
                }
            }
        }
        handleProfessionTool(chra);
        for (Item item : chra.getInventory(MapleInventoryType.CASH).newList()) {
            if (item.getItemId() / 100000 == 52) {
                if (expMod < 3 && (item.getItemId() == 5211060 || item.getItemId() == 5211050 || item.getItemId() == 5211051 || item.getItemId() == 5211052 || item.getItemId() == 5211053 || item.getItemId() == 5211054)) {
                    expMod = 3.0; //overwrite
                } else if (expMod < 2 && (item.getItemId() == 5210000 || item.getItemId() == 5210001 || item.getItemId() == 5210002 || item.getItemId() == 5210003 || item.getItemId() == 5210004 || item.getItemId() == 5210005 || item.getItemId() == 5211061 || item.getItemId() == 5211000 || item.getItemId() == 5211001 || item.getItemId() == 5211002 || item.getItemId() == 5211003 || item.getItemId() == 5211046 || item.getItemId() == 5211047 || item.getItemId() == 5211048 || item.getItemId() == 5211049)) {
                    expMod = 2.0;
                } else if (expMod < 1.5 && (item.getItemId() == 5211068)) {
                    expMod = 1.5;
                }
            } else if (dropMod == 1.0 && item.getItemId() / 10000 == 536) {
                if (item.getItemId() >= 5360000 && item.getItemId() < 5360100) {
                    dropMod = 2.0;
                }
            } else if (item.getItemId() == 5710000) {
                questBonus = 2;
            } else if (item.getItemId() == 5590000) {
                levelBonus += 5;
            }
        }
        if (dropMod > 0 && EventConstants.DoubleTime) {
            dropMod *= 2.0;
        }
        if (expMod > 0 && EventConstants.DoubleTime) {
            expMod *= 2.0;
        }
        for (Item item : chra.getInventory(MapleInventoryType.ETC).list()) {
            switch (item.getItemId()) {
                case 4030003:
                    pickupRange = Double.POSITIVE_INFINITY;
                    break;
                case 4030004:
                    hasClone = true;
                    break;
                case 4031864:
                    cashMod = 2;
                    break;
            }
        }
        handleBuffStats(chra);
        Integer buff = chra.getBuffedValue(MapleBuffStat.ENHANCED_MAXHP);
        if (buff != null) {
            localmaxhp_ += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.ENHANCED_MAXMP);
        if (buff != null) {
            localmaxmp_ += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.HP_BOOST);
        if (buff != null) {
            localmaxhp_ += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.MP_BOOST);
        if (buff != null) {
            localmaxmp_ += buff.intValue();
        }
        handlePassiveSkills(chra);
        handleHyperPassiveSkills(chra);
        if (chra.getGuildId() > 0) {
            final MapleGuild g = World.Guild.getGuild(chra.getGuildId());
            if (g != null && g.getSkills().size() > 0) {
                final long now = System.currentTimeMillis();
                for (MapleGuildSkill gs : g.getSkills()) {
                    if (gs.timestamp > now && gs.activator.length() > 0) {
                        final MapleStatEffect e = SkillFactory.getSkill(gs.skillID).getEffect(gs.level);
                        crit_rate += e.getCr();
                        watk += e.getAttackX();
                        magic += e.getMagicX();
                        expBuff *= (e.getEXPRate() + 100.0) / 100.0;
                        dodgeChance += e.getER();
                        percent_wdef += e.getWDEFRate();
                        percent_mdef += e.getMDEFRate();
                    }
                }
            }
        }
        for (Pair<Integer, Integer> ix : chra.getCharacterCard().getCardEffects()) {
            final MapleStatEffect e = SkillFactory.getSkill(ix.getLeft()).getEffect(ix.getRight());
            percent_wdef += e.getWDEFRate();
            watk += (e.getLevelToWatk() * chra.getLevel());
            percent_hp += e.getPercentHP();
            percent_mp += e.getPercentMP();
            magic += (e.getLevelToMatk() * chra.getLevel());
            RecoveryUP += e.getMPConsumeEff();
            percent_acc += e.getPercentAcc();
            crit_rate += e.getCr();
            jump += e.getPassiveJump();
            speed += e.getPassiveSpeed();
            dodgeChance += e.getPercentAvoid();
            damX += (e.getLevelToDamage() * chra.getLevel());
            BuffUP_Summon += e.getSummonTimeInc();
            expLossReduceR += e.getEXPLossRate();
            ASR += e.getASRRate();
            //ignoreMobDamR
            suddenDeathR += e.getSuddenDeathR();
            BuffUP_Skill += e.getBuffTimeRate();
            //onHitHpRecoveryR
            //onHitMpRecoveryR
            coolTimeR += e.getCooltimeReduceR();
            incMesoProp += e.getMesoAcquisition();
            damX += Math.floor((e.getHpToDamage() * oldmaxhp) / 100.0f);
            damX += Math.floor((e.getMpToDamage() * oldmaxhp) / 100.0f);
            //finalAttackDamR
            passive_sharpeye_percent += e.getCriticalMax();
            ignoreTargetDEF += e.getIgnoreMob();
            localstr += e.getStrX();
            localdex += e.getDexX();
            localint_ += e.getIntX();
            localluk += e.getLukX();
            localmaxhp_ += e.getMaxHpX();
            localmaxmp_ += e.getMaxMpX();
            watk += e.getAttackX();
            magic += e.getMagicX();
            bossdam_r += e.getBossDamage();
        }

        localstr += Math.floor((localstr * percent_str) / 100.0f);
        localdex += Math.floor((localdex * percent_dex) / 100.0f);
        localint_ += Math.floor((localint_ * percent_int) / 100.0f);
        localluk += Math.floor((localluk * percent_luk) / 100.0f);
        if (localint_ > localdex) {
            accuracy += localint_ + Math.floor(localluk * 1.2);
        } else {
            accuracy += localluk + Math.floor(localdex * 1.2);
        }
        watk += Math.floor((watk * percent_atk) / 100.0f);
        magic += Math.floor((magic * percent_matk) / 100.0f);
        localint_ += Math.floor((localint_ * percent_matk) / 100.0f);

        wdef += Math.floor((localstr * 1.2) + ((localdex + localluk) * 0.5) + (localint_ * 0.4));
        mdef += Math.floor((localstr * 0.4) + ((localdex + localluk) * 0.5) + (localint_ * 1.2));
        wdef += Math.min(30000, Math.floor((wdef * percent_wdef) / 100.0f));
        mdef += Math.min(30000, Math.floor((wdef * percent_mdef) / 100.0f));

        hands = localdex + localint_ + localluk;
        calculateFame(chra);
        ignoreTargetDEF += chra.getTrait(MapleTraitType.charisma).getLevel() / 10;
        pvpDamage += chra.getTrait(MapleTraitType.charisma).getLevel() / 10;
        //ASR += chra.getTrait(MapleTraitType.will).getLevel() / 5;

        accuracy += Math.floor((accuracy * percent_acc) / 100.0f);
        accuracy += chra.getTrait(MapleTraitType.insight).getLevel() * 15 / 10;
        
        
        //[CUSTOM]: Inneral Abilities:
        //Apply formulas from InnerSkillValueHolder.java here;
        //PROOF OF CONCEPT. SEEMS TO WORK JUST FINE INGAME. 
        for(InnerSkillValueHolder ISVH : chra.getInnerSkills()){
            int x = ISVH.getSkillLevel();
            switch(ISVH.getSkillId()){
                case 70000000: localstr += MapleStatEffect.parseEval("x", x); break; //strFX = x
                case 70000001: localdex += MapleStatEffect.parseEval("x", x); break; 
                case 70000002: localint_ += MapleStatEffect.parseEval("x", x); break;
                case 70000003: localluk += MapleStatEffect.parseEval("x", x); break;
                //case 70000004: accuracy += MapleStatEffect.parseEval("(10 * x)", x); break; //accX = 10 * x
                case 70000005: evaR += MapleStatEffect.parseEval("(10 * x)", x); break;
                case 70000006: wdef += MapleStatEffect.parseEval("(10 * x)", x); break;
                case 70000007: mdef += MapleStatEffect.parseEval("(10 * x)", x); break;
                case 70000008: localmaxhp_ += MapleStatEffect.parseEval("(15 * x)", x); break;//mhpX = x * 15
                case 70000009: localmaxmp_ += MapleStatEffect.parseEval("(15 * x)", x);; break;
                case 70000010: jump += MapleStatEffect.parseEval("2*u(x/3)", x); break; //psdJump = 2 * u(x / 3)
                case 70000011: speed += MapleStatEffect.parseEval("2*u(x/3)", x);break;
                case 70000012: watk += MapleStatEffect.parseEval("3*u(x/3)", x); break; //padX = 3 * u(x / 3)
                case 70000013: magic += MapleStatEffect.parseEval("3*u(x/3)", x); break;
                //case 70000014: passive_sharpeye_rate += MapleStatEffect.parseEval("x", x); break;//client side
                case 70000015:{ //lukFX = x	strFX = x	dexFX = x	intFX = x
                    localstr += MapleStatEffect.parseEval("x", x);
                    localdex += MapleStatEffect.parseEval("x", x);
                    localint_ += MapleStatEffect.parseEval("x", x);
                    localluk += MapleStatEffect.parseEval("x", x);                 
                } break;
                case 70000016: break; //actionSpeed ??= -1 //this is attack speed, isn't it?  client-sided
                case 70000017: mdef += (wdef * MapleStatEffect.parseEval("u(x / 4)", x) / 100); break;// pdd2mdd = u (x / 4)
                case 70000018: wdef += (mdef * MapleStatEffect.parseEval("u(x / 4)", x) / 100); break;
                case 70000019: localmaxmp_ += (accuracy * MapleStatEffect.parseEval("(5 * u(x / 4))", x) / 100); break;//acc2mp = 5 * u (x / 4)
                case 70000020: localmaxhp_ += (evaR * MapleStatEffect.parseEval("(5 * u(x / 4))", x) / 100); break;
                case 70000021: localdex += (str * MapleStatEffect.parseEval("u(x / 4)", x) / 100); break; //str2dex = u (x / 4) (str is base, localstr is bonus included)
                case 70000022: localstr += (dex * MapleStatEffect.parseEval("u(x / 4)", x) / 100); break;
                case 70000023: localluk += (int_ *MapleStatEffect.parseEval("u(x / 4)", x) / 100); break;
                case 70000024: localdex += (luk * MapleStatEffect.parseEval("u(x / 4)", x) / 100); break;
                case 70000025:{ 
                    int perLevelGain = MapleStatEffect.parseEval("(20 - (2 * d(x / 2)))", x); 
                    watk += d(chra.getLevel() / perLevelGain);
                } break; //lv2pad = 20-2 * d (x / 2)	
                case 70000026: { 
                    int perLevelGain = MapleStatEffect.parseEval("(20 - (2 * d(x / 2)))", x);
                    magic += d(chra.getLevel() / perLevelGain);
                } break;
                case 70000027: accuracy += (accuracy * (MapleStatEffect.parseEval("x", x) / 100)); break;//accR = x
                case 70000028: evaR += (evaR * (MapleStatEffect.parseEval("x", x) / 100)); break;
                case 70000029: wdef += (wdef * (MapleStatEffect.parseEval("x", x) / 100)); break;
                case 70000030: mdef += (mdef * (MapleStatEffect.parseEval("x", x) / 100)); break;
                case 70000031: percent_hp += ((MapleStatEffect.parseEval("x", x))); break;
                case 70000032: percent_mp += ((MapleStatEffect.parseEval("x", x))); break;
                //case 70000033: accuracy += (accuracy * (MapleStatEffect.parseEval("u(x / 2)", x) / 100)); break; //ar = u (x/2)
                case 70000034: evaR += (evaR * (MapleStatEffect.parseEval("u(x / 2)", x) / 100)); break;
                case 70000035: break;//bossdam_r += MapleStatEffect.parseEval("x", x); break;//bdR = x //Clientsided?
                case 70000036: break;//+% Damage to Norm Mobs//Clientsided?
                case 70000037: break;//+% Damage to Towers//Clientsided?
                case 70000038: break;//+% Chance to insta-kill in Azwan Supply//Clientsided?
                case 70000039: break;//+% Damage when attacking abnormal ailment targets.//Clientsided?
                case 70000040: { //Definitely needs to be looked into. "% of Wep Acc or Magic ACC (>) added to additional damage.
                    watk += (accuracy * (MapleStatEffect.parseEval("x * 2 + u (x / 2)", x) / 100));//acc2dam = x * 2 + u (x / 2)
                    magic += (accuracy * (MapleStatEffect.parseEval("x * 2 + u (x / 2)", x) / 100));
                            //Assuming only one type of attack (magic or weapon) can be active at a time, just apply them to both;
                } break;
                case 70000041:{
                    watk += (wdef * (MapleStatEffect.parseEval("x * 2 + u (x / 2)", x) / 100));//pdd2dam = x * 2 + u (x / 2)
                    magic += (wdef * (MapleStatEffect.parseEval("x * 2 + u (x / 2)", x) / 100));
                } break;
                case 70000042: {
                    watk += (wdef * (MapleStatEffect.parseEval("x * 2 + u (x / 2)", x) / 100));//pdd2dam = x * 2 + u (x / 2)
                    magic += (mdef * (MapleStatEffect.parseEval("x * 2 + u (x / 2)", x) / 100));
                } break;
                case 70000043: 
                break; //When hit with magic attack, damage equal to % of WDEF is ignored. //Clientsided?
                case 70000044: 
                break; //When hit with physical attack, damage equal to % of MDEF is ignored. //Clientsided?
                case 70000045: nocoolProp += MapleStatEffect.parseEval("x", x); 
                break;//Cooldown not applied at % Chance should be hooked elsewhere. //Clientsided? TEST.
                case 70000046: //Increase Skill level of passive skills by # //Pretty sure Clientsided
                break;
                case 70000047: //Number of enemies hit by multi target skills +1 //Clientsided.
                break;
                case 70000048: BuffUP_Skill += (MapleStatEffect.parseEval("x+u(x/4)", x)); 
                break; //x+u(x/4)
                case 70000049: dropBuff += (MapleStatEffect.parseEval("u(x / 2)", x)); 
                break; //dropR = u (x / 2)	
                case 70000050: mesoBuff += (MapleStatEffect.parseEval("u(x / 2)", x)); 
                break;
                case 70000051: {
                    localstr += MapleStatEffect.parseEval("x", x);
                    localdex += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000052: {
                    localstr += MapleStatEffect.parseEval("x", x);
                    localint_ += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000053: {
                    localstr += MapleStatEffect.parseEval("x", x);
                    localluk += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000054: {
                    localdex += MapleStatEffect.parseEval("x", x);
                    localint_ += MapleStatEffect.parseEval("u(x / 2)", x);
                } break; 
                case 70000055: {
                    localdex += MapleStatEffect.parseEval("x", x);
                    localluk += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000056: {
                    localint_ += MapleStatEffect.parseEval("x", x);
                    localluk += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000057: {
                    localdex += MapleStatEffect.parseEval("x", x);
                    localstr += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000058: {
                    localint_ += MapleStatEffect.parseEval("x", x);
                    localstr += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000059: {
                    localluk += MapleStatEffect.parseEval("x", x);
                    localstr += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000060: {
                    localint_ += MapleStatEffect.parseEval("x", x);
                    localdex += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000061:  {
                    localluk += MapleStatEffect.parseEval("x", x);
                    localdex += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                case 70000062:{
                    localluk += MapleStatEffect.parseEval("x", x);
                    localint_ += MapleStatEffect.parseEval("u(x / 2)", x);
                } break;
                default:
                    break;
            }
        }
            if (chra.hasEquipped(1032077)) {
            percent_hp += 10;
            }
            if (chra.hasEquipped(1032078)) {
            percent_hp += 10;
            }
            if (chra.hasEquipped(1032079)) {
            percent_hp += 10;
            }
            if (chra.hasEquipped(1032102)) {
            percent_hp += 10;
            }
            if (chra.hasEquipped(1032103)) {
            percent_hp += 10;
            }
            if (chra.hasEquipped(1032104)) {
            percent_hp += 10;
            }
            if (chra.hasEquipped(1122077)) {
            percent_hp += 7;
            }
            if (chra.hasEquipped(1122076)) {
            percent_hp += 10;
            percent_mp += 10;
            }
            if (chra.hasEquipped(1122114)) {
            percent_hp += 10;
            percent_mp += 10;
            }
            if (chra.hasEquipped(1122150)) {
            percent_hp += 10;
            percent_mp += 10;
            }
            if (chra.hasEquipped(1122151)) {
            percent_hp += 10;
            percent_mp += 10;
            }
            if (chra.hasEquipped(1003112)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        //Eluna
        if (chra.hasEquipped(1122380)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        if (chra.hasEquipped(1122385)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        if (chra.hasEquipped(1122390)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        if (chra.hasEquipped(1122395)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        if (chra.hasEquipped(1122400)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        if (chra.hasEquipped(1122405)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        if (chra.hasEquipped(1122410)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        if (chra.hasEquipped(1122415)) {
            percent_hp += 10;
            percent_mp += 10;
            }
        if (GameConstants.isAngelicBuster(chra.getJob())) {
        Skill bx;
        int bof;
        bx = SkillFactory.getSkill(65110005); //Love Me Heart
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    localmaxhp_ += bx.getEffect(bof).getMaxHpX();
                } 
        }
        Skill bx;
        int bof;
        MapleStatEffect eff;
        bx = SkillFactory.getSkill(80000133);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            localmaxhp_ += eff.getMaxHpX();
            localmaxmp_ += eff.getMaxMpX();
        }
        bx = SkillFactory.getSkill(70000079);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            localmaxhp_ += eff.getMaxHpX();
            localmaxmp_ += eff.getMaxMpX();
        }
        bx = SkillFactory.getSkill(80000134);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            localmaxhp_ += eff.getMaxHpX();
            localmaxmp_ += eff.getMaxMpX();
        }
        bx = SkillFactory.getSkill(80000135);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            localmaxhp_ += eff.getMaxHpX();
            localmaxmp_ += eff.getMaxMpX();
        }
        bx = SkillFactory.getSkill(80000136);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            localmaxhp_ += eff.getMaxHpX();
            localmaxmp_ += eff.getMaxMpX();
        }
        bx = SkillFactory.getSkill(1000009);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            localmaxhp_ += eff.getlv2mhp() * chra.getLevel();
        }
        localmaxhp_ += chra.getTrait(MapleTraitType.will).getLevel() / 5 * 100;
        localmaxhp_ += Math.floor((percent_hp * localmaxhp_) / 100.0f);
        localmaxhp = Math.min(500000, Math.abs(Math.max(-500000, localmaxhp_)));

        localmaxmp_ += chra.getTrait(MapleTraitType.sense).getLevel() / 5 * 100;
        localmaxmp_ += Math.floor((percent_mp * localmaxmp_) / 100.0f);
        localmaxmp = Math.min(500000, Math.abs(Math.max(-500000, localmaxmp_)));
        
        BuffUP_Skill += chra.getTrait(MapleTraitType.sense).getLevel() / 10;
        if (chra.getEventInstance() != null && chra.getEventInstance().getName().startsWith("PVP")) { //hack
            //MapleStatEffect eff;
            localmaxhp = Math.min(40000, localmaxhp * 3); //approximate.
            localmaxmp = Math.min(20000, localmaxmp * 2);
            //not sure on 20000 cap
            for (int i : pvpSkills) {
                Skill skil = SkillFactory.getSkill(i);
                if (skil != null && skil.canBeLearnedBy(chra.getJob())) {
                    sData.put(skil, new SkillEntry((byte) 1, (byte) 0, -1));
                    eff = skil.getEffect(1);
                    switch ((i / 1000000) % 10) {
                        case 1:
                            if (eff.getX() > 0) {
                                pvpDamage += (wdef / eff.getX());
                            }
                            break;
                        case 3:
                            hpRecoverProp += eff.getProb();
                            hpRecover += eff.getX();
                            mpRecoverProp += eff.getProb();
                            mpRecover += eff.getX();
                            break;
                        case 5:
                            crit_rate += eff.getProb();
                            passive_sharpeye_percent = 100;
                            break;
                    }
                    break;
                }
            }
            eff = chra.getStatForBuff(MapleBuffStat.MORPH);
            if (eff != null && eff.getSourceId() % 10000 == 1105) { //ice knight
                localmaxhp = 500000;
                localmaxmp = 500000;
            }
        }
        chra.changeSkillLevel_Skip(sData, false);
        if (GameConstants.isDemonSlayer(chra.getJob())) {
            localmaxmp = DemonMP;
        } else if (GameConstants.isZero(chra.getJob())) {
            localmaxmp = 100;
        }
        if (GameConstants.isDemonAvenger(chra.getJob())) {
            chra.getClient().getSession().write(JobPacket.AvengerPacket.giveAvengerHpBuff(hp/2));
        }
        CalcPassive_SharpEye(chra);
        CalcPassive_Mastery(chra);
        recalcPVPRank(chra);
        if (first_login) {
            chra.silentEnforceMaxHpMp();
            relocHeal(chra);
        } else {
            chra.enforceMaxHpMp();
        }
        calculateMaxBaseDamage(Math.max(magic, watk), pvpDamage, chra);
        trueMastery = Math.min(100, trueMastery);
        passive_sharpeye_min_percent = (short) Math.min(passive_sharpeye_min_percent, passive_sharpeye_percent);
        if (oldmaxhp != 0 && oldmaxhp != localmaxhp) {
            chra.updatePartyMemberHP();
        }
    }
    
        public List<Triple<Integer, String, Integer>> getPsdSkills(){
        return psdSkills;
    }

    private void handlePassiveSkills(MapleCharacter chra) {
        Skill bx;
        int bof;
        MapleStatEffect eff;
        if (GameConstants.isKOC(chra.getJob())) {
            bx = SkillFactory.getSkill(2000006);
            bof = chra.getTotalSkillLevel(bx);
            if (bof > 0) {
                eff = bx.getEffect(bof);
                percent_hp += eff.getX();
                percent_mp += eff.getX();
            }
        }
        
                psdSkills.clear();
        for(Skill sk : chra.getSkills().keySet()){
            if(sk.getPsd() == 1){
                Triple<Integer, String, Integer> psdSkill = new Triple<>(0, "", 0);
                psdSkill.left = sk.getPsdSkill();
                psdSkill.mid = sk.getPsdDamR(); //This only handles damage increases; some skills have effects other than that, so TODO
                psdSkill.mid = sk.getPsdtarget();
                psdSkill.right = sk.getId();
                psdSkills.add(psdSkill);
            }
        }
        switch (chra.getJob()) {
            case 200:
            case 210:
            case 211:
            case 212:
            case 220:
            case 221:
            case 222:
            case 230:
            case 231:
            case 232: {
                bx = SkillFactory.getSkill(2320045);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(2120051);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(2000006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_mp += bx.getEffect(bof).getPercentMP();
                }
                break;
            }
            case 1200:
            case 1210:
            case 1211:
            case 1212: {
                bx = SkillFactory.getSkill(12000005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_mp += bx.getEffect(bof).getPercentMP();
                }
                bx = SkillFactory.getSkill(12110000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    TER += bx.getEffect(bof).getX();
                }
                break;
            }
            case 1100:
            case 1110:
            case 1111:
            case 1112: {
                bx = SkillFactory.getSkill(11000005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                break;
            }
            case 2003: // Phantom noob
                // Phantom Instinct - 20030204
                // Dexterous Training - 20030206
                bx = SkillFactory.getSkill(20030204); // +10% crit rate
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    crit_rate += eff.getCr();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                bx = SkillFactory.getSkill(20030206); // +40 DEX, Base Avoidability: +20%
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    localdex += eff.getDexX();
                    dodgeChance += eff.getER();
                }
                break;
            case 2400:
            case 2410:
            case 2411:
            case 2412: { // Phantom
                // Blanc Carte - 24100003
                // Cane Mastery - 24100004
                // Luck of Phantom Thief - 24111002
                // 24111003- uses monlight effect, but is Misfortune Protection
                // 24110004 - Flash and Flee -> active
                // 24111005 - Moonlight
                // 24111006 - Phantom Charge
                // 24111008- Breeze Carte, (hidden), linked to phantom charge
                // 24121000 - Ultimate Drive
                // 24120002 - Noir Carte
                // 24121003 - Twilight
                // 24121004 - Pray of Aria
                // 24121005 - Tempest of Card
                // 24120006 - Cane Expert
                // 24121007 - Soul Steal
                // 24121008 - Maple Warrior
                // 24121009 - Hero's will
                // 24121010 - Some linked skill (Twilight)
                bx = SkillFactory.getSkill(24120044);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(24001002); // Swift Phantom
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    speed += eff.getPassiveSpeed();
                    jump += eff.getPassiveJump();
                }
                bx = SkillFactory.getSkill(24000003); // Quick Evasion
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dodgeChance += eff.getX();
                }
                bx = SkillFactory.getSkill(24100006); //Luck Monopoly
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    localluk += eff.getLukX();
                }
                bx = SkillFactory.getSkill(24110007); // Acute Sense
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    crit_rate += eff.getCr();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                bx = SkillFactory.getSkill(24111002); //Luck of Phantom Thief
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    localluk += eff.getLukX();
                }
                break;
            }
            case 501:
            case 530:
            case 531:
            case 532:
        bx = SkillFactory.getSkill(110);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            localstr += eff.getStrX();
            localdex += eff.getDexX();
            localint_ += eff.getIntX();
            localluk += eff.getLukX();
            percent_hp += eff.getHpR();
            percent_mp += eff.getMpR();
        }
                bx = SkillFactory.getSkill(5010003);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    watk += bx.getEffect(bof).getAttackX();
                }
                bx = SkillFactory.getSkill(5300008);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(5311001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    damageIncrease.put(5301001, (int) bx.getEffect(bof).getDAMRate());
                }
                bx = SkillFactory.getSkill(5310007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getHpR();
                    ASR += eff.getASRRate();
                    percent_wdef += eff.getWDEFRate();
                }
                bx = SkillFactory.getSkill(5310006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    watk += bx.getEffect(bof).getAttackX();
                }
                bx = SkillFactory.getSkill(5321009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                    ignoreTargetDEF += eff.getIgnoreMob();
                }
                break;
            case 3001:
            case 3100:
            case 3110:
            case 3111:
            case 3112:
                mpRecoverProp = 100;
                bx = SkillFactory.getSkill(31120009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ignoreDAMrSkill += bx.getEffect(bof).getT();
                }
                bx = SkillFactory.getSkill(31000003);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getHpR();
                }
                bx = SkillFactory.getSkill(31100007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(31000004, (int) eff.getDAMRate());
                    damageIncrease.put(31001006, (int) eff.getDAMRate());
                    damageIncrease.put(31001007, (int) eff.getDAMRate());
                    damageIncrease.put(31001008, (int) eff.getDAMRate());
                }
                bx = SkillFactory.getSkill(31100005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(31100010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(31000004, (int) eff.getX());
                    damageIncrease.put(31001006, (int) eff.getX());
                    damageIncrease.put(31001007, (int) eff.getX());
                    damageIncrease.put(31001008, (int) eff.getX());
                }
                bx = SkillFactory.getSkill(31111007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                }
                bx = SkillFactory.getSkill(31110008);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dodgeChance += eff.getX();
                }
                bx = SkillFactory.getSkill(31110009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    mpRecover += 1;
                    mpRecoverProp += eff.getProb();
                }
                bx = SkillFactory.getSkill(31111006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getX() + 100.0) / 100.0;
                    bossdam_r *= (eff.getX() + 100.0) / 100.0;
                    crit_rate += eff.getY();
                }
                bx = SkillFactory.getSkill(31121006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ignoreTargetDEF += bx.getEffect(bof).getIgnoreMob();
                }
                bx = SkillFactory.getSkill(31120011);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(31000004, (int) eff.getX());
                    damageIncrease.put(31001006, (int) eff.getX());
                    damageIncrease.put(31001007, (int) eff.getX());
                    damageIncrease.put(31001008, (int) eff.getX());
                }
                bx = SkillFactory.getSkill(31120008);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    watk += eff.getAttackX();
                    trueMastery += eff.getMastery();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                bx = SkillFactory.getSkill(31120010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_wdef += bx.getEffect(bof).getT();
                }
                bx = SkillFactory.getSkill(30010112);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    bossdam_r += eff.getBossDamage();
                    mpRecover += eff.getX();
                    mpRecoverProp += eff.getBossDamage(); //yes
                }
                bx = SkillFactory.getSkill(30010185);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    if (chra.getTrait(MapleTraitType.charisma).getLevel() < 20) {
                    chra.getTrait(MapleTraitType.charisma).addExp(4563);    
                    }
                    if (chra.getTrait(MapleTraitType.will).getLevel() < 20) {
                    chra.getTrait(MapleTraitType.will).addExp(4563);    
                    }
                }
                bx = SkillFactory.getSkill(30010111);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    hpRecoverSkillPercent += eff.getX();
                    hpRecoverSkillProp += eff.getProb(); //yes
                }
                bx = SkillFactory.getSkill(31121054);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    reduceForceR += eff.getreduceForceR();
                   /*if (chra.isSuperGM()) {
                    chra.dropMessage(5,"reduceForceR value is " + reduceForceR);    
                    }*/
                }
                bx = SkillFactory.getSkill(31110009); //Max Fury
                bof = chra.getTotalSkillLevel(bx);
                if(bof > 0){
                    final int DFRecovery = 2 * bof;
                    if(chra.getDFRecoveryTimer() != null){
                        chra.getDFRecoveryTimer().cancel();
                        chra.getDFRecoveryTimer().purge();
                    }
                    chra.setDFRecoveryTimer(new java.util.Timer());
                    TimerTask healTask = new TimerTask(){
                        @Override
                        public void run(){
                            if(chra.getStat().getMp() < DemonMP && chra.isAlive()){
                                chra.handleForceGain(0,0,DFRecovery);
                            }
                        }
                    };
                    chra.getDFRecoveryTimer().scheduleAtFixedRate(healTask, 4000, 4000);
                }
                break;
            case 3002:
            case 3600:
            case 3610:
            case 3611:
            case 3612:
                {
                bx = SkillFactory.getSkill(30020234); //Lateral 1
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                 bx = SkillFactory.getSkill(36000004); //Lateral 2
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getZ();
                    percent_mp += bx.getEffect(bof).getS();
                }
                                 bx = SkillFactory.getSkill(36100007); //Lateral 3
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getS();
                    percent_mp += bx.getEffect(bof).getS();
                }
               bx = SkillFactory.getSkill(36110007); //Lateral 4
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getS();
                    percent_mp += bx.getEffect(bof).getS();
                }
                               bx = SkillFactory.getSkill(36120010); //Lateral 5
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getS();
                    percent_mp += bx.getEffect(bof).getS();
                }
                  bx = SkillFactory.getSkill(36120045); //Lateral 5
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    targetPlus += bx.getEffect(bof).gettargetPlus();
                }
                break;
            }
            case 6000:
            case 6100:
            case 6110:
            case 6111:
            case 6112:
                {
                 bx = SkillFactory.getSkill(61100007); //Inner Blaze
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
               bx = SkillFactory.getSkill(61110007); //Adv Inner Blade
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                               bx = SkillFactory.getSkill(60000222); //Iron Will
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                        
                break;
            }
            case 6001:
            case 6500:
            case 6510:
            case 6511:
            case 6512:
                {
                bx = SkillFactory.getSkill(65120006); //Affinity Heart IV
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ReAttempt += bx.getEffect(bof).getX();
                }        
                bx = SkillFactory.getSkill(65111100); //Soul Seeker
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    EssenceRecreationProp += bx.getEffect(bof).getS();
                }
                bx = SkillFactory.getSkill(65120044); //Soul Seeker - Make Up
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    EssenceRecreationProp += bx.getEffect(bof).getProb();
                }
                bx = SkillFactory.getSkill(65000003); //Affinity Heart I
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    RechargeRate += bx.getEffect(bof).getX();
                }
                /*bx = SkillFactory.getSkill(65120045); //Soul Seeker - RechargeUp
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    RechargeRate += bx.getEffect(bof).getOnActive();
                }*/
                bx = SkillFactory.getSkill(65100005); //Affinity Heart II
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    MonsterKills += bx.getEffect(bof).getX();
                }
                bx = SkillFactory.getSkill(65120048); //Finale Ribbon - Cooldown Cutter
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    HyperCoolDownReduce = bx.getEffect(bof).getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(65120050); //Soul Resonance - Cooldown Cutter
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    HyperCoolDownReduce2 = bx.getEffect(bof).getCooltimeReduceR();
                }
                break;
            }
            case 3101:
            case 3120:
            case 3121:
            case 3122: {
             bx = SkillFactory.getSkill(31011001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    HPRestore += bx.getEffect(bof).getX();
                }   
                break;
            }
            case 510:
            case 511:
            case 512: {
                bx = SkillFactory.getSkill(5100009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                break;
            }
            case 1510:
            case 1511:
            case 1512: {
                bx = SkillFactory.getSkill(15100007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                break;
            }
            case 508:
            case 570:
            case 571:
            case 572: { // Jett
                bx = SkillFactory.getSkill(5080000); // Comet Booster
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    accuracy += eff.getAccX();
                    jump += eff.getPassiveJump();
                    speed += eff.getSpeedMax(); // TODO: split speed max and speed. (speed have a limit, while speedMax will add to the max)
                } // TODO: research more on percentage hp/mp and stats, which doesn't take effect to note.
                bx = SkillFactory.getSkill(5080004); // Shadow Heart
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    crit_rate += eff.getCr();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                // 5081010, 5081011: Hidden
                if (chra.getJob() >= 570) {
                    bx = SkillFactory.getSkill(5700003); // Physical Training
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        localstr += eff.getStrX();
                        localdex += eff.getDexX();
                    }
                }
                if (chra.getJob() >= 571) {
                    bx = SkillFactory.getSkill(5710004); // High Life
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        percent_wdef += eff.getWDEFRate();
                        percent_mdef += eff.getMDEFRate();
                        add_hp += eff.getMaxHpX();
                        add_mp += eff.getMaxMpX();
                    }
                    bx = SkillFactory.getSkill(5710005); // Cutting Edge
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        crit_rate += eff.getCr();
                        ignoreTargetDEF += eff.getIgnoreMob();
                    }
                }
                if (chra.getJob() == 572) {
                    bx = SkillFactory.getSkill(5720008); // Collateral Damage
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        crit_rate += eff.getCr();
                        passive_sharpeye_min_percent += eff.getCriticalMin();
                        passive_sharpeye_percent += eff.getCriticalMax();
                        bossdam_r += eff.getBossDamage();
                    }
                    // TODO: 5721009, 5720012(Counterattack)
                }
                break;
            }
            case 400: // Thief
            case 410: // Assassin
            case 411: // Hermit
            case 412: // Night Lord
            case 420: // Bandit
            case 421: // Chief Bandit
            case 422: { // Shadower
                bx = SkillFactory.getSkill(4001005); // Haste
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    speed += eff.getSpeedMax();
                }
                // 4000010: Magic Theft, invisible.
                if (chra.getJob() >= 410 && chra.getJob() <= 412) {
                    bx = SkillFactory.getSkill(4100007); // Physical Training
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        localluk += eff.getLukX();
                        localdex += eff.getDexX();
                    }
                }
                if (chra.getJob() == 411 || chra.getJob() == 412) {
                    bx = SkillFactory.getSkill(4110008); // Enveloping Darkness
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        percent_hp += eff.getPercentHP();
                        ASR += eff.getASRRate();
                        TER += eff.getTERRate();
                    }
                    bx = SkillFactory.getSkill(4110012); // Expert Throwing Star Handling
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        damageIncrease.put(4001344, eff.getDAMRate());
                        damageIncrease.put(4101008, eff.getDAMRate());
                        damageIncrease.put(4101009, eff.getDAMRate());
                        damageIncrease.put(4101010, eff.getDAMRate());
                    }
                    bx = SkillFactory.getSkill(4110014);
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        RecoveryUP += eff.getX() - 100;
                    }
                }
                if (chra.getJob() == 412) {
                    bx = SkillFactory.getSkill(4121014); // Dark Harmony
                    bof = chra.getTotalSkillLevel(bx);
                    if (bof > 0) {
                        eff = bx.getEffect(bof);
                        ignoreTargetDEF += eff.getIgnoreMob();
                    }
                }

                bx = SkillFactory.getSkill(4200006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getPercentHP();
                    ASR += eff.getASRRate();
                    TER += eff.getTERRate();
                }
                bx = SkillFactory.getSkill(4210000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_wdef += eff.getX();
                    percent_mdef += eff.getX();
                }
                break;
            }
            case 431: // Blade Acolyte
            case 432: // Blade Specialist
            case 433: // Blade Lord
            case 434: { // Blade Master
                bx = SkillFactory.getSkill(4001006); // Haste
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    speed += eff.getSpeedMax();
                }

                bx = SkillFactory.getSkill(4310004);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getPercentHP();
                    ASR += eff.getASRRate();
                    TER += eff.getTERRate();
                }
                bx = SkillFactory.getSkill(4341006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_wdef += eff.getWDEFRate();
                    percent_mdef += eff.getMDEFRate();
                }
                break;
            }
            case 100:
            case 110:
            case 111:
            case 112:
            case 120:
            case 121:
            case 122:
            case 130:
            case 131:
            case 132: {
                bx = SkillFactory.getSkill(1000006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                bx = SkillFactory.getSkill(1210001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_wdef += eff.getX();
                    percent_mdef += eff.getX();
                }
                bx = SkillFactory.getSkill(1220005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_wdef += bx.getEffect(bof).getT();
                }
                bx = SkillFactory.getSkill(1220010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    trueMastery += bx.getEffect(bof).getMastery();
                }
                bx = SkillFactory.getSkill(1310000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    TER += bx.getEffect(bof).getX();
                }
                break;
            }
            case 322: { // Crossbowman
                bx = SkillFactory.getSkill(3220051);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(3211010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getHpR();
                } 
                bx = SkillFactory.getSkill(3220004);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    watk += eff.getX();
                    trueMastery += eff.getMastery();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                bx = SkillFactory.getSkill(3220009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getPercentHP();
                    ignoreTargetDEF += eff.getIgnoreMob();
                }
                bx = SkillFactory.getSkill(3220005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0 && chra.getBuffedValue(MapleBuffStat.SPIRIT_LINK) != null) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getX();
                    dam_r *= (eff.getDamage() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDamage() + 100.0) / 100.0;
                }
                break;
            }
            case 312: { // Bowmaster
                bx = SkillFactory.getSkill(3120005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    watk += bx.getEffect(bof).getX();
                }
                bx = SkillFactory.getSkill(3120011);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getPercentHP();
                    ignoreTargetDEF += eff.getIgnoreMob();
                }
                bx = SkillFactory.getSkill(3120006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0 && chra.getBuffedValue(MapleBuffStat.SPIRIT_LINK) != null) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getX();
                    dam_r *= (eff.getDamage() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDamage() + 100.0) / 100.0;
                }
                break;
            }
            case 3510:
            case 3511:
            case 3512: {
                bx = SkillFactory.getSkill(35100000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    watk += bx.getEffect(bof).getAttackX();
                }
                bx = SkillFactory.getSkill(35120000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    trueMastery += bx.getEffect(bof).getMastery();
                }
                break;
            }
            case 3211:
            case 3212: {
                bx = SkillFactory.getSkill(32120048);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(32120051);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce2 += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(32110000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ASR += bx.getEffect(bof).getASRRate();
                    TER += bx.getEffect(bof).getTERRate();
                }
                bx = SkillFactory.getSkill(32110001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                bx = SkillFactory.getSkill(32120000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    magic += bx.getEffect(bof).getMagicX();
                }
                bx = SkillFactory.getSkill(32120001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getER();
                }
                bx = SkillFactory.getSkill(32120009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                break;
            }
            case 3300:
            case 3310:
            case 3311:
            case 3312: {
                bx = SkillFactory.getSkill(33120000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    watk += eff.getX();
                    trueMastery += eff.getMastery();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                bx = SkillFactory.getSkill(33110000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getDamage() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDamage() + 100.0) / 100.0;
                }
                bx = SkillFactory.getSkill(33120010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    ignoreTargetDEF += eff.getIgnoreMob();
                    dodgeChance += eff.getER();
                }
                bx = SkillFactory.getSkill(32110001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                }
                break;
            }
            case 2710:
            case 2711:
            case 2712:{
            bx = SkillFactory.getSkill(27111004);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ASR += bx.getEffect(bof).getASRRate();
                }
            bx = SkillFactory.getSkill(20040221);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    if (chra.getTrait(MapleTraitType.insight).getLevel() < 20) {
                    chra.getTrait(MapleTraitType.insight).addExp(4563);    
                    }
                    if (chra.getTrait(MapleTraitType.will).getLevel() < 20) {
                    chra.getTrait(MapleTraitType.will).addExp(4563);    
                    }
                }
            break;    
            }
            case 2200:
            case 2210:
            case 2211:
            case 2212:
            case 2213:
            case 2214:
            case 2215:
            case 2216:
            case 2217:
            case 2218: {
                 bx = SkillFactory.getSkill(22170047);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                magic += chra.getTotalSkillLevel(SkillFactory.getSkill(22000000));
                bx = SkillFactory.getSkill(22150000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    mpconPercent += eff.getX() - 100;
                    dam_r *= eff.getY() / 100.0;
                    bossdam_r *= eff.getY() / 100.0;
                }
                bx = SkillFactory.getSkill(22160000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getDamage() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDamage() + 100.0) / 100.0;
                }
                bx = SkillFactory.getSkill(22170001); // magic mastery, this is an invisible skill
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    magic += eff.getX();
                    trueMastery += eff.getMastery();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                break;
            }
            case 2112: {
                bx = SkillFactory.getSkill(21120001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    watk += eff.getX();
                    trueMastery += eff.getMastery();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                bx = SkillFactory.getSkill(21120044);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                break;
            }
            case 4100: // Hayato
            case 4110:
            case 4111:
            case 4112:
            {
             /*   if (chra.getJob() >= 4111) {
                        bx = SkillFactory.getSkill(41110006);// Willow Dodge
                        bof = chra.getTotalSkillLevel(bx);
                            if (bof > 0) {
                                eff = bx.getEffect(bof);
                                dodgeChance += bx.getEffect(bof).getER();
                                dam_r *= (eff.getX() * eff.getY() + 100.0) / 100.0;
                                bossdam_r *= (eff.getX() * eff.getY() + 100.0) / 100.0;
                            }
                    }
                * */
                bx = SkillFactory.getSkill(41120051);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                    if (chra.getJob() >= 4111) {
                        bx = SkillFactory.getSkill(41120006);// Willow Dodge 2
                        bof = chra.getTotalSkillLevel(bx);
                            if (bof > 0) {
                                eff = bx.getEffect(bof);
                                dodgeChance += eff.getPercentAvoid();//dodgeChance += eff.getER();
                            }
                    }
                    if (chra.getJob() >= 4110) {
                        bx = SkillFactory.getSkill(41100006); // Unfaltering Blade
                        bof = chra.getTotalSkillLevel(bx);
                            if (bof > 0) {
                                eff = bx.getEffect(bof);
                                accuracy += eff.getX();
                                passive_sharpeye_percent += eff.getCriticalMax();
                                passive_sharpeye_min_percent +=  eff.getCriticalMin();
                            }   
                    }
                    if (chra.getJob() >= 4100) {
                        bx = SkillFactory.getSkill(41000003); // Center Ki
                        bof = chra.getTotalSkillLevel(bx);
                            if (bof > 0) {
                                eff = bx.getEffect(bof);
                                localstr += eff.getStrX();
                                localdex += eff.getDexX();
                            }
                    }
            break;
        }
            case 4212:
            {
             bx = SkillFactory.getSkill(42120050);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
            break;
            }
            }
        if (chra.getSkillLevel(70000065) > 0) { 
        expBuff += 15;
        }
        bx = SkillFactory.getSkill(80000000);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            localstr += eff.getStrX();
            localdex += eff.getDexX();
            localint_ += eff.getIntX();
            localluk += eff.getLukX();
            percent_hp += eff.getHpR();
            percent_mp += eff.getMpR();
        }
        bx = SkillFactory.getSkill(70000031);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            percent_hp += eff.getHpR();
        }
        bx = SkillFactory.getSkill(70000063);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            percent_hp += eff.getHpR();
        }
        bx = SkillFactory.getSkill(80000001);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            bossdam_r += eff.getBossDamage();
        }
        bx = SkillFactory.getSkill(80001040);
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
           expBuff += 10;
        }
        if (GameConstants.isAdventurer(chra.getJob())) {
            bx = SkillFactory.getSkill(74);
            bof = chra.getSkillLevel(bx);
            if (bof > 0) {
                levelBonus += bx.getEffect(bof).getX();
            }

            bx = SkillFactory.getSkill(80);
            bof = chra.getSkillLevel(bx);
            if (bof > 0) {
                levelBonus += bx.getEffect(bof).getX();
            }

            bx = SkillFactory.getSkill(10074);
            bof = chra.getSkillLevel(bx);
            if (bof > 0) {
                levelBonus += bx.getEffect(bof).getX();
            }

            bx = SkillFactory.getSkill(10080);
            bof = chra.getSkillLevel(bx);
            if (bof > 0) {
                levelBonus += bx.getEffect(bof).getX();
            }

            bx = SkillFactory.getSkill(110);
            bof = chra.getSkillLevel(bx);
            if (bof > 0) {
                eff = bx.getEffect(bof);
                localstr += eff.getStrX();
                localdex += eff.getDexX();
                localint_ += eff.getIntX();
                localluk += eff.getLukX();
                percent_hp += eff.getHpR();
                percent_mp += eff.getMpR();
            }

            bx = SkillFactory.getSkill(10110);
            bof = chra.getSkillLevel(bx);
            if (bof > 0) {
                eff = bx.getEffect(bof);
                localstr += eff.getStrX();
                localdex += eff.getDexX();
                localint_ += eff.getIntX();
                localluk += eff.getLukX();
                percent_hp += eff.getHpR();
                percent_mp += eff.getMpR();
            }
        }
        bx = SkillFactory.getSkill(GameConstants.getBOF_ForJob(chra.getJob()));
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            watk += eff.getX();
            magic += eff.getY();
            accuracy += eff.getX();
        }

        bx = SkillFactory.getSkill(GameConstants.getEmpress_ForJob(chra.getJob()));
        bof = chra.getSkillLevel(bx);
        if (bof > 0) {
            eff = bx.getEffect(bof);
            watk += eff.getX();
            magic += eff.getY();
            accuracy += eff.getZ();
        }
        switch (chra.getJob()) {
            case 210:
            case 211:
            case 212: { // IL
                bx = SkillFactory.getSkill(2100007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    localint_ += bx.getEffect(bof).getIntX();
                }
                bx = SkillFactory.getSkill(2110000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dotTime += eff.getX();
                    dot += eff.getZ();
                }
                bx = SkillFactory.getSkill(2110001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    mpconPercent += eff.getX() - 100;
                    dam_r *= eff.getY() / 100.0;
                    bossdam_r *= eff.getY() / 100.0;
                }
                bx = SkillFactory.getSkill(2121003);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(2111003, (int) eff.getX());
                }
                bx = SkillFactory.getSkill(2120009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    magic += eff.getMagicX();
                    BuffUP_Skill += eff.getX();
                }
                bx = SkillFactory.getSkill(2121005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    TER += bx.getEffect(bof).getTERRate();
                }
                bx = SkillFactory.getSkill(2121009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    magic += bx.getEffect(bof).getMagicX();
                }
                bx = SkillFactory.getSkill(2120010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getX() * eff.getY() + 100.0) / 100.0;
                    bossdam_r *= (eff.getX() * eff.getY() + 100.0) / 100.0;
                    ignoreTargetDEF += eff.getIgnoreMob();
                }
                break;
            }
            case 220:
            case 221:
            case 222: { // IL
                bx = SkillFactory.getSkill(2200007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    localint_ += bx.getEffect(bof).getIntX();
                }
                bx = SkillFactory.getSkill(2210000);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dot += bx.getEffect(bof).getZ();
                }
                bx = SkillFactory.getSkill(2210001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    mpconPercent += eff.getX() - 100;
                    dam_r *= eff.getY() / 100.0;
                    bossdam_r *= eff.getY() / 100.0;
                }
                bx = SkillFactory.getSkill(2220009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    magic += eff.getMagicX();
                    BuffUP_Skill += eff.getX();
                }
                bx = SkillFactory.getSkill(2221005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    TER += bx.getEffect(bof).getTERRate();
                }
                bx = SkillFactory.getSkill(2221009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    magic += bx.getEffect(bof).getMagicX();
                }
                bx = SkillFactory.getSkill(2220010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getX() * eff.getY() + 100.0) / 100.0;
                    bossdam_r *= (eff.getX() * eff.getY() + 100.0) / 100.0;
                    ignoreTargetDEF += eff.getIgnoreMob();
                }
                break;
            }
            case 1211:
            case 1212: { // flame
                bx = SkillFactory.getSkill(12110001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    mpconPercent += eff.getX() - 100;
                    dam_r *= eff.getY() / 100.0;
                    bossdam_r *= eff.getY() / 100.0;
                }

                bx = SkillFactory.getSkill(12111004);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    TER += bx.getEffect(bof).getTERRate();
                }
                break;
            }
            case 230:
            case 231:
            case 232: { // Bishop
                bx = SkillFactory.getSkill(2300007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    localint_ += bx.getEffect(bof).getIntX();
                }
                bx = SkillFactory.getSkill(2310008);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    crit_rate += bx.getEffect(bof).getCr();
                }
                bx = SkillFactory.getSkill(2320010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    magic += eff.getMagicX();
                    BuffUP_Skill += eff.getX();
                }
                bx = SkillFactory.getSkill(2321010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    magic += bx.getEffect(bof).getMagicX();
                }
                bx = SkillFactory.getSkill(2320005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ASR += bx.getEffect(bof).getASRRate();
                }
                bx = SkillFactory.getSkill(2320011);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getX() * eff.getY() + 100.0) / 100.0;
                    bossdam_r *= (eff.getX() * eff.getY() + 100.0) / 100.0;
                    ignoreTargetDEF += eff.getIgnoreMob();
                }
                break;
            }
            case 2002:
            case 2300:
            case 2310:
            case 2311:
            case 2312: {
                bx = SkillFactory.getSkill(20020112);
                bof = chra.getSkillLevel(bx);
                if (bof > 0) {
                    chra.getTrait(MapleTraitType.charm).addLocalExp(GameConstants.getTraitExpNeededForLevel(30));
                }
                bx = SkillFactory.getSkill(23000001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getER();
                }
                bx = SkillFactory.getSkill(23100008);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(20021110);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    expBuff += 10;
                }
                bx = SkillFactory.getSkill(23110004);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getProb();
                }
                bx = SkillFactory.getSkill(23110004);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    damageIncrease.put(23101001, (int) bx.getEffect(bof).getDAMRate());
                }
                bx = SkillFactory.getSkill(23121004);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getProb();
                }
                bx = SkillFactory.getSkill(23120009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    watk += bx.getEffect(bof).getX();
                }
                bx = SkillFactory.getSkill(23120010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ignoreTargetDEF += bx.getEffect(bof).getX(); //or should we do 100?
                }
                bx = SkillFactory.getSkill(23120011);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    damageIncrease.put(23101001, (int) bx.getEffect(bof).getDAMRate());
                }
                bx = SkillFactory.getSkill(23120012);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    watk += bx.getEffect(bof).getAttackX();
                }
                break;
            }
            case 1300:
            case 1310:
            case 1311:
            case 1312: {
                bx = SkillFactory.getSkill(13110025);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ignoreDAMrSkill += bx.getEffect(bof).getX();
                }
                bx = SkillFactory.getSkill(13000001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    defRange += bx.getEffect(bof).getRange();
                }
                bx = SkillFactory.getSkill(13110008);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getER();
                }
                bx = SkillFactory.getSkill(13110003);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    trueMastery += eff.getMastery();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                break;
            }
            case 300:
            case 310:
            case 311:
            case 312: {
                bx = SkillFactory.getSkill(3111010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getHpR();
                }
                bx = SkillFactory.getSkill(3000002);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    defRange += bx.getEffect(bof).getRange();
                }
                bx = SkillFactory.getSkill(3100006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(3001004, eff.getX());
                    damageIncrease.put(3001005, eff.getY());
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(3110007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getER();
                }
                bx = SkillFactory.getSkill(3120005);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    trueMastery += eff.getMastery();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                break;
            }
            case 320:
            case 321:
            case 322: {
                bx = SkillFactory.getSkill(3210007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getER();
                }
                bx = SkillFactory.getSkill(3000002);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    defRange += bx.getEffect(bof).getRange();
                }
                bx = SkillFactory.getSkill(3200006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(3001004, eff.getX());
                    damageIncrease.put(3001005, eff.getY());
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(3220010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    damageIncrease.put(3211006, bx.getEffect(bof).getDamage() - 150);
                }
                bx = SkillFactory.getSkill(3210007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getER();
                }
                break;
            }
            case 422: {
                bx = SkillFactory.getSkill(4221007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) { //Savage Blow, Steal, and Assaulter
                    eff = bx.getEffect(bof);
                    damageIncrease.put(4201005, (int) eff.getDAMRate());
                    damageIncrease.put(4201004, (int) eff.getDAMRate());
                    damageIncrease.put(4211002, (int) eff.getDAMRate());
                }
                bx = SkillFactory.getSkill(4210012);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    mesoBuff *= (eff.getMesoRate() + 100.0) / 100.0;
                    pickRate += eff.getU();
                    mesoGuard -= eff.getV();
                    mesoGuardMeso -= eff.getW();
                    damageIncrease.put(4211006, eff.getX());
                }
                break;
            }
            case 433:
            case 434: {
                bx = SkillFactory.getSkill(4340051);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(4330007);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    hpRecoverProp += eff.getProb();
                    hpRecoverPercent += eff.getX();
                }
                bx = SkillFactory.getSkill(4341002);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) { //Fatal Blow, Slash Storm, Tornado Spin, Bloody Storm, Upper Stab, and Flying Assaulter
                    eff = bx.getEffect(bof);
                    damageIncrease.put(4311002, (int) eff.getDAMRate());
                    damageIncrease.put(4311003, (int) eff.getDAMRate());
                    damageIncrease.put(4321000, (int) eff.getDAMRate());
                    damageIncrease.put(4321001, (int) eff.getDAMRate());
                    damageIncrease.put(4331000, (int) eff.getDAMRate());
                    damageIncrease.put(4331004, (int) eff.getDAMRate());
                    damageIncrease.put(4331005, (int) eff.getDAMRate());
                }
                bx = SkillFactory.getSkill(4341006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    dodgeChance += bx.getEffect(bof).getER();
                }
                break;
            }
            case 2110:
            case 2111:
            case 2112: { // Aran
                bx = SkillFactory.getSkill(21101006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDAMRate() + 100.0) / 100.0;
                }
                bx = SkillFactory.getSkill(21110002);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    damageIncrease.put(21000004, bx.getEffect(bof).getW());
                }
                bx = SkillFactory.getSkill(21111010);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ignoreTargetDEF += bx.getEffect(bof).getIgnoreMob();
                }
                bx = SkillFactory.getSkill(21120002);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    damageIncrease.put(21100007, bx.getEffect(bof).getZ());
                }
                bx = SkillFactory.getSkill(21120011);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(21100002, (int) eff.getDAMRate());
                    damageIncrease.put(21110003, (int) eff.getDAMRate());
                }
                break;
            }
            case 3511:
            case 3512: {
                bx = SkillFactory.getSkill(35120045);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(35110014);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) { //ME-07 Drillhands, Atomic Hammer
                    eff = bx.getEffect(bof);
                    damageIncrease.put(35001003, (int) eff.getDAMRate());
                    damageIncrease.put(35101003, (int) eff.getDAMRate());
                }
                bx = SkillFactory.getSkill(35121006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) { //Satellite
                    eff = bx.getEffect(bof);
                    damageIncrease.put(35111001, (int) eff.getDAMRate());
                 //   damageIncrease.put(35111009, (int) eff.getDAMRate());
                 //   damageIncrease.put(35111010, (int) eff.getDAMRate());
                }
                bx = SkillFactory.getSkill(35120001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) { //Satellite
                    eff = bx.getEffect(bof);
                    damageIncrease.put(35111005, eff.getX());
                    damageIncrease.put(35111011, eff.getX());
                    damageIncrease.put(35121009, eff.getX());
                    damageIncrease.put(35121010, eff.getX());
                    damageIncrease.put(35121011, eff.getX());
                    BuffUP_Summon += eff.getY();
                }
                break;
            }
            case 110:
            case 111:
            case 112: {
                bx = SkillFactory.getSkill(1001003);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_hp += eff.getHpR();
                }
                bx = SkillFactory.getSkill(1100009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(1001004, eff.getX());
                    damageIncrease.put(1001005, eff.getY());
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(1110009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= eff.getDamage() / 100.0;
                    bossdam_r *= eff.getDamage() / 100.0;
                }
                bx = SkillFactory.getSkill(1120012);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ignoreTargetDEF += bx.getEffect(bof).getIgnoreMob();
                }
                bx = SkillFactory.getSkill(1120013);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    watk += eff.getAttackX();
                    damageIncrease.put(1100002, (int) eff.getDamage());
                }
                break;
            }
            case 120:
            case 121:
            case 122: {
                bx = SkillFactory.getSkill(1220051);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(1200009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(1001004, eff.getX());
                    damageIncrease.put(1001005, eff.getY());
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(1220006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    ASR += bx.getEffect(bof).getASRRate();
                    TER += bx.getEffect(bof).getTERRate();
                }
                break;
            }
            case 511:
            case 512: {
                bx = SkillFactory.getSkill(5110008);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) { //Backspin Blow, Double Uppercut, and Corkscrew Blow
                    eff = bx.getEffect(bof);
                    damageIncrease.put(5101002, eff.getX());
                    damageIncrease.put(5101003, eff.getY());
                    damageIncrease.put(5101004, eff.getZ());
                }
                break;
            }
            case 520:
            case 521:
            case 522: {
                bx = SkillFactory.getSkill(5220048);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(5220001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) { //Flamethrower and Ice Splitter
                    eff = bx.getEffect(bof);
                    damageIncrease.put(5211004, (int) eff.getDamage());
                    damageIncrease.put(5211005, (int) eff.getDamage());
                }
                break;
            }
            case 130:
            case 131:
            case 132: {
                bx = SkillFactory.getSkill(1320047);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    HyperCoolDownReduce += eff.getCooltimeReduceR();
                }
                bx = SkillFactory.getSkill(1300009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(1001004, eff.getX());
                    damageIncrease.put(1001005, eff.getY());
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(1310009);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    crit_rate += eff.getCr();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                    hpRecoverProp += eff.getProb();
                    hpRecoverPercent += eff.getX();
                }
                bx = SkillFactory.getSkill(1320006);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    dam_r *= (eff.getDamage() + 100.0) / 100.0;
                    bossdam_r *= (eff.getDamage() + 100.0) / 100.0;
                }
                break;
            }
            case 1400:
            case 1410:
            case 1411:
            case 1412: {
                bx = SkillFactory.getSkill(14110003);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    RecoveryUP += eff.getX() - 100;
                    BuffUP += eff.getY() - 100;
                }
                bx = SkillFactory.getSkill(14000001);
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    defRange += bx.getEffect(bof).getRange();
                }
                break;
            }
            case 5000: // Mihile 0
            case 5100: // Mihile 1
            case 5110: // Mihile 2
            case 5111: // Mihile 3
            case 5112: { // Mihile 4
                // Mihile 1st Job Passive Skills
                bx = SkillFactory.getSkill(51000000); // Mihile || HP Boost
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_hp += bx.getEffect(bof).getPercentHP();
                }
                bx = SkillFactory.getSkill(51000001); // Mihile || Soul Shield
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    percent_wdef += eff.getX();
                    percent_mdef += eff.getX();
                }
                bx = SkillFactory.getSkill(51000002); // Mihile || Soul Devotion
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    accuracy += eff.getAccX();
                    speed += eff.getPassiveSpeed();
                    jump += eff.getPassiveJump();
                }

                // Mihile 2nd Job Passive Skills
                bx = SkillFactory.getSkill(51100000); // Mihile || Physical Training
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    damageIncrease.put(5001002, eff.getX());
                    damageIncrease.put(5001003, eff.getY());
                    localstr += eff.getStrX();
                    localdex += eff.getDexX();
                }
                bx = SkillFactory.getSkill(51120002); // Mihile || Final Attack && Advanced Final Attack
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    watk += eff.getAttackX();
                    damageIncrease.put(51100002, (int) eff.getDamage());
                }

                // Mihile 3rd Job Passive Skills
                bx = SkillFactory.getSkill(51110000); // Mihile || Self Recovery
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    hpRecoverProp += eff.getProb();
                    hpRecover += eff.getX();
                    mpRecoverProp += eff.getProb();
                    mpRecover += eff.getX();
                }
                bx = SkillFactory.getSkill(51110001); // Mihile || Intense Focus
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    localstr += eff.getStrX();
                    // Add Attack Speed here
                }
                bx = SkillFactory.getSkill(51110002); // Mihile || Righteous Indignation
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    ASR += eff.getX();
                    percent_atk += eff.getX();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }

                // Mihile 4th Job Passive Skills
                bx = SkillFactory.getSkill(51120000); // Mihile || Combat Mastery
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    ignoreTargetDEF += eff.getIgnoreMob();
                }
                bx = SkillFactory.getSkill(51120001); // Mihile || Expert Sword Mastery
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    eff = bx.getEffect(bof);
                    watk += bx.getEffect(bof).getX();
                    trueMastery += eff.getMastery();
                    passive_sharpeye_min_percent += eff.getCriticalMin();
                }
                bx = SkillFactory.getSkill(51120003); // Mihile || Soul Asylum
                bof = chra.getTotalSkillLevel(bx);
                if (bof > 0) {
                    percent_wdef += bx.getEffect(bof).getT();
                }
                break;
            }
        }
        if (GameConstants.isResist(chra.getJob())) {
            bx = SkillFactory.getSkill(30000002);
            bof = chra.getTotalSkillLevel(bx);
            if (bof > 0) {
                RecoveryUP += bx.getEffect(bof).getX() - 100;
            }
        }
    }

    private void handleHyperPassiveSkills(MapleCharacter chra) {
        int prefix = chra.getJob() * 10000;
        Skill bx;
        int bof;
        MapleStatEffect eff;
        for (int i = 30; i < 50; i++) {
            bx = SkillFactory.getSkill(prefix + i);
            bof = chra.getSkillLevel(bx);
            if (bx != null && bx.isHyper() && bof > 0) {
                eff = bx.getEffect(bof);
                if (eff != null) {
                    switch (i) {
                        case 30:
                            localstr += eff.getStrX();
                            break;
                        case 31:
                            localdex += eff.getDexX();
                            break;
                        case 32:
                            localint_ += eff.getIntX();
                            break;
                        case 33:
                            localluk += eff.getLukX();
                            break;
                        case 34:
                            crit_rate += eff.getCr();
                            break;
                        case 35:
                            accuracy += eff.getAccR();
                            break;
                        case 36:
                            percent_hp += eff.getPercentHP();
                            break;
                        case 37:
                            percent_mp += eff.getPercentMP();
                            break;
                        case 38:
                            localmaxmp += eff.getMaxDemonFury();
                            break;
                        case 39:
                            wdef += eff.getWDEFX();
                            break;
                        case 40:
                            mdef += eff.getMDEFX();
                            break;
                        case 41:
                            speed += eff.getSpeed();
                            break;
                        case 42:
                            jump += eff.getJump();
                            break;
                    }
                }
            }
        }
        for (int i = 30; i < 50; i++) {
            bx = SkillFactory.getSkill(prefix + i);
            bof = chra.getSkillLevel(bx);
            if (bx != null && bx.isHyper() && bof > 0) {
                eff = bx.getEffect(bof);
                String name = bx.getName();
                if (eff != null && name != null) {
                    int skill = GameConstants.findSkillByName(name.split(" - ")[0], prefix, 0);
                    if (skill != 0) {
                        Skill skil = SkillFactory.getSkill(skill);
                        if (skil != null && chra.getSkillLevel(skil) > 0) {
                            if (eff.getDAMRate() > 0) {
                                //skil.getEffect(chra.getSkillLevel(skil)).setDAMRate(eff.getDAMRate());
                            }
                        }
                    }
                }
            }
        }
    }

    private void handleBuffStats(MapleCharacter chra) {
        MapleStatEffect eff = chra.getStatForBuff(MapleBuffStat.MONSTER_RIDING);
        if (eff != null && eff.getSourceId() == 33001001) { // jaguar
            crit_rate += eff.getW();
            percent_hp += eff.getZ();
        }
        Integer buff = chra.getBuffedValue(MapleBuffStat.DICE_ROLL);
        if (buff != null) {
            percent_wdef += GameConstants.getDiceStat(buff.intValue(), 2);
            percent_mdef += GameConstants.getDiceStat(buff.intValue(), 2);
            percent_hp += GameConstants.getDiceStat(buff.intValue(), 3);
            percent_mp += GameConstants.getDiceStat(buff.intValue(), 3);
            crit_rate += GameConstants.getDiceStat(buff.intValue(), 4);
            dam_r *= (GameConstants.getDiceStat(buff.intValue(), 5) + 100.0) / 100.0;
            bossdam_r *= (GameConstants.getDiceStat(buff.intValue(), 5) + 100.0) / 100.0;
            expBuff *= (GameConstants.getDiceStat(buff.intValue(), 6) + 100.0) / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.HP_BOOST_PERCENT);
        if (buff != null) {
            percent_hp += buff.intValue();
        }
                buff = chra.getBuffedValue(MapleBuffStat.HAYATO3);
        if (buff != null) {
            percent_hp += buff.intValue();
        }
                        buff = chra.getBuffedValue(MapleBuffStat.HAYATO3);
        if (buff != null) {
            percent_mp += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.MP_BOOST_PERCENT);
        if (buff != null) {
            percent_mp += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.DEFENCE_BOOST_R);
        if (buff != null) {
            percent_wdef += buff.intValue();
            percent_mdef += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.ABNORMAL_STATUS_R);
        if (buff != null) {
            ASR += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.ELEMENTAL_STATUS_R);
        if (buff != null) {
            TER += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.INFINITY);
        if (buff != null) {
            percent_matk += buff.intValue() - 1;
        }
        buff = chra.getBuffedValue(MapleBuffStat.ONYX_SHROUD);
        if (buff != null) {
            dodgeChance += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.PVP_DAMAGE);
        if (buff != null) {
            pvpDamage += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.PVP_ATTACK);
        if (buff != null) {
            pvpDamage += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.FELINE_BERSERK);
        if (buff != null) {
            percent_hp += buff.intValue();
        }
        eff = chra.getStatForBuff(MapleBuffStat.BLUE_AURA);
        if (eff != null) {
            percent_wdef += eff.getZ() + eff.getY();
            percent_mdef += eff.getZ() + eff.getY();
        }
        buff = chra.getBuffedValue(MapleBuffStat.CONVERSION);
        if (buff != null) {
            percent_hp += buff.intValue();
        } else {
            buff = chra.getBuffedValue(MapleBuffStat.MAXHP);
            if (buff != null) {
                percent_hp += buff.intValue();
            }
        }
        buff = chra.getBuffedValue(MapleBuffStat.MAXMP);
        if (buff != null) {
            percent_mp += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.MP_BUFF);
        if (buff != null) {
            percent_mp += buff.intValue();
        }
        buff = chra.getBuffedSkill_X(MapleBuffStat.BUFF_MASTERY);
        if (buff != null) {
            BuffUP_Skill += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.STR);
        if (buff != null) {
            localstr += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.DEX);
        if (buff != null) {
            localdex += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.INT);
        if (buff != null) {
            localint_ += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.LUK);
        if (buff != null) {
            localluk += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.ANGEL_STAT);
        if (buff != null) {
            localstr += buff.intValue();
            localdex += buff.intValue();
            localint_ += buff.intValue();
            localluk += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.ENHANCED_WDEF);
        if (buff != null) {
            wdef += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.ENHANCED_MDEF);
        if (buff != null) {
            mdef += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.WDEF);
        if (buff != null) {
            wdef += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.WDEF);
        if (buff != null) {
            mdef += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.MAPLE_WARRIOR);
        if (buff != null) {
            final double d = buff.doubleValue() / 100.0;
            localstr += d * str; //base only
            localdex += d * dex;
            localluk += d * luk;
            localint_ += d * int_;
        }
        buff = chra.getBuffedValue(MapleBuffStat.ECHO_OF_HERO);
        if (buff != null) {
            final double d = buff.doubleValue() / 100.0;
            watk += (int) (watk * d);
            magic += (int) (magic * d);
        }
        buff = chra.getBuffedValue(MapleBuffStat.ARAN_COMBO);
        if (buff != null) {
            watk += buff.intValue() / 10;
        }
        buff = chra.getBuffedValue(MapleBuffStat.MESOGUARD);
        if (buff != null) {
            mesoGuardMeso += buff.doubleValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.EXPRATE);
        if (buff != null) {
            expBuff *= buff.doubleValue() / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.DROP_RATE);
        if (buff != null) {
            dropBuff += buff.doubleValue()/* / 100.0*/;
        }
        buff = chra.getBuffedValue(MapleBuffStat.ACASH_RATE);
        if (buff != null) {
            cashBuff *= buff.doubleValue() / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.MESO_RATE);
        if (buff != null) {
            mesoBuff += buff.doubleValue()/* / 100.0*/;
        }
        buff = chra.getBuffedValue(MapleBuffStat.MESOUP);
        if (buff != null) {
            mesoBuff *= buff.doubleValue() / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.ACC);
        if (buff != null) {
            accuracy += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.ANGEL_ACC);
        if (buff != null) {
            accuracy += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.INDIE_PAD);
        if (buff != null) {
            watk += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.INDIE_MAD);
        if (buff != null) {
            magic += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.WATK);
        if (buff != null) {
            watk += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.DAMAGE_R);
        if (buff != null) {
            crit_rate += buff.intValue();
            dam_r *= (buff.intValue() + 100.0) / 100.0;
            bossdam_r *= (buff.intValue() + 100.0) / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.ENHANCED_WATK);
        if (buff != null) {
            watk += buff.intValue();
        }
        eff = chra.getStatForBuff(MapleBuffStat.ENERGY_CHARGE);
        if (eff != null) {
            watk += eff.getWatk();
            accuracy += eff.getAcc();
        }
        buff = chra.getBuffedValue(MapleBuffStat.MATK);
        if (buff != null) {
            magic += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.SPEED);
        if (buff != null) {
            speed += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.JUMP);
        if (buff != null) {
            jump += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.DASH_SPEED);
        if (buff != null) {
            speed += buff.intValue();
        }
        buff = chra.getBuffedValue(MapleBuffStat.DASH_JUMP);
        if (buff != null) {
            jump += buff.intValue();
        }
        eff = chra.getStatForBuff(MapleBuffStat.HIDDEN_POTENTIAL);
        if (eff != null) {
            crit_rate = 100; //INTENSE
            ASR = 100; //INTENSE

            wdef += eff.getX();
            mdef += eff.getX();
            watk += eff.getX();
            magic += eff.getX();
        }
        buff = chra.getBuffedValue(MapleBuffStat.DAMAGE_BUFF);
        if (buff != null) {
            dam_r *= (buff.doubleValue() + 100.0) / 100.0;
            bossdam_r *= (buff.doubleValue() + 100.0) / 100.0;
        }
        buff = chra.getBuffedSkill_Y(MapleBuffStat.FINAL_CUT);
        if (buff != null) {
            dam_r *= buff.doubleValue() / 100.0;
            bossdam_r *= buff.doubleValue() / 100.0;
        }
        buff = chra.getBuffedSkill_Y(MapleBuffStat.OWL_SPIRIT);
        if (buff != null) {
            dam_r *= buff.doubleValue() / 100.0;
            bossdam_r *= buff.doubleValue() / 100.0;
        }
        buff = chra.getBuffedSkill_X(MapleBuffStat.BERSERK_FURY);
        if (buff != null) {
            dam_r *= buff.doubleValue() / 100.0;
            bossdam_r *= buff.doubleValue() / 100.0;
        }
        eff = chra.getStatForBuff(MapleBuffStat.BLESS);
        if (eff != null) {
            watk += eff.getX();
            magic += eff.getY();
            accuracy += eff.getV();
        }
        buff = chra.getBuffedSkill_X(MapleBuffStat.CONCENTRATE);
        if (buff != null) {
            mpconReduce += buff.intValue();
        }
        eff = chra.getStatForBuff(MapleBuffStat.HOLY_SHIELD);
        if (eff != null) {
            watk += eff.getX();
            magic += eff.getY();
            accuracy += eff.getV();
            mpconReduce += eff.getMPConReduce();
        }
        eff = chra.getStatForBuff(MapleBuffStat.MAGIC_RESISTANCE);
        if (eff != null) {
            ASR += eff.getX();
        }

        eff = chra.getStatForBuff(MapleBuffStat.COMBO);
        buff = chra.getBuffedValue(MapleBuffStat.COMBO);
        if (eff != null && buff != null) {
            dam_r *= ((100.0 + ((eff.getV() + eff.getDAMRate()) * (buff.intValue() - 1))) / 100.0);
            bossdam_r *= ((100.0 + ((eff.getV() + eff.getDAMRate()) * (buff.intValue() - 1))) / 100.0);
        }
        eff = chra.getStatForBuff(MapleBuffStat.SUMMON);
        if (eff != null) {
            if (eff.getSourceId() == 35121010) { //amp
                dam_r *= (eff.getX() + 100.0) / 100.0;
                bossdam_r *= (eff.getX() + 100.0) / 100.0;
            }
        }
        eff = chra.getStatForBuff(MapleBuffStat.DARK_AURA);
        if (eff != null) {
            dam_r *= (eff.getX() + 100.0) / 100.0;
            bossdam_r *= (eff.getX() + 100.0) / 100.0;
        }
        eff = chra.getStatForBuff(MapleBuffStat.BODY_BOOST);
        if (eff != null) {
            dam_r *= (eff.getV() + 100.0) / 100.0;
            bossdam_r *= (eff.getV() + 100.0) / 100.0;
        }
        eff = chra.getStatForBuff(MapleBuffStat.BEHOLDER);
        if (eff != null) {
            trueMastery += eff.getMastery();
        }
        eff = chra.getStatForBuff(MapleBuffStat.MECH_CHANGE);
        if (eff != null) {
            crit_rate += eff.getCr();
        }
        eff = chra.getStatForBuff(MapleBuffStat.PYRAMID_PQ);
        if (eff != null && eff.getBerserk() > 0) {
            dam_r *= eff.getBerserk() / 100.0;
            bossdam_r *= eff.getBerserk() / 100.0;
        }
        eff = chra.getStatForBuff(MapleBuffStat.WK_CHARGE);
        if (eff != null) {
            dam_r *= eff.getDamage() / 100.0;
            bossdam_r *= eff.getDamage() / 100.0;
        }
        eff = chra.getStatForBuff(MapleBuffStat.PICKPOCKET);
        if (eff != null) {
            pickRate = eff.getProb();
        }
        eff = chra.getStatForBuff(MapleBuffStat.DAMAGE_RATE);
        if (eff != null) {
            dam_r *= (eff.getDAMRate() + 100.0) / 100.0;
            bossdam_r *= (eff.getDAMRate() + 100.0) / 100.0;
        }
        eff = chra.getStatForBuff(MapleBuffStat.LIGHTNING_CHARGE);
        if (eff != null) {
            dam_r *= eff.getDamage() / 100.0;
            bossdam_r *= eff.getDamage() / 100.0;
        }
        eff = chra.getStatForBuff(MapleBuffStat.WIND_WALK);
        if (eff != null) {
            dam_r *= eff.getDamage() / 100.0;
            bossdam_r *= eff.getDamage() / 100.0;
        }
        eff = chra.getStatForBuff(MapleBuffStat.DIVINE_SHIELD);
        if (eff != null) {
            watk += eff.getEnhancedWatk();
        }
        buff = chra.getBuffedSkill_Y(MapleBuffStat.DARKSIGHT);
        if (buff != null) {
            dam_r *= (buff.intValue() + 100.0) / 100.0;
            bossdam_r *= (buff.intValue() + 100.0) / 100.0;
        }
        buff = chra.getBuffedSkill_X(MapleBuffStat.ENRAGE);
        if (buff != null) {
            dam_r *= (buff.intValue() + 100.0) / 100.0;
            bossdam_r *= (buff.intValue() + 100.0) / 100.0;
        }
        buff = chra.getBuffedSkill_X(MapleBuffStat.COMBAT_ORDERS);
        if (buff != null) {
            combatOrders += buff.intValue();
        }
        eff = chra.getStatForBuff(MapleBuffStat.SHARP_EYES);
        if (eff != null) {
            crit_rate += eff.getX();
            passive_sharpeye_percent += eff.getCriticalMax();
        }
        eff = chra.getStatForBuff(MapleBuffStat.DIABOLIC_RECOVERY);
        if (eff != null) {
            shouldHealHP += eff.getX() * chra.getStat().getCurrentMaxHp() / 100;
            hpRecoverTime = 4000;
        }
        buff = chra.getBuffedValue(MapleBuffStat.CRITICAL_RATE_BUFF);
        if (buff != null) {
            crit_rate += buff.intValue();
        }
        if (speed > 140) {
            speed = 140;
        }
        if (jump > 123) {
            jump = 123;
        }
        buff = chra.getBuffedValue(MapleBuffStat.MONSTER_RIDING);
        if (buff != null) {
            jump = 120;
            switch (buff.intValue()) {
                case 1:
                    speed = 150;
                    break;
                case 2:
                    speed = 170;
                    break;
                case 3:
                    speed = 180;
                    break;
                default:
                    speed = 200; //lol
                    break;
            }
        }
        eff = chra.getStatForBuff(MapleBuffStat.ATTACK);
        if (eff != null) {
            watk = Integer.MAX_VALUE;
        }
    }

    public boolean checkEquipLevels(final MapleCharacter chr, long gain) {
        if (chr.isClone()) {
            return false;
        }
        boolean changed = false;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        List<Equip> all = new ArrayList<>(equipLevelHandling);
        for (Equip eq : all) {
            int lvlz = eq.getEquipLevel();
            eq.setItemEXP(Math.min(eq.getItemEXP() + gain, Long.MAX_VALUE));

            if (eq.getEquipLevel() > lvlz) { //lvlup
                for (int i = eq.getEquipLevel() - lvlz; i > 0; i--) {
                    //now for the equipment increments...
                    final Map<Integer, Map<String, Integer>> inc = ii.getEquipIncrements(eq.getItemId());
                    int extra = eq.getYggdrasilWisdom();
                    if (extra == 1) {
                        inc.get(lvlz + i).put("STRMin", 1);
                        inc.get(lvlz + i).put("STRMax", 3);
                    } else if (extra == 2) {
                        inc.get(lvlz + i).put("DEXMin", 1);
                        inc.get(lvlz + i).put("DEXMax", 3);
                    } else if (extra == 3) {
                        inc.get(lvlz + i).put("INTMin", 1);
                        inc.get(lvlz + i).put("INTMax", 3);
                    } else if (extra == 4) {
                        inc.get(lvlz + i).put("LUKMin", 1);
                        inc.get(lvlz + i).put("LUKMax", 3);
                    }
                    if (inc != null && inc.containsKey(lvlz + i)) { //flair = 1
                        eq = ii.levelUpEquip(eq, inc.get(lvlz + i));
                    }
                    //UGH, skillz
                    if (GameConstants.getStatFromWeapon(eq.getItemId()) == null && GameConstants.getMaxLevel(eq.getItemId()) < (lvlz + i) && Math.random() < 0.1 && eq.getIncSkill() <= 0 && ii.getEquipSkills(eq.getItemId()) != null) {
                        for (int zzz : ii.getEquipSkills(eq.getItemId())) {
                            final Skill skil = SkillFactory.getSkill(zzz);
                            if (skil != null && skil.canBeLearnedBy(chr.getJob())) { //dont go over masterlevel :D
                                eq.setIncSkill(skil.getId());
                                chr.dropMessage(5, "Your skill has gained a levelup: " + skil.getName() + " +1");
                            }
                        }
                    }
                }
                changed = true;
            }
            chr.forceReAddItem(eq.copy(), MapleInventoryType.EQUIPPED);
        }
        if (changed) {
            chr.equipChanged();
            chr.getClient().getSession().write(EffectPacket.showItemLevelupEffect());
            chr.getMap().broadcastMessage(chr, EffectPacket.showForeignItemLevelupEffect(chr.getId()), false);
        }
        return changed;
    }

    public boolean checkEquipDurabilitys(final MapleCharacter chr, int gain) {
        return checkEquipDurabilitys(chr, gain, false);
    }

    public boolean checkEquipDurabilitys(final MapleCharacter chr, int gain, boolean aboveZero) {
        if (chr.isClone() || chr.inPVP()) {
            return true;
        }
        List<Equip> all = new ArrayList<>(durabilityHandling);
        for (Equip item : all) {
            if (item != null && ((item.getPosition() >= 0) == aboveZero)) {
                item.setDurability(item.getDurability() + gain);
                if (item.getDurability() < 0) { //shouldnt be less than 0
                    item.setDurability(0);
                }
            }
        }
        for (Equip eqq : all) {
            if (eqq != null && eqq.getDurability() == 0 && eqq.getPosition() < 0) { //> 0 went to negative
                if (chr.getInventory(MapleInventoryType.EQUIP).isFull()) {
                    chr.getClient().getSession().write(InventoryPacket.getInventoryFull());
                    chr.getClient().getSession().write(InventoryPacket.getShowInventoryFull());
                    return false;
                }
                durabilityHandling.remove(eqq);
                final short pos = chr.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot();
                MapleInventoryManipulator.unequip(chr.getClient(), eqq.getPosition(), pos);
            } else if (eqq != null) {
                chr.forceReAddItem(eqq.copy(), MapleInventoryType.EQUIPPED);
            }
        }
        return true;
    }

    private void CalcPassive_SharpEye(final MapleCharacter player) {
        Skill critSkill;
        int critlevel;
        if (GameConstants.isDemonSlayer(player.getJob())) {
            critSkill = SkillFactory.getSkill(30010022);
            critlevel = player.getTotalSkillLevel(critSkill);
            if (critlevel > 0) {
                crit_rate += critSkill.getEffect(critlevel).getProb();
                this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
            }
        } else if (GameConstants.isMercedes(player.getJob())) {
            critSkill = SkillFactory.getSkill(20020022);
            critlevel = player.getTotalSkillLevel(critSkill);
            if (critlevel > 0) {
                crit_rate += critSkill.getEffect(critlevel).getProb();
                this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
            }
        } else if (GameConstants.isResist(player.getJob())) {
            critSkill = SkillFactory.getSkill(30000022);
            critlevel = player.getTotalSkillLevel(critSkill);
            if (critlevel > 0) {
                crit_rate += critSkill.getEffect(critlevel).getProb();
                this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
            }
        }
        switch (player.getJob()) { // Apply passive Critical bonus
            case 410: // Assasin
            case 411: // Hermit
            case 412: { // Night Lord
                critSkill = SkillFactory.getSkill(4100001); // Critical Throw
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    crit_rate += (short) (critSkill.getEffect(critlevel).getProb());
                    passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 2412: { // Phantom
                critSkill = SkillFactory.getSkill(24120006);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                    this.watk += critSkill.getEffect(critlevel).getAttackX();
                }
                break;
            }
            case 1410:
            case 1411:
            case 1412: { // Night Walker
                critSkill = SkillFactory.getSkill(14100001);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getProb());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 3100:
            case 3110:
            case 3111:
            case 3112: {
                critSkill = SkillFactory.getSkill(31100006); //TODO LEGEND, not final
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getCr());
                    this.watk += critSkill.getEffect(critlevel).getAttackX();
                }
                break;
            }
            case 2300:
            case 2310:
            case 2311:
            case 2312: {
                critSkill = SkillFactory.getSkill(23000003);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getCr());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 3210:
            case 3211:
            case 3212: {
                critSkill = SkillFactory.getSkill(32100006);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getCr());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 434: {
                critSkill = SkillFactory.getSkill(4340010);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getProb());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 520:
            case 521:
            case 522: {
                critSkill = SkillFactory.getSkill(5200007);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getCr());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 1211:
            case 1212: {
                critSkill = SkillFactory.getSkill(12110000);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getCr());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 530:
            case 531:
            case 532: {
                critSkill = SkillFactory.getSkill(5300004);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getCr());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 510:
            case 511:
            case 512: { // Buccaner, Viper
                critSkill = SkillFactory.getSkill(5110000);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) critSkill.getEffect(critlevel).getProb();
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                //final Skill critSkill2 = SkillFactory.getSkill(5100008);
                // final int critlevel2 = player.getTotalSkillLevel(critSkill);
                //  if (critlevel2 > 0) {
                //     this.passive_sharpeye_rate += (short) critSkill2.getEffect(critlevel2).getCr();
                //     this.passive_sharpeye_min_percent += critSkill2.getEffect(critlevel2).getCriticalMin();
                // }
                return;
            }
            case 1511:
            case 1512: {
                critSkill = SkillFactory.getSkill(15110000);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getProb());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 2111:
            case 2112: {
                critSkill = SkillFactory.getSkill(21110000);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) ((critSkill.getEffect(critlevel).getX() * critSkill.getEffect(critlevel).getY()) + critSkill.getEffect(critlevel).getCr());
                }
                break;
            }
            case 300:
            case 310:
            case 311:
            case 312:
            case 320:
            case 321:
            case 322: { // Bowman
                critSkill = SkillFactory.getSkill(3000001);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getProb());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 1300:
            case 1310:
            case 1311:
            case 1312: { // Bowman
                critSkill = SkillFactory.getSkill(13000000);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getProb());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
            case 2214:
            case 2215:
            case 2216:
            case 2217:
            case 2218: { //Evan
                critSkill = SkillFactory.getSkill(22140000);
                critlevel = player.getTotalSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.crit_rate += (short) (critSkill.getEffect(critlevel).getProb());
                    this.passive_sharpeye_min_percent += critSkill.getEffect(critlevel).getCriticalMin();
                }
                break;
            }
        }
    }

    private void CalcPassive_Mastery(final MapleCharacter player) {
        if (player.getInventory(MapleInventoryType.EQUIPPED).getItem((byte) -11) == null) {
            passive_mastery = 0;
            return;
        }
        final int skil;
        final MapleWeaponType weaponType = GameConstants.getWeaponType(player.getInventory(MapleInventoryType.EQUIPPED).getItem((byte) -11).getItemId());
        boolean acc = true;
        switch (weaponType) {
            case BOW:
                skil = GameConstants.isKOC(player.getJob()) ? 13100000 : 3100000;
                break;
            case CLAW:
                skil = 4100000;
                break;
            case CANE:
                skil = player.getTotalSkillLevel(24120006) > 0 ? 24120006 : 24100004;
                break;
            case CANNON:
                skil = 5300005;
                break;
            case KATARA:
            case DAGGER:
                skil = player.getJob() >= 430 && player.getJob() <= 434 ? 4300000 : 4200000;
                break;
            case CROSSBOW:
                skil = GameConstants.isResist(player.getJob()) ? 33100000 : 3200000;
                break;
            case AXE1H:
            case BLUNT1H:
                skil = GameConstants.isResist(player.getJob()) ? 31100004 : (GameConstants.isKOC(player.getJob()) ? 11100000 : (player.getJob() > 112 ? 1200000 : 1100000)); //hero/pally
                break;
            case AXE2H:
            case SWORD1H:
            case SWORD2H:
            case BLUNT2H:
                skil = GameConstants.isKOC(player.getJob()) ? 11100000 : (player.getJob() > 112 ? 1200000 : 1100000); //hero/pally
                break;
            case POLE_ARM:
                skil = GameConstants.isAran(player.getJob()) ? 21100000 : 1300000;
                break;
            case SPEAR:
                skil = 1300000;
                break;
            case KNUCKLE:
                skil = GameConstants.isKOC(player.getJob()) ? 15100001 : 5100001;
                break;
            case GUN:
                skil = GameConstants.isResist(player.getJob()) ? 35100000 : (GameConstants.isJett(player.getJob()) ? 5700000 : 5200000);
                break;
            case DUAL_BOW:
                skil = 23100005;
                break;
            case WAND:
            case STAFF:
                acc = false;
                skil = GameConstants.isResist(player.getJob()) ? 32100006 : (player.getJob() <= 212 ? 2100006 : (player.getJob() <= 222 ? 2200006 : (player.getJob() <= 232 ? 2300006 : (player.getJob() <= 2000 ? 12100007 : 22120002))));
                break;
            default:
                passive_mastery = 0;
                return;

        }
        if (player.getSkillLevel(skil) <= 0) {
            passive_mastery = 0;
            return;
        }// TODO: add job id check above skill, etc
        final MapleStatEffect eff = SkillFactory.getSkill(skil).getEffect(player.getTotalSkillLevel(skil));
        if (acc) {
            accuracy += eff.getX();
            if (skil == 35100000) {
                watk += eff.getX();
            }
        } else {
            magic += eff.getX();
        }
        crit_rate += eff.getCr();
        passive_mastery = (byte) eff.getMastery();
        trueMastery += eff.getMastery() + weaponType.getBaseMastery();
        if (player.getJob() == 412) {
            final Skill bx = SkillFactory.getSkill(4120012); // Claw Expert
            final int bof = player.getTotalSkillLevel(bx);
            if (bof > 0) {
                final MapleStatEffect eff2 = bx.getEffect(bof);
                passive_mastery = (byte) eff2.getMastery(); // Override
                accuracy += eff2.getPercentAcc();
                dodgeChance += eff2.getPercentAvoid();
                watk += eff2.getX();
                trueMastery -= eff.getMastery(); // - old
                trueMastery += eff2.getMastery(); // add new
            }
        }
    }

    private void calculateFame(final MapleCharacter player) {
        player.getTrait(MapleTraitType.charm).addLocalExp(player.getFame());
        for (MapleTraitType t : MapleTraitType.values()) {
            player.getTrait(t).recalcLevel();
        }
    }

    public final short passive_sharpeye_min_percent() {
        return passive_sharpeye_min_percent;
    }

    public final short passive_sharpeye_percent() {
        return passive_sharpeye_percent;
    }

    public final short passive_sharpeye_rate() {
        return crit_rate;
    }

    public final byte passive_mastery() {
        return passive_mastery; //* 5 + 10 for mastery %
    }

    public final void calculateMaxBaseDamage(final int watk, final int pvpDamage, MapleCharacter chra) {
        if (watk <= 0) {
            localmaxbasedamage = 1;
            localmaxbasepvpdamage = 1;
        } else {
            final Item weapon_item = chra.getInventory(MapleInventoryType.EQUIPPED).getItem((byte) -11);
            final Item weapon_item2 = chra.getInventory(MapleInventoryType.EQUIPPED).getItem((byte) -10);
            final int job = chra.getJob();
            final MapleWeaponType weapon = weapon_item == null ? MapleWeaponType.NOT_A_WEAPON : GameConstants.getWeaponType(weapon_item.getItemId());
            final MapleWeaponType weapon2 = weapon_item2 == null ? MapleWeaponType.NOT_A_WEAPON : GameConstants.getWeaponType(weapon_item2.getItemId());
            int mainstat, secondarystat, mainstatpvp, secondarystatpvp;
            final boolean mage = (job >= 200 && job <= 232) || (job >= 1200 && job <= 1212) || (job >= 2200 && job <= 2218) || (job >= 3200 && job <= 3212 || GameConstants.isLuminous(job));
            switch (weapon) {
                case BOW:
                case CROSSBOW:
                case GUN:
                    mainstat = localdex;
                    secondarystat = localstr;
                    mainstatpvp = dex;
                    secondarystatpvp = str;
                    break;
                case SOUL_SHOOTER:
                    mainstat = localdex;
                    secondarystat = localstr;
                    mainstatpvp = dex;
                    secondarystatpvp = str;
                    break;
                case DAGGER:
                case KATARA:
                case CLAW:
                case CANE:
                    mainstat = localluk;
                    secondarystat = localdex + localstr;
                    mainstatpvp = luk;
                    secondarystatpvp = dex + str;
                    break;
                default:
                    if (mage) {
                        mainstat = localint_;
                        secondarystat = localluk;
                        mainstatpvp = int_;
                        secondarystatpvp = luk;
                    } else {
                        mainstat = localstr;
                        secondarystat = localdex;
                        mainstatpvp = str;
                        secondarystatpvp = dex;
                    }
                    break;
            }
            localmaxbasepvpdamage = weapon.getMaxDamageMultiplier() * (4 * mainstatpvp + secondarystatpvp) * (100.0f + (pvpDamage / 100.0f));
            localmaxbasepvpdamageL = weapon.getMaxDamageMultiplier() * (4 * mainstat + secondarystat) * (100.0f + (pvpDamage / 100.0f));
            localmaxbasedamage = weapon.getMaxDamageMultiplier() * (4 * mainstat + secondarystat) * (watk * 5 / 100.0f);
            //System.out.println("Weapon Multiplier is " + weapon.getMaxDamageMultiplier() + " Main stat and Secondary Stat " + (mainstat + secondarystat) + " Attack " + watk);
        }
    }

    public final float getHealHP() {
        return shouldHealHP;
    }

    public final float getHealMP() {
        return shouldHealMP;
    }
    public final float getHealHPOverTime() {
        return shouldHealHPOverTime;
    }

    public final float getHealMPOverTime() {
        return shouldHealMPOverTime;
    }
    public final float getHealHPFamiliar() {
        return shouldHealHPFamiliar;
    }

    public final float getHealMPFamiliar() {
        return shouldHealMPFamiliar;
    }
    public final void relocHeal(MapleCharacter chra) {
        if (chra.isClone()) {
            return;
        }
        final int playerjob = chra.getJob();
        shouldHealHP = 0;
        shouldHealMP = 0;
        shouldHealHPFamiliar = 0;
        shouldHealMPFamiliar = 0;
        hpRecoverTimeFamiliar = 0;
        mpRecoverTimeFamiliar = 0;
        mpRecoverTime = 0;
        hpRecoverTime = 0;
        InterServerHandler.ElunaBuffTime = 0;
        InterServerHandler.ElunaBuffTime2 = 0;
        InterServerHandler.ElunaBuffTime3 = 0;
        InterServerHandler.ElunaBuffTime4 = 0;
        InterServerHandler.ElunaBuffTime5 = 0;
        InterServerHandler.ElunaBuffTime6 = 0;
        InterServerHandler.ElunaBuffTime7 = 0;
        InterServerHandler.ElunaBuffTime8 = 0;
        shouldHealHPOverTime = 10; // Reset
        shouldHealMPOverTime = GameConstants.isDemonSlayer(chra.getJob()) ? 0 : (3 + recoverMP + (localint_ / 10)); // i think
        if (recoverHPsoc > 0) {
        shouldHealHP += chra.getStat().getCurrentMaxHp() * recoverHPsoc / 100; 
        hpRecoverTime = 4000;
        }
        if (recoverMPsoc > 0) {
        shouldHealMP += chra.getStat().getCurrentMaxMp(chra.getJob()) * recoverMPsoc / 100; 
        mpRecoverTime = 4000;
        }
        if (chra.hasEquipped(1122380)) {
        InterServerHandler.ElunaBuffTime = 600000;    
        }
        if (chra.hasEquipped(1122385)) {
        InterServerHandler.ElunaBuffTime2 = 600000;    
        }
        if (chra.hasEquipped(1122390)) {
        InterServerHandler.ElunaBuffTime3 = 600000;    
        }
        if (chra.hasEquipped(1122395)) {
        InterServerHandler.ElunaBuffTime4 = 600000;    
        }
        if (chra.hasEquipped(1122400)) {
        InterServerHandler.ElunaBuffTime5 = 600000;    
        }
        if (chra.hasEquipped(1122405)) {
        InterServerHandler.ElunaBuffTime6 = 600000;    
        }
        if (chra.hasEquipped(1122410)) {
        InterServerHandler.ElunaBuffTime7 = 600000;    
        }
        if (chra.hasEquipped(1122415)) {
        InterServerHandler.ElunaBuffTime8 = 600000;    
        }
         if (recoverHPNebulite > 0) {
        shouldHealHP += recoverHPNebulite; 
        hpRecoverTime = 4000;
        }
        if (recoverMPNebulite > 0) {
        shouldHealMP += recoverMPNebulite; 
        mpRecoverTime = 4000;
        }
        if (chra.getBuffedValue(MapleBuffStat.HPrecoverFamiliar) != null && chra.getBuffedValue(MapleBuffStat.MPrecoverFamiliar) != null || chra.getBuffedValue(MapleBuffStat.INTERVAL) != null) {
            shouldHealHPFamiliar = getCurrentMaxHp() * chra.getBuffedValue(MapleBuffStat.HPrecoverFamiliar) / 100;
            hpRecoverTimeFamiliar = chra.getBuffedValue(MapleBuffStat.INTERVAL);
            shouldHealMPFamiliar = getCurrentMaxMp(chra.getJob()) * chra.getBuffedValue(MapleBuffStat.MPrecoverFamiliar) / 100;
            mpRecoverTimeFamiliar = hpRecoverTimeFamiliar;
        }
        if (chra.getBuffedValue(MapleBuffStat.HPrecoverFamiliar) != null && chra.getBuffedValue(MapleBuffStat.MPrecoverFamiliar) == null) {
            shouldHealHPFamiliar = getCurrentMaxHp() * chra.getBuffedValue(MapleBuffStat.HPrecoverFamiliar) / 100;
            hpRecoverTimeFamiliar = chra.getBuffedValue(MapleBuffStat.INTERVAL);
        }
        if (chra.getBuffedValue(MapleBuffStat.MPrecoverFamiliar) != null && chra.getBuffedValue(MapleBuffStat.HPrecoverFamiliar) == null) {
            shouldHealMPFamiliar = getCurrentMaxMp(chra.getJob()) * chra.getBuffedValue(MapleBuffStat.MPrecoverFamiliar) / 100;
            mpRecoverTimeFamiliar = chra.getBuffedValue(MapleBuffStat.INTERVAL);
 
        }
        if (playerjob == 111 || playerjob == 112) {
            final Skill effect = SkillFactory.getSkill(1110000); // Improving MP Recovery
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                MapleStatEffect eff = effect.getEffect(lvl);
                if (eff.getHp() > 0) {
                    shouldHealHP += eff.getHp();
                    hpRecoverTime = 4000;
                }
                shouldHealMP += eff.getMp();
                mpRecoverTime = 4000;
            }

        } else if (playerjob == 1111 || playerjob == 1112) {
            final Skill effect = SkillFactory.getSkill(11110000); // Improving MP Recovery
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                shouldHealMP += effect.getEffect(lvl).getMp();
                mpRecoverTime = 4000;
            }
        } else if (GameConstants.isMercedes(playerjob)) {
            final Skill effect = SkillFactory.getSkill(20020109); // Improving MP Recovery
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                shouldHealHP += (effect.getEffect(lvl).getX() * localmaxhp) / 100;
                hpRecoverTime = 4000;
                shouldHealMP += (effect.getEffect(lvl).getX() * localmaxmp) / 100;
                mpRecoverTime = 4000;
            }
        } else if (GameConstants.isJett(playerjob) && playerjob != 508) {
            final Skill effect = SkillFactory.getSkill(5700005); // Perseverance
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                final MapleStatEffect eff = effect.getEffect(lvl);
                shouldHealHP += eff.getX();
                shouldHealMP += eff.getX();
                hpRecoverTime = eff.getY();
                mpRecoverTime = eff.getY();
            }
        } else if (playerjob == 3111 || playerjob == 3112) {
            final Skill effect = SkillFactory.getSkill(31110009); // Improving MP Recovery
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                shouldHealMP += effect.getEffect(lvl).getY();
                mpRecoverTime = 4000;
            }
        }
        if (chra.getChair() != 0) { // Is sitting on a chair.
            shouldHealHP += 99; // Until the values of Chair heal has been fixed,
            shouldHealMP += 99; // MP is different here, if chair data MP = 0, heal + 1.5
        } else if (chra.getMap() != null) { // Because Heal isn't multipled when there's a chair :)
            final float recvRate = chra.getMap().getRecoveryRate();
            if (recvRate > 0) {
                shouldHealHP *= recvRate;
                shouldHealMP *= recvRate;
            }
        }
    }

    public final void connectData(final MaplePacketLittleEndianWriter mplew) {
        mplew.writeShort(str);
        mplew.writeShort(dex);
        mplew.writeShort(int_);
        mplew.writeShort(luk);
        mplew.writeInt(hp);
        mplew.writeInt(maxhp);
        mplew.writeInt(mp);
        mplew.writeInt(maxmp);
    }

    public final void zeroData(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        mplew.writeInt(0);
        mplew.write(0xFF);
        mplew.write(0);
        mplew.writeInt(maxhp);
        mplew.writeInt(maxmp);
        mplew.write(0);
        mplew.writeInt(chr.getSecondHair());
        mplew.writeInt(chr.getSecondFace());
        mplew.writeInt(maxhp);
        mplew.writeInt(maxmp);
    }
    private final static int[] allJobs = {0, 10000, 10000000, 20000000, 20010000, 20020000, 20030000, 20040000, 30000000, 30010000, 50000000};
    public final static int[] pvpSkills = {1000007, 2000007, 3000006, 4000010, 5000006, 5010004, 11000006, 12000006, 13000005, 14000006, 15000005, 21000005, 22000002, 23000004, 31000005, 32000012, 33000004, 35000005};

    public static int getSkillByJob(final int skillID, final int job) { //test
        return skillID + (GameConstants.getBeginnerJob((short) job) * 10000);
    }
    
    public static int getSkillBy4thJob(final int skillID, final int job) { //test
        return skillID + (GameConstants.get4thJob((short) job) * 10000);
    }

    public final int getSkillIncrement(final int skillID) {
        if (skillsIncrement.containsKey(skillID)) {
            return skillsIncrement.get(skillID);
        }
        return 0;
    }

    public final int getElementBoost(final Element key) {
        if (elemBoosts.containsKey(key)) {
            return elemBoosts.get(key);
        }
        return 0;
    }

    public final int getDamageIncrease(final int key) {
        if (damageIncrease.containsKey(key)) {
            return damageIncrease.get(key) + damX;
        }
        return damX;
    }

    public final int getAccuracy() {
        return accuracy;
    }

    public void heal_noUpdate(MapleCharacter chra) {
        setHp(getCurrentMaxHp(), chra);
        setMp(getCurrentMaxMp(chra.getJob()), chra);
    }

    public void heal(MapleCharacter chra) {
        heal_noUpdate(chra);
        chra.updateSingleStat(MapleStat.HP, getCurrentMaxHp());
        chra.updateSingleStat(MapleStat.MP, getCurrentMaxMp(chra.getJob()));
    }

    public Pair<Integer, Integer> handleEquipAdditions(MapleItemInformationProvider ii, MapleCharacter chra, boolean first_login, Map<Skill, SkillEntry> sData, final int itemId) {
        final List<Triple<String, String, String>> additions = ii.getEquipAdditions(itemId);
        if (additions == null) {
            return null;
        }
        int localmaxhp_x = 0, localmaxmp_x = 0;
        int skillid = 0, skilllevel = 0;
        String craft, job, level;
        for (final Triple<String, String, String> add : additions) {
            if (add.getMid().contains("con")) {
                continue;
            }
            final int right = Integer.parseInt(add.getRight());
            switch (add.getLeft()) {
                case "elemboost":
                    craft = ii.getEquipAddReqs(itemId, add.getLeft(), "craft");
                    if (add.getMid().equals("elemVol") && (craft == null || craft != null && chra.getTrait(MapleTraitType.craft).getLocalTotalExp() >= Integer.parseInt(craft))) {
                        int value = Integer.parseInt(add.getRight().substring(1, add.getRight().length()));
                        final Element key = Element.getFromChar(add.getRight().charAt(0));
                        if (elemBoosts.get(key) != null) {
                            value += elemBoosts.get(key);
                        }
                        elemBoosts.put(key, value);
                    }
                    break;
                case "mobcategory": //skip the category, thinkings too expensive to have yet another Map<Integer, Integer> for damage calculations
                    if (add.getMid().equals("damage")) {
                        dam_r *= (right + 100.0) / 100.0;
                        bossdam_r += (right + 100.0) / 100.0;
                    }
                    break;
                case "critical": // lv critical lvl?
                    boolean canJob = false,
                     canLevel = false;
                    job = ii.getEquipAddReqs(itemId, add.getLeft(), "job");
                    if (job != null) {
                        if (job.contains(",")) {
                            final String[] jobs = job.split(",");
                            for (final String x : jobs) {
                                if (chra.getJob() == Integer.parseInt(x)) {
                                    canJob = true;
                                }
                            }
                        } else {
                            if (chra.getJob() == Integer.parseInt(job)) {
                                canJob = true;
                            }
                        }
                    }
                    level = ii.getEquipAddReqs(itemId, add.getLeft(), "level");
                    if (level != null) {
                        if (chra.getLevel() >= Integer.parseInt(level)) {
                            canLevel = true;
                        }
                    }
                    if ((job != null && canJob || job == null) && (level != null && canLevel || level == null)) {
                        switch (add.getMid()) {
                            case "prob":
                                crit_rate += right;
                                break;
                            case "damage":
                                passive_sharpeye_min_percent += right;
                                passive_sharpeye_percent += right; //???CONFIRM - not sure if this is max or minCritDmg
                                break;
                        }
                    }
                    break;
                case "boss": // ignore prob, just add
                    craft = ii.getEquipAddReqs(itemId, add.getLeft(), "craft");
                    if (add.getMid().equals("damage") && (craft == null || craft != null && chra.getTrait(MapleTraitType.craft).getLocalTotalExp() >= Integer.parseInt(craft))) {
                        bossdam_r *= (right + 100.0) / 100.0;
                    }
                    break;
                case "mobdie": // lv, hpIncRatioOnMobDie, hpRatioProp, mpIncRatioOnMobDie, mpRatioProp, modify =D, don't need mob to die
                    craft = ii.getEquipAddReqs(itemId, add.getLeft(), "craft");
                    if ((craft == null || craft != null && chra.getTrait(MapleTraitType.craft).getLocalTotalExp() >= Integer.parseInt(craft))) {
                        switch (add.getMid()) {
                            case "hpIncOnMobDie":
                                hpRecover += right;
                                hpRecoverProp += 5;
                                break;
                            case "mpIncOnMobDie":
                                mpRecover += right;
                                mpRecoverProp += 5;
                                break;
                        }
                    }
                    break;
                case "skill": // all these are additional skills
                    if (first_login) {
                        craft = ii.getEquipAddReqs(itemId, add.getLeft(), "craft");
                        if ((craft == null || craft != null && chra.getTrait(MapleTraitType.craft).getLocalTotalExp() >= Integer.parseInt(craft))) {
                            switch (add.getMid()) {
                                case "id":
                                    skillid = right;
                                    break;
                                case "level":
                                    skilllevel = right;
                                    break;
                            }
                        }
                    }
                    break;
                case "hpmpchange":
                    switch (add.getMid()) {
                        case "hpChangerPerTime":
                            recoverHP += right;
                            break;
                        case "mpChangerPerTime":
                            recoverMP += right;
                            break;
                    }
                    break;
                case "statinc":
                    boolean canJobx = false,
                     canLevelx = false;
                    job = ii.getEquipAddReqs(itemId, add.getLeft(), "job");
                    if (job != null) {
                        if (job.contains(",")) {
                            final String[] jobs = job.split(",");
                            for (final String x : jobs) {
                                if (chra.getJob() == Integer.parseInt(x)) {
                                    canJobx = true;
                                }
                            }
                        } else if (chra.getJob() == Integer.parseInt(job)) {
                            canJobx = true;
                        }
                    }
                    level = ii.getEquipAddReqs(itemId, add.getLeft(), "level");
                    if (level != null && chra.getLevel() >= Integer.parseInt(level)) {
                        canLevelx = true;
                    }
                    if ((!canJobx && job != null) || (!canLevelx && level != null)) {
                        continue;
                    }
                    if (itemId == 1142367) {
                        final int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                        if (day != 1 && day != 7) {
                            continue;
                        }
                    }
                    switch (add.getMid()) {
                        case "incPAD":
                            watk += right;
                            break;
                        case "incMAD":
                            magic += right;
                            break;
                        case "incSTR":
                            localstr += right;
                            break;
                        case "incDEX":
                            localdex += right;
                            break;
                        case "incINT":
                            localint_ += right;
                            break;
                        case "incLUK":
                            localluk += right;
                            break;
                        case "incJump":
                            jump += right;
                            break;
                        case "incMHP":
                            localmaxhp_x += right;
                            break;
                        case "incMMP":
                            localmaxmp_x += right;
                            break;
                        case "incPDD":
                            wdef += right;
                            break;
                        case "incMDD":
                            mdef += right;
                            break;
                        case "incACC":
                            accuracy += right;
                            break;
                        case "incEVA":
                            break;
                        case "incSpeed":
                            speed += right;
                            break;
                        case "incMMPr":
                            percent_mp += right;
                            break;
                    }
                    break;
            }
        }
        if (skillid != 0 && skilllevel != 0) {
            sData.put(SkillFactory.getSkill(skillid), new SkillEntry((byte) skilllevel, (byte) 0, -1));
        }
        return new Pair<>(localmaxhp_x, localmaxmp_x);
    }

    public void handleItemOption(StructItemOption soc, MapleCharacter chra, boolean first_login, Map<Skill, SkillEntry> hmm) {
        localstr += soc.get("incSTR");
        localdex += soc.get("incDEX");
        localint_ += soc.get("incINT");
        localluk += soc.get("incLUK");
        accuracy += soc.get("incACC");
        // incEVA -> increase dodge
        speed += soc.get("incSpeed");
        jump += soc.get("incJump");
        watk += soc.get("incPAD");
        magic += soc.get("incMAD");
        wdef += soc.get("incPDD");
        mdef += soc.get("incMDD");
        percent_str += soc.get("incSTRr");
        percent_dex += soc.get("incDEXr");
        percent_int += soc.get("incINTr");
        percent_luk += soc.get("incLUKr");
        percent_hp += soc.get("incMHPr");
        percent_mp += soc.get("incMMPr");
        percent_acc += soc.get("incACCr");
        dodgeChance += soc.get("incEVAr");
        percent_atk += soc.get("incPADr");
        percent_matk += soc.get("incMADr");
        percent_wdef += soc.get("incPDDr");
        percent_mdef += soc.get("incMDDr");
        crit_rate += soc.get("incCr");
        bossdam_r *= (soc.get("incDAMr") + 100.0) / 100.0;
        if (soc.get("boss") <= 0) {
            dam_r *= (soc.get("incDAMr") + 100.0) / 100.0;
        }
        if (soc.get("AutoStealPotential") > 0) {
        AutoSteal = 1;
        AutoStealprop += soc.get("prop");
        }
        recoverHPsoc += soc.get("RecoveryHP"); // This shouldn't be here, set 4 seconds.
        recoverMPsoc += soc.get("RecoveryMP"); // This shouldn't be here, set 4 seconds.
        if (soc.get("HP") > 0) { // Should be heal upon attacking
            hpRecover += soc.get("HP");
            hpRecoverProp += soc.get("prop");
        }
        if (soc.get("MP") > 0 && !GameConstants.isDemonSlayer(chra.getJob())) {
            mpRecover += soc.get("MP");
            mpRecoverProp += soc.get("prop");
        }
        ignoreTargetDEF += soc.get("ignoreTargetDEF");
        if (soc.get("ignoreDAM") > 0) {
            ignoreDAM += soc.get("ignoreDAM");
            ignoreDAM_rate += soc.get("prop");
        }
        incAllskill += soc.get("incAllskill");
        if (soc.get("ignoreDAMr") > 0) {
            ignoreDAMr = soc.get("ignoreDAMr");
            ignoreDAMr_rate += (soc.get("prop"));
        }
        RecoveryUP += soc.get("RecoveryUP"); // only for hp items and skills
        passive_sharpeye_min_percent += soc.get("incCriticaldamageMin");
        passive_sharpeye_percent += soc.get("incCriticaldamageMax");
        TER += soc.get("incTerR"); // elemental resistance = avoid element damage from monster
        ASR += soc.get("incAsrR"); // abnormal status = disease
        if (soc.get("DAMreflect") > 0) {
            DAMreflect += soc.get("DAMreflect");
            DAMreflect_rate += soc.get("prop");
        }
        if (soc.get("attackType") == 1000) {
        PoisonPotential += soc.get("attackType");
        PoisonPotentialprop += soc.get("prop");
        PoisonLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1001) {
        StunPotential += soc.get("attackType");
        StunPotentialprop += soc.get("prop");
        StunLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1002) {
        SlowPotential += soc.get("attackType");
        SlowPotentialprop += soc.get("prop");
        SlowLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1003) {
        BlindPotential += soc.get("attackType");
        BlindPotentialprop += soc.get("prop");
        BlindLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1004) {
        FreezePotential += soc.get("attackType");
        FreezePotentialprop += soc.get("prop");
        FreezeLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1005) {
        SealPotential += soc.get("attackType");
        SealPotentialprop += soc.get("prop");
        SealLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1010) {
        BurnPotential += soc.get("attackType");
        BurnPotentialprop += soc.get("prop");
        BurnLevel += soc.get("level");
        }
        mpconReduce += soc.get("mpconReduce");
        reduceCooltime += soc.get("reduceCooltime"); // in seconds
        incMesoProp += soc.get("incMesoProp"); // mesos + %
        dropBuff += (soc.get("incRewardProp")); // extra drop rate for item
        if (first_login && soc.get("skillID") > 0) {
            hmm.put(SkillFactory.getSkill(getSkillByJob(soc.get("skillID"), chra.getJob())), new SkillEntry((byte) 1, (byte) 0, -1));
        }
        bossdam_r *= (soc.get("bdR") + 100.0) / 100.0;
        ignoreTargetDEF *= (soc.get("imdR") + 100.0) / 100.0;
        // TODO: Auto Steal potentials (modify handleSteal), potentials with invincible stuffs, abnormal status duration decrease,
        // poison, stun, etc (uses level field -> cast disease to mob/player), face?
    }
    
    public void handleItemOptions(StructItemOption soc, MapleCharacter chra, boolean first_login, Map<Skill, SkillEntry> hmm) {
        localstr += soc.get("incSTR");
        localdex += soc.get("incDEX");
        localint_ += soc.get("incINT");
        localluk += soc.get("incLUK");
        accuracy += soc.get("incACC");
        // incEVA -> increase dodge
        speed += soc.get("incSpeed");
        jump += soc.get("incJump");
        watk += soc.get("incPAD");
        magic += soc.get("incMAD");
        wdef += soc.get("incPDD");
        mdef += soc.get("incMDD");
        percent_str += soc.get("incSTRr");
        percent_dex += soc.get("incDEXr");
        percent_int += soc.get("incINTr");
        percent_luk += soc.get("incLUKr");
        percent_hp += soc.get("incMHPr");
        percent_mp += soc.get("incMMPr");
        percent_acc += soc.get("incACCr");
        dodgeChance += soc.get("incEVAr");
        percent_atk += soc.get("incPADr");
        percent_matk += soc.get("incMADr");
        percent_wdef += soc.get("incPDDr");
        percent_mdef += soc.get("incMDDr");
        crit_rate += soc.get("incCr");
        bossdam_r *= (soc.get("incDAMr") + 100.0) / 100.0;
        if (soc.get("boss") <= 0) {
            dam_r *= (soc.get("incDAMr") + 100.0) / 100.0;
        }
        recoverHPsoc += soc.get("RecoveryHP"); // This shouldn't be here, set 4 seconds.
        recoverMPsoc += soc.get("RecoveryMP"); // This shouldn't be here, set 4 seconds.
        if (soc.get("HP") > 0) { // Should be heal upon attacking
            hpRecover += soc.get("HP");
            hpRecoverProp += soc.get("prop");
        }
        if (soc.get("MP") > 0 && !GameConstants.isDemonSlayer(chra.getJob())) {
            mpRecover += soc.get("MP");
            mpRecoverProp += soc.get("prop");
        }
        ignoreTargetDEF += soc.get("ignoreTargetDEF");
        if (soc.get("ignoreDAM") > 0) {
            ignoreDAM += soc.get("ignoreDAM");
            ignoreDAM_rate += soc.get("prop");
        }
        incAllskill += soc.get("incAllskill");
        if (soc.get("ignoreDAMr") > 0) {
            ignoreDAMr = soc.get("ignoreDAMr");
            ignoreDAMr_rate += (soc.get("prop"));
        }
        if (soc.get("AutoStealPotential") > 0) {
        AutoSteal = 1;
        AutoStealprop += soc.get("prop");
        }
        RecoveryUP += soc.get("RecoveryUP"); // only for hp items and skills
        passive_sharpeye_min_percent += soc.get("incCriticaldamageMin");
        passive_sharpeye_percent += soc.get("incCriticaldamageMax");
        TER += soc.get("incTerR"); // elemental resistance = avoid element damage from monster
        ASR += soc.get("incAsrR"); // abnormal status = disease
        if (soc.get("DAMreflect") > 0) {
            DAMreflect += soc.get("DAMreflect");
            DAMreflect_rate += soc.get("prop");
        }
        if (soc.get("attackType") == 1000) {
        PoisonPotential += soc.get("attackType");
        PoisonPotentialprop += soc.get("prop");
        PoisonLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1001) {
        StunPotential += soc.get("attackType");
        StunPotentialprop += soc.get("prop");
        StunLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1002) {
        SlowPotential += soc.get("attackType");
        SlowPotentialprop += soc.get("prop");
        SlowLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1003) {
        BlindPotential += soc.get("attackType");
        BlindPotentialprop += soc.get("prop");
        BlindLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1004) {
        FreezePotential += soc.get("attackType");
        FreezePotentialprop += soc.get("prop");
        FreezeLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1005) {
        SealPotential += soc.get("attackType");
        SealPotentialprop += soc.get("prop");
        SealLevel += soc.get("level");
        }
        if (soc.get("attackType") == 1010) {
        BurnPotential += soc.get("attackType");
        BurnPotentialprop += soc.get("prop");
        BurnLevel += soc.get("level");
        }
        mpconReduce += soc.get("mpconReduce");
        reduceCooltime += soc.get("reduceCooltime"); // in seconds
        incMesoProp += soc.get("incMesoProp"); // mesos + %
        dropBuff += (soc.get("incRewardProp")); // extra drop rate for item
        if (first_login && soc.get("skillID") > 0) {
            hmm.put(SkillFactory.getSkill(getSkillByJob(soc.get("skillID"), chra.getJob())), new SkillEntry((byte) 1, (byte) 0, -1));
        }
        bossdam_r *= (soc.get("bdR") + 100.0) / 100.0;
        ignoreTargetDEF *= (soc.get("imdR") + 100.0) / 100.0;
        // TODO: Auto Steal potentials (modify handleSteal), potentials with invincible stuffs, abnormal status duration decrease,
        // poison, stun, etc (uses level field -> cast disease to mob/player), face?
    }

    public final void handleProfessionTool(final MapleCharacter chra) {
        if (chra.getProfessionLevel(92000000) > 0 || chra.getProfessionLevel(92010000) > 0) {
            final Iterator<Item> itera = chra.getInventory(MapleInventoryType.EQUIP).newList().iterator();
            while (itera.hasNext()) { //goes to first harvesting tool and stops
                final Equip equip = (Equip) itera.next();
                if (equip.getDurability() != 0 && (equip.getItemId() / 10000 == 150 && chra.getProfessionLevel(92000000) > 0) || (equip.getItemId() / 10000 == 151 && chra.getProfessionLevel(92010000) > 0)) {
                    if (equip.getDurability() > 0) {
                        durabilityHandling.add(equip);
                    }
                    harvestingTool = equip.getPosition();
                    break;
                }
            }
        }
    }

    public void recalcPVPRank(MapleCharacter chra) {
        this.pvpRank = 10;
        this.pvpExp = chra.getTotalBattleExp();
        for (int i = 0; i < 10; i++) {
            if (pvpExp > GameConstants.getPVPExpNeededForLevel(i + 1)) {
                pvpRank--;
                pvpExp -= GameConstants.getPVPExpNeededForLevel(i + 1);
            }
        }
    }

    public int getHPPercent() {
        return (int) Math.ceil((hp * 100.0) / localmaxhp);
    }

    public final void init(MapleCharacter chra) {
        recalcLocalStats(chra);
    }

    public final int getStr() {
        return str;
    }

    public final int getDex() {
        return dex;
    }

    public final int getInt() {
        return int_;
    }

    public final int getLuk() {
        return luk;
    }

    public final int getHp() {
        return hp;
    }

    public final int getMp() {
        return mp;
    }

    public final int getMaxHp() {
        return maxhp;
    }

    public final int getMaxMp() {
        return maxmp;
    }

    public final void setStr(final short str, MapleCharacter chra) {
        this.str = str;
        recalcLocalStats(chra);
    }

    public final void setDex(final short dex, MapleCharacter chra) {
        this.dex = dex;
        recalcLocalStats(chra);
    }

    public final void setInt(final short int_, MapleCharacter chra) {
        this.int_ = int_;
        recalcLocalStats(chra);
    }

    public final void setLuk(final short luk, MapleCharacter chra) {
        this.luk = luk;
        recalcLocalStats(chra);
    }

    public final boolean setHp(final int newhp, MapleCharacter chra) {
        return setHp(newhp, false, chra);
    }

    public final boolean setHp(int newhp, boolean silent, MapleCharacter chra) {
        final int oldHp = hp;
        int thp = newhp;
        if (thp < 0) {
            thp = 0;
        }
        if (thp > localmaxhp) {
            thp = localmaxhp;
        }
        this.hp = thp;

        if (chra != null) {
            if (!silent) {
                chra.checkBerserk();
                chra.updatePartyMemberHP();
            }
            if (oldHp > hp && !chra.isAlive()) {
                chra.playerDead();
            }
        }
        if (GameConstants.isDemonAvenger(chra.getJob())) {
            chra.getClient().getSession().write(JobPacket.AvengerPacket.giveAvengerHpBuff(hp/2));
        }
        return hp != oldHp;
    }

    public final boolean setMp(final int newmp, final MapleCharacter chra) {
        final int oldMp = mp;
        int tmp = newmp;
        if (tmp < 0) {
            tmp = 0;
        }
        if (tmp > localmaxmp) {
            tmp = localmaxmp;
        }
        this.mp = tmp;
        return mp != oldMp;
    }

    public final void setMaxHp(final int hp, MapleCharacter chra) {
        this.maxhp = hp;
        recalcLocalStats(chra);
    }
    

    public final void setMaxMp(final int mp, MapleCharacter chra) {
        this.maxmp = mp;
        recalcLocalStats(chra);
    }

    public final void setInfo(final int maxhp, final int maxmp, final int hp, final int mp) {
        this.maxhp = maxhp;
        this.maxmp = maxmp;
        this.hp = hp;
        this.mp = mp;
    }

    public final int getTotalStr() {
        return localstr;
    }
    
    public final int getExpBuff() {
        return (int) expBuff;
    }
    
    public final int getQuestExpBuff() {
        return (int) QuestExpBuff;
    }
     
    public final int getMesoBuff() {
        return (int) mesoBuff;
    }
    
    public final int getDropBuff() {
        return (int) dropBuff;
    }
     
    public final int getTotalDex() {
        return localdex;
    }

    public final int getTotalInt() {
        return localint_;
    }

    public final int getTotalLuk() {
        return localluk;
    }

    public final int getTotalWatk() {
        return watk;
    }

    public final int getTotalMagic() {
        return magic;
    }

    public final int getCurrentMaxHp() {
        return localmaxhp;
    }

    public final int getCurrentMaxMp(final int job) {
        if (GameConstants.isDemonSlayer(job)) {
            return DemonMP;
        }
        else if (GameConstants.isZero(job)) {
            localmaxmp = 100;
        }
        return localmaxmp;
    }

    public final int getHands() {
        return hands; // Only used for stimulator/maker skills
    }

    public final float getCurrentMaxBaseDamage() {
        return localmaxbasedamage;
    }

    public final float getCurrentMaxBasePVPDamage() {
        return localmaxbasepvpdamage;
    }

    public final float getCurrentMaxBasePVPDamageL() {
        return localmaxbasepvpdamageL;
    }

    public final boolean isRangedJob(final int job) {
        return GameConstants.isJett(job) || GameConstants.isMercedes(job) || GameConstants.isCannon(job) || job == 400 || (job / 10 == 52) || (job / 100 == 3) || (job / 100 == 13) || (job / 100 == 14) || (job / 100 == 33) || (job / 100 == 35) || (job / 10 == 41);
    }
     public final int u(int variable){
        return (int) Math.ceil(variable);
    }
    
    public final int d(int variable){
        return (int) Math.floor(variable);
    }

}
