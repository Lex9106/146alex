/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.buffs.buffclasses.hero;

import client.MapleBuffStat;
import constants.GameConstants;
import server.MapleStatEffect;
import server.MapleStatInfo;
import server.buffs.AbstractBuffClass;

/**
 *
 * @author Maple
 */
public class LuminousBuff extends AbstractBuffClass {
    
    public LuminousBuff() {
        buffs = new int[]{
            27001004, //Mana Well
            27101004,
            27111004, //Shadow Shell
            27111005, //Dusk Guard
            27111006, //Photic Meditation
            27121006, //Arcane Pitch
            27100003, //Black Blessing
        };
    }
    
    @Override
    public boolean containsJob(int job) {
        return GameConstants.isLuminous(job);
    }

    @Override
    public void handleBuff(MapleStatEffect eff, int skill) {
        switch (skill) {
            case 27001004: // Mana Well
                eff.statups.put(MapleBuffStat.MP_R, eff.info.get(MapleStatInfo.indieMmpR));
                break;
            case 27101004://booster
                eff.statups.put(MapleBuffStat.BOOSTER, eff.info.get(MapleStatInfo.x));
                break;
            case 27111004: // Shadow Shell
                eff.info.put(MapleStatInfo.time, Integer.valueOf(2100000000));
                eff.statups.put(MapleBuffStat.RECHARGE, 1);
                break;
            case 27111005: // Dusk Guard
                eff.statups.put(MapleBuffStat.ENHANCED_WDEF, eff.info.get(MapleStatInfo.indiePdd));
                eff.statups.put(MapleBuffStat.ENHANCED_MDEF, eff.info.get(MapleStatInfo.indiePdd));
                eff.statups.put(MapleBuffStat.DUSK_GUARD, eff.info.get(MapleStatInfo.prop));
                break;
            case 27111006: // Photic Meditation
                eff.statups.put(MapleBuffStat.ENHANCED_MATK, eff.info.get(MapleStatInfo.emad));
                break;
           //case 27121005: // Dark Crescendo TODO: Count up GMS-Like
                //eff.info.put(MapleStatInfo.time, Integer.valueOf(180000));
                //eff.statups.put(MapleBuffStat.DARK_CRESCENDO, eff.info.get(MapleStatInfo.x));
                //eff.statups.put(MapleBuffStat.PRESSURE_VOID, eff.info.get(MapleStatInfo.x));
               // break;
            case 27121006: // Arcane Pitch
                eff.statups.put(MapleBuffStat.IGNORE_DEF, eff.info.get(MapleStatInfo.x));
                eff.statups.put(MapleBuffStat.FINALATTACK, eff.info.get(MapleStatInfo.y));
                break;
            case 27100003: //Black Blessing
                //TODO
                break;
            default:
                System.out.println("Unhandled Buff: " + skill);
                break;
        }
    }
}
