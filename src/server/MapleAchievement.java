/*
 This file is part of the OdinMS Maple Story Server
 Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc>
 Matthias Butz <matze@odinms.de>
 Jan Christian Meyer <vimes@odinms.de>

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License version 3
 as published by the Free Software Foundation. You may not use, modify
 or distribute this program under any other version of the
 GNU Affero General Public License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package server;

import client.MapleCharacter;
import constants.GameConstants;
import handling.world.World;
import tools.packet.CField;
import tools.packet.CWvsContext;

/**
 *
 * @author KyleShum
 */
public class MapleAchievement {

    private String name;
    private int reward;
    private final boolean notice;

    public MapleAchievement(String name, int reward) {
        this.name = name;
        this.reward = reward;
        this.notice = true;
    }

    public MapleAchievement(String name, int reward, boolean notice) {
        this.name = name;
        this.reward = reward;
        this.notice = notice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public boolean getNotice() {
        return notice;
    }

    public void finishAchievement(MapleCharacter chr) {
        chr.modifyCSPoints(1, reward, false);
        chr.setAchievementFinished(MapleAchievements.getInstance().getByMapleAchievement(this));
        if (notice) {
            chr.getClient().getSession().write(CField.showEffect("Yut/goal"));
            chr.getClient().getSession().write(CField.showEffect("fruitCollect/Success"));
            chr.getClient().getSession().write(CField.playSound("AchievementEff/AchievmentComplete"));
            World.Broadcast.broadcastMessage(CWvsContext.broadcastMsg(6, "[Achievement] Congratulations to " + chr.getName() + " on " + name + " and rewarded with " + (GameConstants.GMS ? (reward / 2) : reward) + " cash!"));
        } else {
            chr.dropMessage(-6, "[Achievement] You've gained " + (GameConstants.GMS ? (reward / 2) : reward) + " Cash as you " + name + ".");
            chr.getClient().getSession().write(CField.showEffect("fruitCollect/Success"));
            chr.getClient().getSession().write(CField.showEffect("Yut/goal"));
            chr.getClient().getSession().write(CField.playSound("AchievementEff/AchievmentComplete"));
        }
    }
}
