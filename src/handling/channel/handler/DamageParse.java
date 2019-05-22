package handling.channel.handler;

import client.MapleBuffStat;
import client.MapleCharacter;
import client.MapleQuestStatus;
import client.MonsterStatus;
import client.MonsterStatusEffect;
import client.PlayerStats;
import client.Skill;
import client.SkillFactory;
import client.anticheat.CheatTracker;
import client.anticheat.CheatingOffense;
//import client.inventory.Equip;
import client.inventory.Item;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
//import com.sun.media.sound.SF2Modulator;
import constants.GameConstants;
import constants.ServerConstants;
import handling.world.MaplePartyCharacter;
import handling.world.World;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MapleStatEffect;
import server.Randomizer;
import server.StructItemOption;
import server.life.Element;
import server.life.ElementalEffectiveness;
import server.life.MapleMonster;
import server.life.MapleMonsterStats;
import server.maps.MapleMap;
import server.maps.MapleMapItem;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.movement.LifeMovementFragment;
import server.quest.MapleQuest;
import tools.AttackPair;
import static tools.HexTool.getByteArrayFromHexString;
import tools.Pair;
import tools.Triple;
import tools.data.LittleEndianAccessor;
import tools.packet.CField;
import tools.packet.CWvsContext;
import tools.packet.JobPacket;
import static handling.channel.handler.InterServerHandler.PickAll;

public class DamageParse {
public static EnumMap<MapleBuffStat, Integer> statups;
public static MapleStatEffect c;
    @SuppressWarnings("empty-statement")
    public static void applyAttack(AttackInfo attack, Skill theSkill, MapleCharacter player, int attackCount, double maxDamagePerMonster, MapleStatEffect effect, AttackType attack_type) {
        if (!player.isAlive()) {
            player.getCheatTracker().registerOffense(CheatingOffense.ATTACKING_WHILE_DEAD);
            return;
        }
        if ((attack.real) && (GameConstants.getAttackDelay(attack.skill, theSkill) >= 100)) {
            player.getCheatTracker().checkAttack(attack.skill, attack.lastAttackTickCount);
        }
        if (attack.skill != 0) {
            if (effect == null) {
                player.getClient().getSession().write(CWvsContext.enableActions());
                return;
            }
            if (GameConstants.isMulungSkill(attack.skill)) {
                if (player.getMapId() / 10000 != 92502) {
                    return;
                }
                if (player.getMulungEnergy() < 10000) {
                    return;
                }
                player.mulung_EnergyModify(false);
            } else if (GameConstants.isPyramidSkill(attack.skill)) {
                if (player.getMapId() / 1000000 != 926) {
                    return;
                }

                //if ((player.getPyramidSubway() != null) && (player.getPyramidSubway().onSkillUse(player)));
            } /*else if (GameConstants.isInflationSkill(attack.skill)) {
                if (player.getBuffedValue(MapleBuffStat.GIANT_POTION) != null);
            } else if ((attack.targets > effect.getMobCount()) && (attack.skill != 1211002) && (attack.skill != 1220010)) {
                player.getCheatTracker().registerOffense(CheatingOffense.MISMATCHING_BULLETCOUNT);
                return;
            }*/
        }
        if (player.getClient().getChannelServer().isAdminOnly()) {
            player.dropMessage(-1, new StringBuilder().append("Animation: ").append(Integer.toHexString((attack.display & 0x8000) != 0 ? attack.display - 32768 : attack.display)).toString());
        }
        boolean useAttackCount = (attack.skill != 4211006) && (attack.skill != 3221007) && (attack.skill != 23121003) && ((attack.skill != 1311001) || (player.getJob() != 132)) && (attack.skill != 3211006);
        if ((attack.hits > 0) && (attack.targets > 0)) {
            if (!player.getStat().checkEquipDurabilitys(player, -1)) {
                player.dropMessage(5, "An item has run out of durability but has no inventory room to go to.");
                return;
            }
        }
        int totDamage = 0;
        MapleMap map = player.getMap();
        if (attack.skill == 4211006) {
            for (AttackPair oned : attack.allDamage) {
                if (oned.attack == null) {
                    MapleMapObject mapobject = map.getMapObject(oned.objectid, MapleMapObjectType.ITEM);

                    if (mapobject != null) {
                        MapleMapItem mapitem = (MapleMapItem) mapobject;
                        mapitem.getLock().lock();
                        try {
                            if (mapitem.getMeso() > 0) {
                                if (mapitem.isPickedUp()) {
                                    return;
                                }
                                map.removeMapObject(mapitem);
                                map.broadcastMessage(CField.explodeDrop(mapitem.getObjectId()));
                                mapitem.setPickedUp(true);
                            } else {
                                player.getCheatTracker().registerOffense(CheatingOffense.ETC_EXPLOSION);
                                return;
                            }
                        } finally {
                            mapitem.getLock().unlock();
                        }
                    } else {
                        player.getCheatTracker().registerOffense(CheatingOffense.EXPLODING_NONEXISTANT);
                        return;
                    }
                }
            }
        }
        int totDamageToOneMonster = 0;
        long hpMob = 0L;
        PlayerStats stats = player.getStat();

        int CriticalDamage = stats.passive_sharpeye_percent();
        int ShdowPartnerAttackPercentage = 0;
        if ((attack_type == AttackType.RANGED_WITH_SHADOWPARTNER) || (attack_type == AttackType.NON_RANGED_WITH_MIRROR)) {
            MapleStatEffect shadowPartnerEffect = player.getStatForBuff(MapleBuffStat.SHADOWPARTNER);
            if (shadowPartnerEffect != null) {
                ShdowPartnerAttackPercentage += shadowPartnerEffect.getX();
            }
            attackCount /= 2;
        }
        ShdowPartnerAttackPercentage *= (CriticalDamage + 100) / 100;
        if (attack.skill == 4221001) {
            ShdowPartnerAttackPercentage *= 10;
        }

        double maxDamagePerHit = 0.0D;

        // int antiKS = 0;
        byte overallAttackCount = 0;
        for (AttackPair oned : attack.allDamage) {
            MapleMonster monster = map.getMonsterByOid(oned.objectid);
            if ((monster != null) && (monster.getLinkCID() <= 0)) {
                totDamageToOneMonster = 0;
                hpMob = monster.getMobMaxHp();
                MapleMonsterStats monsterstats = monster.getStats();
                int fixeddmg = monsterstats.getFixedDamage();
                boolean Tempest = (monster.getStatusSourceID(MonsterStatus.FREEZE) == 21120006) || (attack.skill == 21120006) || (attack.skill == 1221011);

                /*if ((!Tempest) && (!player.isGM())) {
                    if (((player.getJob() >= 3200) && (player.getJob() <= 3212) && (!monster.isBuffed(MonsterStatus.DAMAGE_IMMUNITY)) && (!monster.isBuffed(MonsterStatus.MAGIC_IMMUNITY)) && (!monster.isBuffed(MonsterStatus.MAGIC_DAMAGE_REFLECT))) || (attack.skill == 3221007) || (attack.skill == 23121003) || (((player.getJob() < 3200) || (player.getJob() > 3212)) && (!monster.isBuffed(MonsterStatus.DAMAGE_IMMUNITY)) && (!monster.isBuffed(MonsterStatus.WEAPON_IMMUNITY)) && (!monster.isBuffed(MonsterStatus.WEAPON_DAMAGE_REFLECT)))) {
                        maxDamagePerHit = CalculateMaxWeaponDamagePerHit(player, monster, attack, theSkill, effect, maxDamagePerMonster, Integer.valueOf(CriticalDamage));
                    } else {
                        maxDamagePerHit = 1.0D;
                    }
                }*/

                int criticals = 0;
                for (Pair eachde : oned.attack) {
                    Integer eachd = (Integer) eachde.left;
                    
                    if (((Boolean) eachde.right)) {
                        criticals++;
                    }
                    if ((useAttackCount) && (overallAttackCount - 1 == attackCount)) {
                        maxDamagePerHit = maxDamagePerHit / 100.0D * (ShdowPartnerAttackPercentage * (monsterstats.isBoss() ? stats.bossdam_r : stats.dam_r) / 100.0D);
                    }

                    if (fixeddmg != -1) {
                        if (monsterstats.getOnlyNoramlAttack()) {
                            eachd = attack.skill != 0 ? 0 : fixeddmg;
                        } else {
                            eachd = fixeddmg;
                        }
                    } else if (monsterstats.getOnlyNoramlAttack()) {
                        eachd = Integer.valueOf(attack.skill != 0 ? 0 : Math.min(eachd.intValue(), (int) maxDamagePerHit));
                    } /*else if (!player.isGM()) {
                        if (Tempest) {
                            if (eachd.intValue() > monster.getMobMaxHp()) {
                                eachd = Integer.valueOf((int) Math.min(monster.getMobMaxHp(), 2147483647L));
                                player.getCheatTracker().registerOffense(CheatingOffense.HIGH_DAMAGE);
                            }
                        } else if (((player.getJob() >= 3200) && (player.getJob() <= 3212) && (!monster.isBuffed(MonsterStatus.DAMAGE_IMMUNITY)) && (!monster.isBuffed(MonsterStatus.MAGIC_IMMUNITY)) && (!monster.isBuffed(MonsterStatus.MAGIC_DAMAGE_REFLECT))) || (attack.skill == 23121003) || (((player.getJob() < 3200) || (player.getJob() > 3212)) && (!monster.isBuffed(MonsterStatus.DAMAGE_IMMUNITY)) && (!monster.isBuffed(MonsterStatus.WEAPON_IMMUNITY)) && (!monster.isBuffed(MonsterStatus.WEAPON_DAMAGE_REFLECT)))) {
                            if (eachd.intValue() > maxDamagePerHit) {
                                player.getCheatTracker().registerOffense(CheatingOffense.HIGH_DAMAGE, new StringBuilder().append("[Damage: ").append(eachd).append(", Expected: ").append(maxDamagePerHit).append(", Mob: ").append(monster.getId()).append("] [Job: ").append(player.getJob()).append(", Level: ").append(player.getLevel()).append(", Skill: ").append(attack.skill).append("]").toString());
                                if (attack.real) {
                                    player.getCheatTracker().checkSameDamage(eachd.intValue(), maxDamagePerHit);
                                }
                                if (eachd.intValue() > maxDamagePerHit * 2.0D) {
                                    player.getCheatTracker().registerOffense(CheatingOffense.HIGH_DAMAGE_2, new StringBuilder().append("[Damage: ").append(eachd).append(", Expected: ").append(maxDamagePerHit).append(", Mob: ").append(monster.getId()).append("] [Job: ").append(player.getJob()).append(", Level: ").append(player.getLevel()).append(", Skill: ").append(attack.skill).append("]").toString());
                                    eachd = Integer.valueOf((int) (maxDamagePerHit * 2.0D));
                                    if (eachd.intValue() >= 2499999) {
                                        player.getClient().getSession().close();
                                    }
                                }
                            }

                        } else if (eachd.intValue() > maxDamagePerHit) {
                            eachd = Integer.valueOf((int) maxDamagePerHit);
                        }

                    }*/

                    if (player == null) {
                        return;
                    }
                    totDamageToOneMonster += eachd.intValue();

                    if (((eachd.intValue() == 0) || (monster.getId() == 9700021)) && (player.getPyramidSubway() != null)) {
                        player.getPyramidSubway().onMiss(player);
                    }
                }
                totDamage += totDamageToOneMonster;
                player.checkMonsterAggro(monster);
/*
                if ((GameConstants.getAttackDelay(attack.skill, theSkill) >= 100) && (!GameConstants.isNoDelaySkill(attack.skill)) && (attack.skill != 3101005) && (!monster.getStats().isBoss()) && (player.getTruePosition().distanceSq(monster.getTruePosition()) > GameConstants.getAttackRange(effect, player.getStat().defRange))) {
                    player.getCheatTracker().registerOffense(CheatingOffense.ATTACK_FARAWAY_MONSTER, new StringBuilder().append("[Distance: ").append(player.getTruePosition().distanceSq(monster.getTruePosition())).append(", Expected Distance: ").append(GameConstants.getAttackRange(effect, player.getStat().defRange)).append(" Job: ").append(player.getJob()).append("]").toString());
                }
*/
                if (player.getSkillLevel(36110005) > 0) {
                    Skill skill = SkillFactory.getSkill(36110005);
                    MapleStatEffect eff = skill.getEffect(player.getSkillLevel(skill));
                    if (player.getLastCombo() + 5000 < System.currentTimeMillis()) {
                        monster.setTriangulation(0);
                        //player.clearDamageMeters();
                    }
                    if (eff.makeChanceResult()) {
                        player.setLastCombo(System.currentTimeMillis());
                        if (monster.getTriangulation() < 3) {
                            monster.setTriangulation(monster.getTriangulation() + 1);
                        }
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.DARKNESS, eff.getX(), eff.getSourceId(), null, false), false, eff.getY() * 1000, true, eff);
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.TRIANGULATION, monster.getTriangulation(), eff.getSourceId(), null, false), false, eff.getY() * 1000, true, eff);
                    }
                }

                if (player.getBuffedValue(MapleBuffStat.PICKPOCKET) != null) {
                    switch (attack.skill) {
                        case 0:
                        case 4001334:
                        case 4201005:
                        case 4211002:
                        case 4211004:
                        case 4221003:
                        case 4221007:
                            handlePickPocket(player, monster, oned);
                    }

                }

                if ((totDamageToOneMonster > 0) || (attack.skill == 1221011) || (attack.skill == 21120006)) {
                    if ((GameConstants.isPhantom(player.getJob())) && (attack.skill != 24120002) && (attack.skill != 24100003)) {
                        player.handleCardStack();
                    }
                    if (GameConstants.isKaiser(player.getJob())) {
                        player.handleKaiserCombo();
                    }
                    if (monster.isBuffed(MonsterStatus.WEAPON_DAMAGE_REFLECT) && !monster.getStats().isElite()) {
                        player.addHP(-(7000 + Randomizer.nextInt(8000)));
                    }
                    if (monster.isBuffed(MonsterStatus.WEAPON_DAMAGE_REFLECT) && monster.getStats().isElite()) {
                        player.addHP(- 3 * player.getStat().getCurrentMaxHp() / 100);
                    }
                    player.onAttack(monster.getMobMaxHp(), monster.getMobMaxMp(), attack.skill, monster.getObjectId(), totDamage, 0);
                    if (GameConstants.isDemonSlayer(player.getJob())) {
                        player.handleForceGain(monster.getObjectId(), attack.skill);
                    }
                    switch (attack.skill) {
                        case 4001002:
                        case 4001334:
                        case 4001344:
                        case 4111005:
                        case 4121007:
                        case 4201005:
                        case 4211002:
                        case 4221001:
                        case 4221007:
                        case 4301001:
                        case 4311002:
                        case 4311003:
                        case 4331000:
                        case 4331004:
                        case 4331005:
                        case 4331006:
                        case 4341002:
                        case 4341004:
                        case 4341005:
                        case 4341009:
                        case 14001004:
                        case 14111002:
                        case 14111005:
                            int[] skills = {4120005, 4220005, 4340001, 14110004};
                            for (int i : skills) {
                                Skill skill = SkillFactory.getSkill(i);
                                if (player.getTotalSkillLevel(skill) > 0) {
                                    MapleStatEffect venomEffect = skill.getEffect(player.getTotalSkillLevel(skill));
                                    if (!venomEffect.makeChanceResult()) {
                                        break;
                                    }
                                    monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.POISON, Integer.valueOf(1), i, null, false), true, venomEffect.getDuration(), true, venomEffect);
                                    break;
                                }

                            }

                            break;
                        case 4201004:
                            monster.handleSteal(player);
                            break;
                        case 21000002:
                        case 21100001:
                        case 21100002:
                        case 21100004:
                        case 21110002:
                        case 21110003:
                        case 21110004:
                        case 21110006:
                        case 21110007:
                        case 21110008:
                        case 21120002:
                        case 21120005:
                        case 21120006:
                        case 21120009:
                        case 21120010:
                            if ((player.getBuffedValue(MapleBuffStat.WK_CHARGE) != null) && (!monster.getStats().isBoss())) {
                                MapleStatEffect eff = player.getStatForBuff(MapleBuffStat.WK_CHARGE);
                                if (eff != null) {
                                    monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SPEED, Integer.valueOf(eff.getX()), eff.getSourceId(), null, false), false, eff.getY() * 1000, true, eff);
                                }
                            }
                            if ((player.getBuffedValue(MapleBuffStat.BODY_PRESSURE) != null) && (!monster.getStats().isBoss())) {
                                MapleStatEffect eff = player.getStatForBuff(MapleBuffStat.BODY_PRESSURE);

                                if ((eff != null) && (eff.makeChanceResult()) && (!monster.isBuffed(MonsterStatus.NEUTRALISE))) {
                                    monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.NEUTRALISE, Integer.valueOf(1), eff.getSourceId(), null, false), false, eff.getX() * 1000, true, eff);
                                }
                            }
                            break;
                    }
                    if (totDamageToOneMonster > 0) {
                        Item weapon_ = player.getInventory(MapleInventoryType.EQUIPPED).getItem((short) -11);
                        if (weapon_ != null) {
                            MonsterStatus stat = GameConstants.getStatFromWeapon(weapon_.getItemId());
                            if ((stat != null) && (Randomizer.nextInt(100) < GameConstants.getStatChance())) {
                                MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(stat, Integer.valueOf(GameConstants.getXForStat(stat)), GameConstants.getSkillForStat(stat), null, false);
                                monster.applyStatus(player, monsterStatusEffect, false, 10000L, false, null);
                            }
                        }
                        if (player.getBuffedValue(MapleBuffStat.BLIND) != null) {
                            MapleStatEffect eff = player.getStatForBuff(MapleBuffStat.BLIND);

                            if ((eff != null) && (eff.makeChanceResult())) {
                                MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(MonsterStatus.ACC, Integer.valueOf(eff.getX()), eff.getSourceId(), null, false);
                                monster.applyStatus(player, monsterStatusEffect, false, eff.getY() * 1000, true, eff);
                            }
                        }

                        if (player.getBuffedValue(MapleBuffStat.HAMSTRING) != null) {
                            MapleStatEffect eff = player.getStatForBuff(MapleBuffStat.HAMSTRING);

                            if ((eff != null) && (eff.makeChanceResult())) {
                                MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(MonsterStatus.SPEED, Integer.valueOf(eff.getX()), 3121007, null, false);
                                monster.applyStatus(player, monsterStatusEffect, false, eff.getY() * 1000, true, eff);
                            }
                        }
                        if ((player.getJob() == 121) || (player.getJob() == 122)) {
                            Skill skill = SkillFactory.getSkill(1211006);
                            if (player.isBuffFrom(MapleBuffStat.WK_CHARGE, skill)) {
                                MapleStatEffect eff = skill.getEffect(player.getTotalSkillLevel(skill));
                                MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(MonsterStatus.FREEZE, Integer.valueOf(1), skill.getId(), null, false);
                                monster.applyStatus(player, monsterStatusEffect, false, eff.getY() * 2000, true, eff);
                            }
                        }
                    }
                    if ((effect != null) && (effect.getMonsterStati().size() > 0) && (effect.makeChanceResult())) {
                        for (Map.Entry z : effect.getMonsterStati().entrySet()) {
                            monster.applyStatus(player, new MonsterStatusEffect((MonsterStatus) z.getKey(), (Integer) z.getValue(), theSkill.getId(), null, false), effect.isPoison(), effect.getDuration(), true, effect);
                        }
                    }
                }
            }
            if (stats.AutoStealprop > 0) {
            monster.handleSteal(player);
            }
            if (stats.PoisonPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.PoisonPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(2111003);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.POISON, 1, 2111003, null, false), true, venomEffect.getDOTTime(), true, venomEffect);
                    }
                }
            }
            if (stats.BurnPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.BurnPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(12111005);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill) + 20);
                    if (monster != null) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.POISON, 1, 12111005, null, false), true, venomEffect.getDOTTime(), true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************
            if (stats.StunPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.StunPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(1111008);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.StunLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.STUN, 1, 1111008, null, false), false, venomEffect.getDuration() / 4, true, venomEffect);
                    }
                    if (monster != null && stats.StunLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.STUN, 1, 1111008, null, false), false, venomEffect.getDuration() * 3 / 4, true, venomEffect);
                    }
                    if (monster != null && stats.StunLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.STUN, 1, 1111008, null, false), false, venomEffect.getDuration() * 5 / 4, true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************      
            if (stats.FreezePotential > 0) {
                if (Randomizer.nextInt(100) <= stats.FreezePotentialprop) {
                    final Skill skill = SkillFactory.getSkill(2221007);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.FreezeLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.FREEZE, 1, 2221007, null, false), false, venomEffect.getDuration() / 15, true, venomEffect);
                    }
                    if (monster != null && stats.FreezeLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.FREEZE, 1, 2221007, null, false), false, venomEffect.getDuration() / 5, true, venomEffect);
                    }
                    if (monster != null && stats.FreezeLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.FREEZE, 1, 2221007, null, false), false, venomEffect.getDuration() / 3, true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************      
            if (stats.SealPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.SealPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(12111002);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.SealLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SEAL, 1, 12111002, null, false), false, venomEffect.getDuration() / 3, true, venomEffect);
                    }
                    if (monster != null && stats.SealLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SEAL, 1, 12111002, null, false), false, venomEffect.getDuration() * 3 / 5, true, venomEffect);
                    }
                    if (monster != null && stats.SealLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SEAL, 1, 12111002, null, false), false, venomEffect.getDuration(), true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************      
            if (stats.SlowPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.SlowPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(12101001);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.SlowLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SPEED, 1, 12101001, null, false), false, venomEffect.getDuration() / 4, true, venomEffect);
                    }
                    if (monster != null && stats.SlowLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SPEED, 1, 12101001, null, false), false, venomEffect.getDuration() * 3 / 4, true, venomEffect);
                    }
                    if (monster != null && stats.SlowLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SPEED, 1, 12101001, null, false), false, venomEffect.getDuration() * 5 / 4, true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************   
            if (stats.BlindPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.BlindPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(11111002);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.BlindLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.DARKNESS, 1, 11111002, null, false), false, venomEffect.getDuration() / 8, false, venomEffect);
                    }
                    if (monster != null && stats.BlindLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.DARKNESS, 1, 11111002, null, false), false, venomEffect.getDuration() / 5, false, venomEffect);
                    }
                    if (monster != null && stats.BlindLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.DARKNESS, 1, 11111002, null, false), false, venomEffect.getDuration() / 4, false, venomEffect);
                    }
                }
            }
            if (attack.skill == 3100010 && MapleStatEffect.times == 2 && Randomizer.nextInt(100) <= 90) {
            final Skill skill = SkillFactory.getSkill(3101009);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getTotalSkillLevel(skill));
                    if (monster != null) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.POISON, 1, 3101009, null, false), true, venomEffect.getDOTTime(), true, venomEffect);
                    }
            }
             if (attack.skill == 3120017 && MapleStatEffect.times == 2 && Randomizer.nextInt(100) <= 90) {
            final Skill skill = SkillFactory.getSkill(3121016);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getTotalSkillLevel(skill));
                    if (monster != null) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.POISON, 1, 3121016, null, false), true, venomEffect.getDOTTime(), true, venomEffect);
                    }
            }
            if (attack.skill != 1221011) {       
                monster.damage(player, totDamageToOneMonster, true, attack.skill);
                if (player.isGM()) {
                player.dropMessage(5, "Skill Id:" + attack.skill + "/ Total Damage : " + totDamage);
            }
            } else {
                monster.damage(player, monster.getStats().isBoss() ? 500000L : monster.getHp() - 1L, true, attack.skill);
            }
            Skill bx;
        int bof;
        MapleStatEffect eff;
        if (player.getJob() >= 321 && player.getJob() <= 322) {
        bx = SkillFactory.getSkill(3210001); //Mortal Blow
        bof = player.getTotalSkillLevel(bx);
        eff = bx.getEffect(bof);
        int Prob = eff.getY();
        int HPReq = eff.getX();
        int HPRecovery = eff.getZ() * player.getStat().getCurrentMaxHp() / 100;
        int MPRecovery = eff.getZ() * player.getStat().getCurrentMaxMp(player.getJob() / 100);
        if (Randomizer.nextInt(100) < Prob && monster.getHp() <= monster.getMobMaxHp() * HPReq / 100 && !monster.getStats().isBoss()) {
        monster.damage(player, monster.getMobMaxHp(), true, attack.skill);  
        player.addMP(MPRecovery);
        player.healHP(HPRecovery);//To show HP Gain on Screen
        player.getClient().getSession().write(CField.EffectPacket.showOwnBuffEffect(13110009, 2, player.getLevel(), MapleStatEffect.level));
        player.getMap().broadcastMessage(player, CField.EffectPacket.showBuffeffect(player.getId(), 13110009, 2, player.getLevel(), MapleStatEffect.level), false);
        }
        }
            if (totDamageToOneMonster >= 10000) {
                player.finishAchievement(26);
                }
                if (totDamageToOneMonster >= 50000) {
                player.finishAchievement(27);
                }
                if (totDamageToOneMonster >= 100000) {
                player.finishAchievement(28);
                }
                 if (totDamageToOneMonster >= 500000) {
                player.finishAchievement(29);
                }
                 if (totDamageToOneMonster >= 500000) {
                player.finishAchievement(29);
                }
                 if (totDamageToOneMonster >= 1000000) {
                player.finishAchievement(30);
                }
                 if (totDamageToOneMonster >= 10000000) {
                player.finishAchievement(50);
                }
                 if (totDamageToOneMonster >= 100000000) {
                player.finishAchievement(51);
                }
                 if (totDamageToOneMonster >= 1000000000) {
                player.finishAchievement(52);
                }
                 if (totDamageToOneMonster >= 10000000000L) {
                player.finishAchievement(53);
                }
        if (attack.skill == 3100010 && MapleStatEffect.times == 1 && Randomizer.nextInt(100) <= 20 || attack.skill == 3120017 && MapleStatEffect.times == 1 && Randomizer.nextInt(100) <= 20) {
        player.addHP(20 * totDamage / 100);
        }
        if (GameConstants.isLuminous(player.getJob())) {
       player.handleLuminous(attack.skill);
                }
//****************************************************************************************************************************************     
        } 
        if (attack.skill == 31121001 && player.getSkillLevel(31120051) > 0) {
        stats.reduceForceRSpecial = 50;    
        }
        else if (attack.skill == 31111005 && player.getSkillLevel(31120045) > 0) {
        stats.reduceForceRSpecial = 50;       
        }
        else {
        stats.reduceForceRSpecial = 0;//let's reset here    
        }
        if ((attack.skill != 0) && ((attack.targets > 0) || ((attack.skill != 4331003) && (attack.skill != 4341002))) && (!GameConstants.isNoDelaySkill(attack.skill))) {
            effect.applyTo(player, attack.position);
            if (player.isSuperGM()) {
                player.dropMessage(5, "ApplyTo");
                }
        }
        if (player.getSkillLevel(3210013) > 0) {
                    MapleStatEffect sse = SkillFactory.getSkill(3210013).getEffect(player.getSkillLevel(3210013));
                    sse.applyTo(player);
                }
        if (player.getJob() >= 1500 && player.getJob() <= 1512) {
            MapleStatEffect crescendo = SkillFactory.getSkill(15001022).getEffect(player.getSkillLevel(15001022));
            if (crescendo != null) {

                if (crescendo.makeChanceResult()) {
                    player.setLastCombo(System.currentTimeMillis());
                    if (player.acaneAim <= 3) {
                        player.acaneAim++;
                        crescendo.applyTo(player);
                    }
                }
            }
        }
int numFinisherOrbs = 0;
            int comboBuff = player.getBuffedValue(MapleBuffStat.COMBO);

            if (GameConstants.isFinisherSkill(attack.skill)) {
                if (comboBuff > 0) {
                    numFinisherOrbs = comboBuff - 1;
                }
                if (numFinisherOrbs <= 0) {
                    return;
                }
                player.handleOrbconsume(attack.skill == 1111003 ? 2 : attack.skill == 1101012 ? 1 : 0);
            }
        if (player.getJob() >= 420 && player.getJob() <= 422) {
            MapleStatEffect crescendo = SkillFactory.getSkill(4200013).getEffect(player.getSkillLevel(4200013));
            if (crescendo != null) {

                if (crescendo.makeChanceResult()) {
                    player.setLastCombo(System.currentTimeMillis());
                    if (player.acaneAim <= 30) {
                        player.acaneAim++;
                        crescendo.applyTo(player);
                    }
                }
            }
        }

        if ((attack.skill == 4331003) && ((hpMob <= 0L) || (totDamageToOneMonster < hpMob))) {
            return;
        }
        if ((hpMob > 0L) && (totDamageToOneMonster > 0)) {
            player.afterAttack(attack.targets, attack.hits, attack.skill);
        }
        if ((totDamage > 1) && (GameConstants.getAttackDelay(attack.skill, theSkill) >= 100)) {
            CheatTracker tracker = player.getCheatTracker();
            tracker.setAttacksWithoutHit(true);
            if (tracker.getAttacksWithoutHit() > 1000) {
                tracker.registerOffense(CheatingOffense.ATTACK_WITHOUT_GETTING_HIT, Integer.toString(tracker.getAttacksWithoutHit()));
            }
        }
        if (player.getSkillLevel(4100012) > 0) { //마크 오브 어쌔신
            MapleStatEffect eff = SkillFactory.getSkill(4100012).getEffect(player.getSkillLevel(4100012));
            if (eff.makeChanceResult()) {
                for (Map.Entry z : effect.getMonsterStati().entrySet()) {
                    for (AttackPair ap : attack.allDamage) {
                        final MapleMonster monster = player.getMap().getMonsterByOid(ap.objectid);
                        monster.applyStatus(player, new MonsterStatusEffect((MonsterStatus) z.getKey(), (Integer) z.getValue(), theSkill.getId(), null, false), effect.isPoison(), effect.getDuration(), true, effect);
//            }

                        // MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(Collections.singletonMap(MonsterStatus.POISON, eff.getSourceId().getStats()), SkillFactory.getSkill(4100011), null, false);
                        //  MonsterStatusEffect.setOwnerId(player.getId()); //cid가 맞아야 보이므로
                        // monster.applyStatus(player, new MonsterStatusEffect(Collections.singletonMap(MonsterStatus.POISON, eff.getSourceId(), SkillFactory.getSkill(4100011), null, false), true, eff.getDuration(), false));
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.POISON, Integer.valueOf(eff.getX()), eff.getSourceId(), null, false), false, eff.getY() * 1000, true, eff);
                        monster.applyStatus(player, new MonsterStatusEffect((MonsterStatus) z.getKey(), (Integer) z.getValue(), theSkill.getId(), null, false), effect.isPoison(), effect.getDuration(), true, effect);
                    }
                }
            }

            int bulletCount = eff.getBulletCount();
            for (AttackPair ap : attack.allDamage) {
                final MapleMonster source = player.getMap().getMonsterByOid(ap.objectid);

                //source.get
                final MonsterStatusEffect check = source.getBuff(MonsterStatus.POISON);

                //  if (check != null && check.getSkill().getId() == 4100011 && check.getOwnerId() == player.getId()) {
                if (check != null && check.getSkill() == 4100011 && check.getOwnerId() == player.getId()) { //:3
                    //   player.message("조건 진입");
                    final List<MapleMapObject> objs = player.getMap().getMapObjectsInRange(player.getPosition(), 500000, Arrays.asList(MapleMapObjectType.MONSTER));
                    final List<MapleMonster> monsters = new ArrayList<>();
                    for (int i = 0; i < bulletCount; i++) {
                        int rand = Randomizer.rand(0, objs.size() - 1);
                        if (objs.size() < bulletCount) {
                            if (i < objs.size()) {
                                monsters.add((MapleMonster) objs.get(i));
                            }
                        } else {
                            monsters.add((MapleMonster) objs.get(rand));
                            objs.remove(rand);
                        }
                    }
                    if (monsters.size() <= 0) {
                        CWvsContext.enableActions();
                        return;
                    }
                    final List<Point> points = new ArrayList<>();
                    for (MapleMonster mob : monsters) {
                        points.add(mob.getPosition());
                    }
                    //                 player.dropMessage(monsters.size());
                    //  player.dropMessage("시작" + monsters.size());
                    player.getMap().broadcastMessage(CWvsContext.giveMarkOfTheif(player.getId(), source.getObjectId(), 4100012, monsters, player.getPosition(), monsters.get(0).getPosition(), 2070005));
                    //          player.message("종료");
                }
            }
        }

        if (player.getJob() == 412) {
            for (AttackPair ap : attack.allDamage) {
                final MapleMonster source = player.getMap().getMonsterByOid(ap.objectid);
                final List<MapleMapObject> objs = player.getMap().getMapObjectsInRange(player.getPosition(), 500000, Arrays.asList(MapleMapObjectType.MONSTER));
                final List<MapleMonster> monsters = new ArrayList<>();
                player.getMap().broadcastMessage(CWvsContext.giveMarkOfTheif(player.getId(), source.getObjectId(), 4100012, monsters, player.getPosition(), monsters.get(0).getPosition(), 2070005));
            }
        }
        if (player.getBuffedValue(MapleBuffStat.DARK_CRESCENDO) != null && GameConstants.isLuminous(player.getJob())) {
                        MapleStatEffect crescendo = SkillFactory.getSkill(27121005).getEffect(player.getSkillLevel(27121005));
                        if (crescendo != null) {
                            if (Randomizer.nextInt(100) < crescendo.getProb()) {
                                if (player.acaneAim < crescendo.getX()) {
                                    player.acaneAim++;
                                    Map<MapleBuffStat, Integer> localstatups = statups, maskedStatups = null;
                                    localstatups = new EnumMap<>(MapleBuffStat.class);
                                    localstatups.put(MapleBuffStat.DARK_CRESCENDO, player.acaneAim);
                                    //localstatups.put(MapleBuffStat.PRESSURE_VOID, player.acaneAim);
                                    player.getClient().getSession().write(CWvsContext.BuffPacket.giveBuff(27121005, (crescendo.getDuration() + (crescendo.getDuration() * player.getStat().BuffUP / 100)), localstatups, c, player));
                                }
                            }
                        }
                }
        //Auto Loot
        for (MaplePet pet : player.getSummonedPets()) {
        if (PickAll > 0 && pet.getSummoned()) {
        List<MapleMapObject> objects = player.getMap().getMapObjectsInRange(player.getTruePosition(), player.getRange(), Arrays.asList(MapleMapObjectType.ITEM));
        boolean foundItem = false;
        for (MapleMapObject mapitemz : objects) {
                        final MapleMapItem mapitem = (MapleMapItem) mapitemz;
                        final Lock lock = mapitem.getLock();
                        lock.lock();
                        try {
                            if (mapitem.isPickedUp()) {
                                continue;
                            }
                            if (mapitem.getQuest() > 0 && player.getQuestStatus(mapitem.getQuest()) != 1) {
                                continue;
                            }
                            if (mapitem.getOwner() != player.getId() && mapitem.isPlayerDrop()) {
                                continue;
                            }
                            if (mapitem.getOwner() != player.getId() && ((!mapitem.isPlayerDrop() && mapitem.getDropType() == 0) || (mapitem.isPlayerDrop() && player.getMap().getEverlast()))) {
                                continue;
                            }
                            if (!mapitem.isPlayerDrop() && (mapitem.getDropType() == 1 || mapitem.getDropType() == 3) && mapitem.getOwner() != player.getId()) {
                                continue;
                            }
                            if (mapitem.getDropType() == 2 && mapitem.getOwner() != player.getId()) {
                                continue;
                            }
                            if (mapitem.getMeso() > 0) {
                                if (player.getParty() != null && mapitem.getOwner() != player.getId()) {
                                    final List<MapleCharacter> toGive = new LinkedList<>();
                                    final int splitMeso = mapitem.getMeso() * 40 / 100;
                                    for (MaplePartyCharacter z : player.getParty().getMembers()) {
                                        MapleCharacter m = player.getMap().getCharacterById(z.getId());
                                        if (m != null && m.getId() != player.getId()) {
                                            toGive.add(m);
                                        }
                                    }
                                    for (final MapleCharacter m : toGive) {
                                        m.gainMeso(splitMeso / toGive.size(), true, true);
                                    }
                                    player.gainMeso(mapitem.getMeso() - splitMeso, true, true);
                                } else {
                                    player.gainMeso(mapitem.getMeso(), true, true);
                                }
                                InventoryHandler.removeItem_Pet(player, mapitem, 0);
                                foundItem = true;
                            } else if (!MapleItemInformationProvider.getInstance().isPickupBlocked(mapitem.getItem().getItemId()) && mapitem.getItem().getItemId() / 10000 != 291 && mapitem.getItem().getItemId() / 10000 != 287 && mapitem.getItem().getItemId() / 10000 !=  238/*&& mapitem.getItem().getItemId() / 10000 != 251 && mapitem.getItem().getItemId() / 10000 != 238 && mapitem.getItem().getItemId() / 10000 != 440 */&& !mapitem.isPlayerDrop()) {
                                if (InventoryHandler.useItem(player.getClient(), mapitem.getItemId())) {
                                    InventoryHandler.removeItem_Pet(player, mapitem, 0);
                                } else if (MapleInventoryManipulator.checkSpace(player.getClient(), mapitem.getItem().getItemId(), mapitem.getItem().getQuantity(), mapitem.getItem().getOwner())) {
                                    if (mapitem.getItem().getQuantity() >= 50 && mapitem.getItem().getItemId() == 2340000) {
                                        player.getClient().setMonitored(true); //hack check
                                    }
                                    if (MapleInventoryManipulator.addFromDrop(player.getClient(), mapitem.getItem(), true, mapitem.getDropper() instanceof MapleMonster)) {
                                        InventoryHandler.removeItem_Pet(player, mapitem, 0);
                                        foundItem = true;
                                    }
                                }
                            }
                        } finally {
                            lock.unlock();
                        }
                }
        if (foundItem) {
                    return;
           }
        }
      }
      //End Auto Loot
    }

    @SuppressWarnings("empty-statement")
    public static final void applyAttackMagic(AttackInfo attack, Skill theSkill, MapleCharacter player, MapleStatEffect effect, double maxDamagePerHit) {
        if (!player.isAlive()) {
            player.getCheatTracker().registerOffense(CheatingOffense.ATTACKING_WHILE_DEAD);
            return;
        }
        if ((attack.real) && (GameConstants.getAttackDelay(attack.skill, theSkill) >= 100)) {
            player.getCheatTracker().checkAttack(attack.skill, attack.lastAttackTickCount);
        }
        if ((attack.hits > 0) && (attack.targets > 0) && (!player.getStat().checkEquipDurabilitys(player, -1))) {
            player.dropMessage(5, "An item has run out of durability but has no inventory room to go to.");
            return;
        }
        if (GameConstants.isMulungSkill(attack.skill)) {
            if (player.getMapId() / 10000 != 92502) {
                return;
            }
            if (player.getMulungEnergy() < 10000) {
                return;
            }
            player.mulung_EnergyModify(false);
        } else if (GameConstants.isPyramidSkill(attack.skill)) {
            if (player.getMapId() / 1000000 != 926) {
                return;
            }

           // if ((player.getPyramidSubway() != null) && (player.getPyramidSubway().onSkillUse(player)));
        } /*else if ((GameConstants.isInflationSkill(attack.skill)) && (player.getBuffedValue(MapleBuffStat.GIANT_POTION) == null)) {
            return;
        }*/
        
        if (player.getClient().getChannelServer().isAdminOnly()) {
            player.dropMessage(-1, new StringBuilder().append("Animation: ").append(Integer.toHexString((attack.display & 0x8000) != 0 ? attack.display - 32768 : attack.display)).toString());
        }
        PlayerStats stats = player.getStat();
        Element element = player.getBuffedValue(MapleBuffStat.ELEMENT_RESET) != null ? Element.NEUTRAL : theSkill.getElement();

        double MaxDamagePerHit = 0.0D;
        int totDamage = 0;

        int CriticalDamage = stats.passive_sharpeye_percent();
        Skill eaterSkill = SkillFactory.getSkill(GameConstants.getMPEaterForJob(player.getJob()));
        int eaterLevel = player.getTotalSkillLevel(eaterSkill);

        MapleMap map = player.getMap();

        int antiKS = 0;
        for (AttackPair oned : attack.allDamage) {
            MapleMonster monster = map.getMonsterByOid(oned.objectid);

            if (ServerConstants.AntiKS) {
                MapleQuestStatus statt = player.getQuestNoAdd(MapleQuest.getInstance(732648172));
                boolean antiks;
                if (statt == null || statt.getCustomData() == null) {
                    antiks = false;
                    statt.setCustomData(antiks + ";" + 0);
                } else {
                    String[] statss = statt.getCustomData().split(";");
                    try {
                        antiks = Boolean.parseBoolean(statss[0]);
                    } catch (Exception ex) {
                        antiks = false;
                    }
                    if (monster.getBelongsToSomeone() && monster.getBelongsTo() != player.getId()
                            && (player.getParty() == null
                            || player.getParty().getMemberById(monster.getBelongsTo()) == null)
                            && !player.isGM()) {
                        monster.setBelongsTo(player);
                        antiKS++;
                        statt.setCustomData(antiks + ";" + (Integer.parseInt(statss[1]) + 1));
                    }
                }
                if (monster.getBelongsToSomeone()) {
                    if (monster.getBelongsTo() != player.getId() && !player.isGM()) {
                        player.dropMessage(5, "You cannot hit this monster because it belongs to someone else.");
                        continue;
                    }
                    if (player.isGM()) {
                        player.dropMessage(5, "You are attacking a monster who was marked by another player.");
                    }
                }
            }
            if ((monster != null) && (monster.getLinkCID() <= 0)) {
                boolean Tempest = (monster.getStatusSourceID(MonsterStatus.FREEZE) == 21120006) && (!monster.getStats().isBoss());
                int totDamageToOneMonster = 0;
                MapleMonsterStats monsterstats = monster.getStats();
                int fixeddmg = monsterstats.getFixedDamage();
                byte overallAttackCount = 0;

                for (Pair eachde : oned.attack) {
                    Integer eachd = (Integer) eachde.left;
                    overallAttackCount = (byte) (overallAttackCount + 1);
                    if (fixeddmg != -1) {
                        eachd = Integer.valueOf(monsterstats.getOnlyNoramlAttack() ? 0 : fixeddmg);
                    } else if (monsterstats.getOnlyNoramlAttack()) {
                        eachd = Integer.valueOf(0);
                    } 
                    totDamageToOneMonster += eachd.intValue();
                }

                totDamage += totDamageToOneMonster;
                player.checkMonsterAggro(monster);

                if ((GameConstants.getAttackDelay(attack.skill, theSkill) >= 100) && (!GameConstants.isNoDelaySkill(attack.skill)) && (!monster.getStats().isBoss()) && (player.getTruePosition().distanceSq(monster.getTruePosition()) > GameConstants.getAttackRange(effect, player.getStat().defRange))) {
                    player.getCheatTracker().registerOffense(CheatingOffense.ATTACK_FARAWAY_MONSTER, new StringBuilder().append("[Distance: ").append(player.getTruePosition().distanceSq(monster.getTruePosition())).append(", Expected Distance: ").append(GameConstants.getAttackRange(effect, player.getStat().defRange)).append(" Job: ").append(player.getJob()).append("]").toString());
                }
                if ((attack.skill == 2301002) && (!monsterstats.getUndead())) {
                    player.getCheatTracker().registerOffense(CheatingOffense.HEAL_ATTACKING_UNDEAD);
                    return;
                }
                if (monster.isBuffed(MonsterStatus.MAGIC_DAMAGE_REFLECT) && !monster.getStats().isElite()) {
                        player.addHP(-(7000 + Randomizer.nextInt(8000)));
                    }
                    if (monster.isBuffed(MonsterStatus.MAGIC_DAMAGE_REFLECT) && monster.getStats().isElite()) {
                        player.addHP(- 3 * player.getStat().getCurrentMaxHp() / 100);
                    }
                if (eaterLevel > 0) {
                        eaterSkill.getEffect(eaterLevel).applyPassive(player, monster);
                    }
                if (stats.AutoStealprop > 0) {
            monster.handleSteal(player);
            }
            if (stats.PoisonPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.PoisonPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(2111003);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.POISON, 1, 2111003, null, false), true, venomEffect.getDOTTime(), true, venomEffect);
                    }
                }
            }
            if (stats.BurnPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.BurnPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(12111005);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill) + 20);
                    if (monster != null) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.POISON, 1, 12111005, null, false), true, venomEffect.getDOTTime(), true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************
            if (stats.StunPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.StunPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(1111008);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.StunLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.STUN, 1, 1111008, null, false), false, venomEffect.getDuration() / 4, true, venomEffect);
                    }
                    if (monster != null && stats.StunLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.STUN, 1, 1111008, null, false), false, venomEffect.getDuration() * 3 / 4, true, venomEffect);
                    }
                    if (monster != null && stats.StunLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.STUN, 1, 1111008, null, false), false, venomEffect.getDuration() * 5 / 4, true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************      
            if (stats.FreezePotential > 0) {
                if (Randomizer.nextInt(100) <= stats.FreezePotentialprop) {
                    final Skill skill = SkillFactory.getSkill(2221007);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.FreezeLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.FREEZE, 1, 2221007, null, false), false, venomEffect.getDuration() / 15, true, venomEffect);
                    }
                    if (monster != null && stats.FreezeLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.FREEZE, 1, 2221007, null, false), false, venomEffect.getDuration() / 5, true, venomEffect);
                    }
                    if (monster != null && stats.FreezeLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.FREEZE, 1, 2221007, null, false), false, venomEffect.getDuration() / 3, true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************      
            if (stats.SealPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.SealPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(12111002);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.SealLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SEAL, 1, 12111002, null, false), false, venomEffect.getDuration() / 3, true, venomEffect);
                    }
                    if (monster != null && stats.SealLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SEAL, 1, 12111002, null, false), false, venomEffect.getDuration() * 3 / 5, true, venomEffect);
                    }
                    if (monster != null && stats.SealLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SEAL, 1, 12111002, null, false), false, venomEffect.getDuration(), true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************      
            if (stats.SlowPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.SlowPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(12101001);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.SlowLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SPEED, 1, 12101001, null, false), false, venomEffect.getDuration() / 4, true, venomEffect);
                    }
                    if (monster != null && stats.SlowLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SPEED, 1, 12101001, null, false), false, venomEffect.getDuration() * 3 / 4, true, venomEffect);
                    }
                    if (monster != null && stats.SlowLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SPEED, 1, 12101001, null, false), false, venomEffect.getDuration() * 5 / 4, true, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************   
            if (stats.BlindPotential > 0) {
                if (Randomizer.nextInt(100) <= stats.BlindPotentialprop) {
                    final Skill skill = SkillFactory.getSkill(11111002);
                    final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                    if (monster != null && stats.BlindLevel == 1) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.DARKNESS, 1, 11111002, null, false), false, venomEffect.getDuration() / 8, false, venomEffect);
                    }
                    if (monster != null && stats.BlindLevel == 2) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.DARKNESS, 1, 11111002, null, false), false, venomEffect.getDuration() / 6, false, venomEffect);
                    }
                    if (monster != null && stats.BlindLevel >= 3) {
                        monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.DARKNESS, 1, 11111002, null, false), false, venomEffect.getDuration() / 4, false, venomEffect);
                    }
                }
            }
//****************************************************************************************************************************************
                if (!GameConstants.isLightSkills(attack.skill) && !GameConstants.isDarkSkills(attack.skill)) {
                        if (totDamageToOneMonster >= 10000) {
                player.finishAchievement(26);
                }
                if (totDamageToOneMonster >= 50000) {
                player.finishAchievement(27);
                }
                if (totDamageToOneMonster >= 100000) {
                player.finishAchievement(28);
                }
                 if (totDamageToOneMonster >= 500000) {
                player.finishAchievement(29);
                }
                 if (totDamageToOneMonster >= 500000) {
                player.finishAchievement(29);
                }
                 if (totDamageToOneMonster >= 1000000) {
                player.finishAchievement(30);
                }
                 if (totDamageToOneMonster >= 10000000) {
                player.finishAchievement(50);
                }
                 if (totDamageToOneMonster >= 100000000) {
                player.finishAchievement(51);
                }
                 if (totDamageToOneMonster >= 1000000000) {
                player.finishAchievement(52);
                }
                 if (totDamageToOneMonster >= 10000000000L) {
                player.finishAchievement(53);
                }
                monster.damage(player, totDamage, true, attack.skill);
                }
                if (player.isSuperGM()) {
                player.dropMessage(5, "Skill Id:" + attack.skill + "/ Total Magic Damage : " + totDamage);
            }
            }
            if (player.getJob() >= 1500 && player.getJob() <= 1512) {
                MapleStatEffect crescendo = SkillFactory.getSkill(15001022).getEffect(player.getSkillLevel(15001022));
                if (crescendo != null) {
                    if (crescendo.makeChanceResult()) {
                        player.setLastCombo(System.currentTimeMillis());
                        if (player.acaneAim <= 3) {
                            player.acaneAim++;
                            crescendo.applyTo(player);
                        }
                    }
                }
            }

            if (attack.skill != 2301002 && !GameConstants.isNoDelaySkill(attack.skill)) {
                effect.applyTo(player, attack.position);
                if (player.isSuperGM()) {
                player.dropMessage(5, "Magic ApplyTo");
                }
            }

            if ((totDamage > 1) && (GameConstants.getAttackDelay(attack.skill, theSkill) >= 100)) {
                CheatTracker tracker = player.getCheatTracker();
                tracker.setAttacksWithoutHit(true);

                if (tracker.getAttacksWithoutHit() > 1000) {
                    tracker.registerOffense(CheatingOffense.ATTACK_WITHOUT_GETTING_HIT, Integer.toString(tracker.getAttacksWithoutHit()));
                }
            }
          //Auto Loot
        for (MaplePet pet : player.getSummonedPets()) {
        if (PickAll > 0 && pet.getSummoned()) {
        List<MapleMapObject> objects = player.getMap().getMapObjectsInRange(player.getTruePosition(), player.getRange(), Arrays.asList(MapleMapObjectType.ITEM));
        boolean foundItem = false;
        for (MapleMapObject mapitemz : objects) {
                        final MapleMapItem mapitem = (MapleMapItem) mapitemz;
                        final Lock lock = mapitem.getLock();
                        lock.lock();
                        try {
                            if (mapitem.isPickedUp()) {
                                continue;
                            }
                            if (mapitem.getQuest() > 0 && player.getQuestStatus(mapitem.getQuest()) != 1) {
                                continue;
                            }
                            if (mapitem.getOwner() != player.getId() && mapitem.isPlayerDrop()) {
                                continue;
                            }
                            if (mapitem.getOwner() != player.getId() && ((!mapitem.isPlayerDrop() && mapitem.getDropType() == 0) || (mapitem.isPlayerDrop() && player.getMap().getEverlast()))) {
                                continue;
                            }
                            if (!mapitem.isPlayerDrop() && (mapitem.getDropType() == 1 || mapitem.getDropType() == 3) && mapitem.getOwner() != player.getId()) {
                                continue;
                            }
                            if (mapitem.getDropType() == 2 && mapitem.getOwner() != player.getId()) {
                                continue;
                            }
                            if (mapitem.getMeso() > 0) {
                                if (player.getParty() != null && mapitem.getOwner() != player.getId()) {
                                    final List<MapleCharacter> toGive = new LinkedList<>();
                                    final int splitMeso = mapitem.getMeso() * 40 / 100;
                                    for (MaplePartyCharacter z : player.getParty().getMembers()) {
                                        MapleCharacter m = player.getMap().getCharacterById(z.getId());
                                        if (m != null && m.getId() != player.getId()) {
                                            toGive.add(m);
                                        }
                                    }
                                    for (final MapleCharacter m : toGive) {
                                        m.gainMeso(splitMeso / toGive.size(), true, true);
                                    }
                                    player.gainMeso(mapitem.getMeso() - splitMeso, true, true);
                                } else {
                                    player.gainMeso(mapitem.getMeso(), true, true);
                                }
                                InventoryHandler.removeItem_Pet(player, mapitem, 0);
                                foundItem = true;
                            } else if (!MapleItemInformationProvider.getInstance().isPickupBlocked(mapitem.getItem().getItemId()) && mapitem.getItem().getItemId() / 10000 != 291 && mapitem.getItem().getItemId() / 10000 != 287 && mapitem.getItem().getItemId() / 10000 !=  238/*&& mapitem.getItem().getItemId() / 10000 != 251 && mapitem.getItem().getItemId() / 10000 != 238 && mapitem.getItem().getItemId() / 10000 != 440 */&& !mapitem.isPlayerDrop()) {
                                if (InventoryHandler.useItem(player.getClient(), mapitem.getItemId())) {
                                    InventoryHandler.removeItem_Pet(player, mapitem, 0);
                                } else if (MapleInventoryManipulator.checkSpace(player.getClient(), mapitem.getItem().getItemId(), mapitem.getItem().getQuantity(), mapitem.getItem().getOwner())) {
                                    if (mapitem.getItem().getQuantity() >= 50 && mapitem.getItem().getItemId() == 2340000) {
                                        player.getClient().setMonitored(true); //hack check
                                    }
                                    if (MapleInventoryManipulator.addFromDrop(player.getClient(), mapitem.getItem(), true, mapitem.getDropper() instanceof MapleMonster)) {
                                        InventoryHandler.removeItem_Pet(player, mapitem, 0);
                                        foundItem = true;
                                    }
                                }
                            }
                        } finally {
                            lock.unlock();
                        }
                }
        if (foundItem) {
                    return;
           }
        }
      }
      //End Auto Loot
        }
    }

    private static void handlePickPocket(MapleCharacter player, MapleMonster mob, AttackPair oned) {
        int maxmeso = player.getBuffedValue(MapleBuffStat.PICKPOCKET).intValue();

        for (Pair eachde : oned.attack) {
            Integer eachd = (Integer) eachde.left;
            if ((player.getStat().pickRate >= 100) || (Randomizer.nextInt(99) < player.getStat().pickRate)) {
                player.getMap().spawnMesoDrop(Math.min((int) Math.max(eachd.intValue() / 20000.0D * maxmeso, 1.0D), maxmeso), new Point((int) (mob.getTruePosition().getX() + Randomizer.nextInt(100) - 50.0D), (int) mob.getTruePosition().getY()), mob, player, false, (byte) 0);
            }
        }
    }

    public static final AttackInfo DivideAttack(final AttackInfo attack, final int rate) {
        attack.real = false;
        if (rate <= 1) {
            return attack; //lol
        }
        for (AttackPair p : attack.allDamage) {
            if (p.attack != null) {
                for (Pair<Integer, Boolean> eachd : p.attack) {
                    eachd.left /= rate; //too ex.
                }
            }
        }
        return attack;
    }

    public static final AttackInfo Modify_AttackCrit(AttackInfo attack, MapleCharacter chr, int type, MapleStatEffect effect) {
        int CriticalRate;
        boolean shadow;
        List damages;
        List damage;
        if ((attack.skill != 4211006) && (attack.skill != 3211003) && (attack.skill != 4111004)) {
            CriticalRate = chr.getStat().passive_sharpeye_rate() + (effect == null ? 0 : effect.getCr());
            shadow = (chr.getBuffedValue(MapleBuffStat.SHADOWPARTNER) != null) && ((type == 1) || (type == 2));
            damages = new ArrayList();
            damage = new ArrayList();

            for (AttackPair p : attack.allDamage) {
                if (p.attack != null) {
                    int hit = 0;
                    int mid_att = shadow ? p.attack.size() / 2 : p.attack.size();

                    int toCrit = (attack.skill == 4221001) || (attack.skill == 3221007) || (attack.skill == 23121003) || (attack.skill == 4341005) || (attack.skill == 4331006) || (attack.skill == 21120005) ? mid_att : 0;
                    if (toCrit == 0) {
                        for (Pair eachd : p.attack) {
                            if ((!((Boolean) eachd.right).booleanValue()) && (hit < mid_att)) {
                                if ((((Integer) eachd.left).intValue() > 999999) || (Randomizer.nextInt(100) < CriticalRate)) {
                                    toCrit++;
                                }
                                damage.add(eachd.left);
                            }
                            hit++;
                        }
                        if (toCrit == 0) {
                            damage.clear();
                        } else {
                            Collections.sort(damage);
                            for (int i = damage.size(); i > damage.size() - toCrit; i--) {
                                damages.add(damage.get(i - 1));
                            }
                            damage.clear();
                        }
                    } else {
                        hit = 0;
                        for (Pair eachd : p.attack) {
                            if (!((Boolean) eachd.right).booleanValue()) {
                                if (attack.skill == 4221001) {
                                    eachd.right = Boolean.valueOf(hit == 3);
                                } else if ((attack.skill == 3221007) || (attack.skill == 23121003) || (attack.skill == 21120005) || (attack.skill == 4341005) || (attack.skill == 4331006) || (((Integer) eachd.left).intValue() > 999999)) {
                                    eachd.right = Boolean.valueOf(true);
                                } else if (hit >= mid_att) {
                                    eachd.right = ((Pair) p.attack.get(hit - mid_att)).right;
                                } else {
                                    eachd.right = Boolean.valueOf(damages.contains(eachd.left));
                                }
                            }
                            hit++;
                        }
                        damages.clear();
                    }
                }
            }
        }
        return attack;
    }

    public static AttackInfo parseDmgMa(LittleEndianAccessor lea, MapleCharacter chr) // magic
    {
        try {
            AttackInfo ret = new AttackInfo();

            lea.skip(1);
            ret.tbyte = lea.readByte();

            ret.targets = ((byte) (ret.tbyte >>> 4 & 0xF));
            ret.hits = ((byte) (ret.tbyte & 0xF));
            ret.skill = lea.readInt();
            if (ret.skill >= 91000000 && ret.skill < 100000000) {
                return null;
            }
            
            lea.skip(1); // 1
            lea.readInt(); // 5
            lea.readShort(); // 7 
            if (GameConstants.isMagicChargeSkill(ret.skill)) {
                ret.charge = lea.readInt();
            } else {
                ret.charge = -1;
            }
            ret.unk = lea.readByte();
            ret.display = lea.readUShort();

            lea.skip(4);
            ret.speed = lea.readByte();
            ret.lastAttackTickCount = lea.readInt();
            lea.skip(4);
            ret.allDamage = new ArrayList();

            for (int i = 0; i < ret.targets; i++) {
                int oid = lea.readInt();
                lea.skip(20);//v140 = 19
                List allDamageNumbers = new ArrayList();

                for (int j = 0; j < ret.hits; j++) {
                    int damage = lea.readInt();
                    allDamageNumbers.add(new Pair(damage, false));
                }
                //lea.skip(8);
                lea.skip(4); // CRC of monster [Wz Editing]
                lea.readInt(); //idk
                ret.allDamage.add(new AttackPair(oid, allDamageNumbers));
            }
            if (lea.available() >= 4L) {
                ret.position = lea.readPos();
                lea.skip(1);
            }
       //     if (ret.skill == 2321054){//Asura - Mixtamal6
       //     lea.skip(3); //new
     //   } 
            return ret;
        } catch (Exception e) {
        }
        return null;
    }

    public static AttackInfo parseDmgM(LittleEndianAccessor lea, MapleCharacter chr)//reg att
    {
        AttackInfo ret = new AttackInfo();
        lea.skip(1);
        ret.tbyte = lea.readByte();

        ret.targets = ((byte) (ret.tbyte >>> 4 & 0xF));
        ret.hits = ((byte) (ret.tbyte & 0xF));
        ret.skill = lea.readInt();
        if (GameConstants.isZero(chr.getJob()) && ret.skill != 0) {
            lea.skip(1); //zero has byte
        }
        if (ret.skill == 2221012|| ret.skill == 36101001 || ret.skill == 42120003) {
            lea.skip(1);
        }
        lea.skip(1);
        lea.readInt();
       // lea.readInt(); //same as above
        lea.readShort();
        switch (ret.skill) {
            case 1311011:// La Mancha Spear
            case 2221012:
            case 4341002:
            case 4341003:
            case 4221052:
            case 5201002:
            case 5300007:
            case 5301001:
            case 11121052:// Styx Crossing
            case 11121055:// Styx Crossing charged
            case 31201001:
            case 31211001:
            case 14111006:
            case 24121000:
            case 24121005:
            case 27101202:
            case 27111100:
            case 27120211:
            case 27121201:    
            case 31001000:
            case 31101000:
            case 31111005:
            case 36121000:
            case 36101001:
            case 42120003: // Monkey Spirits
            case 61111100:
            case 61111111:
            case 61111113:
            case 65121003:
            case 65121052:// Supreme Supernova
            case 101110101:
            case 101110102:
            case 101110104:
            case 101120200:
            case 101120203:
            case 1095:
            case 101120205:
            case 32121003: //Tornado Spin
         //   case 36121001:
                ret.charge = lea.readInt();
                break;
            default:
                ret.charge = 0;
        }
        
        switch (ret.skill) {
            case 11101007: // Power Reflection
            case 11101006: // Dawn Warrior - Power Reflection
            case 21101003: // body pressure
            case 2111007:// tele mastery skills
            case 2211007:
            case 12111007:
            case 22161005:
            case 32111010:
            case 2311007: // bishop tele mastery
                ret.charge = 0;
                ret.display = lea.readUShort();
                lea.skip(4);// dunno
                ret.speed = (byte) lea.readShort();
                ret.lastAttackTickCount = lea.readInt();
                lea.skip(4);// looks like zeroes
                ret.allDamage = new ArrayList();
                for (int i = 0; i < ret.targets; i++) {
            int oid = lea.readInt();
            lea.skip(20);//was 19
            List allDamageNumbers = new ArrayList();
            for (int j = 0; j < ret.hits; j++) {
                int damage = lea.readInt();
                allDamageNumbers.add(new Pair(Integer.valueOf(damage), Boolean.valueOf(false)));
            }
            lea.skip(8);
            ret.allDamage.add(new AttackPair(Integer.valueOf(oid).intValue(), allDamageNumbers));
        }
                ret.position = lea.readPos();
                return ret;
        }
        
        if (GameConstants.isInflationSkill2(ret.skill)) {
                ret.charge = 0;
                ret.display = lea.readUShort();
                lea.skip(4);// dunno
                ret.speed = (byte) lea.readShort();
                ret.lastAttackTickCount = lea.readInt();
                lea.skip(4);// looks like zeroes
                ret.allDamage = new ArrayList();
                for (int i = 0; i < ret.targets; i++) {
            int oid = lea.readInt();
            lea.skip(20);//was 19
            List allDamageNumbers = new ArrayList();
            for (int j = 0; j < ret.hits; j++) {
                int damage = lea.readInt();
                allDamageNumbers.add(new Pair(Integer.valueOf(damage), Boolean.valueOf(false)));
            }
            lea.skip(8);
            ret.allDamage.add(new AttackPair(Integer.valueOf(oid).intValue(), allDamageNumbers));
        }
                ret.position = lea.readPos();
                return ret;
       }
      
        ret.unk = lea.readByte();
        ret.display = lea.readUShort();
        if (ret.skill == 2221012 || ret.skill == 36101001 ||ret.skill == 42120003) {
            lea.skip(4);
        } else {
            lea.skip(5);
        }
        if ((ret.skill == 5300007) || (ret.skill == 5101012) || (ret.skill == 5081001) || (ret.skill == 15101010)) {
            lea.readInt();
        }
        ret.speed = lea.readByte();
        ret.lastAttackTickCount = lea.readInt();
        if (GameConstants.isEnergyBuff(ret.skill)) { 
            lea.skip(4);
        } 
        else if (ret.skill == 4341052){//Asura
            lea.skip(3); //new
        } 
        else {
            lea.skip(8);
        }

        ret.allDamage = new ArrayList();

        for (int i = 0; i < ret.targets; i++) {
            int oid = lea.readInt();

            lea.skip(20);//was 19

            List allDamageNumbers = new ArrayList();

            for (int j = 0; j < ret.hits; j++) {
                int damage = lea.readInt();

                allDamageNumbers.add(new Pair(Integer.valueOf(damage), Boolean.valueOf(false)));
            }
            lea.skip(8);
            ret.allDamage.add(new AttackPair(Integer.valueOf(oid).intValue(), allDamageNumbers));
        }
        ret.position = lea.readPos();

        return ret;
    }

    public static AttackInfo parseDmgR(LittleEndianAccessor lea, MapleCharacter chr)//ranged att
    {
        AttackInfo ret = new AttackInfo();
        lea.skip(1);
        ret.tbyte = lea.readByte();

        ret.targets = ((byte) (ret.tbyte >>> 4 & 0xF));
        ret.hits = ((byte) (ret.tbyte & 0xF));
        ret.skill = lea.readInt();
        if (GameConstants.isZero(chr.getJob())) {
            lea.skip(1); //zero has byte
        }
        lea.skip(1);
        lea.readInt();
     //   lea.readInt(); //same as above
        lea.readShort();
        switch (ret.skill) {
            case 3121004:
            case 3221001:
            case 5321052:
            case 5221004:
            case 5311002:
            case 5711002:
            case 5721001:
            case 13111002://Hurricane
            case 13111020://Sentient Arrow
            case 13121001://Song of Heaven
            case 23121000:
            case 24121000:
            case 33121009:
            case 35001001:
            case 35101009:
            case 60011216://Soul Buster
            case 3101008:
            case 3111009:// Hurricane
            case 3121013:// Arrow Blaster
            case 3120019:
            case 5221022:
                lea.skip(4);
        }

        ret.charge = -1;
        ret.unk = lea.readByte();
        ret.display = lea.readUShort();
        lea.skip(5);
        if (ret.skill == 23111001 || ret.skill == 36111010) {
            lea.skip(12);
        } else if (ret.skill == 3121013) {// Arrow Blaster
            lea.skip(8);
        }if ((ret.skill == 5221022) || (ret.skill == 5220023) || (ret.skill == 95001000)) {//Corsair BoarSide IDSkills
            lea.readInt();//newv144
            lea.readInt();//newv144
        }  
        ret.speed = lea.readByte();
        ret.lastAttackTickCount = lea.readInt();
        lea.skip(4);
        ret.slot = ((byte) lea.readShort());
        ret.csstar = ((byte) lea.readShort());
        ret.AOE = lea.readByte();
        ret.allDamage = new ArrayList();

        for (int i = 0; i < ret.targets; i++) {
            int oid = lea.readInt();
            lea.skip(20);//v140 = 19
            List allDamageNumbers = new ArrayList();
            for (int j = 0; j < ret.hits; j++) {
                int damage = lea.readInt();
                allDamageNumbers.add(new Pair(Integer.valueOf(damage), Boolean.valueOf(false)));
            }
            lea.skip(8);
            ret.allDamage.add(new AttackPair(Integer.valueOf(oid).intValue(), allDamageNumbers));
        }
        ret.position = lea.readPos();
        if (lea.available() >= 4) {
            lea.skip(4);//moved after pos in v141
        }
        return ret;
    }

    public static AttackInfo parseExplosionAttack(LittleEndianAccessor lea, AttackInfo ret, MapleCharacter chr) {
        if (ret.hits == 0) {
            lea.skip(4);
            byte bullets = lea.readByte();
            for (int j = 0; j < bullets; j++) {
                ret.allDamage.add(new AttackPair(Integer.valueOf(lea.readInt()).intValue(), null));
                lea.skip(1);
            }
            lea.skip(2);
            return ret;
        }

        for (int i = 0; i < ret.targets; i++) {
            int oid = lea.readInt();

            lea.skip(12);
            byte bullets = lea.readByte();
            List allDamageNumbers = new ArrayList();
            for (int j = 0; j < bullets; j++) {
                allDamageNumbers.add(new Pair(Integer.valueOf(lea.readInt()), Boolean.valueOf(false)));
            }
            ret.allDamage.add(new AttackPair(Integer.valueOf(oid).intValue(), allDamageNumbers));
            lea.skip(4);
        }
        lea.skip(4);
        byte bullets = lea.readByte();

        for (int j = 0; j < bullets; j++) {
            ret.allDamage.add(new AttackPair(Integer.valueOf(lea.readInt()).intValue(), null));
            lea.skip(2);
        }

        return ret;
    }
}
