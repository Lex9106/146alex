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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapleAchievements {

    private final Map<Integer, MapleAchievement> achievements = new LinkedHashMap<>();
    private static final MapleAchievements instance = new MapleAchievements();

    protected MapleAchievements() {
        achievements.put(1, new MapleAchievement("got their first point", 10, false));
        achievements.put(7, new MapleAchievement("reached 50 fame", 1000, false));
        achievements.put(9, new MapleAchievement("equipped a reverse item", 500, false));
        achievements.put(10, new MapleAchievement("equipped a timeless item", 1000, false));
        achievements.put(11, new MapleAchievement("said our server rocks", 500, false));
        achievements.put(12, new MapleAchievement("killed Female Boss", 1500, false));
        achievements.put(13, new MapleAchievement("killed Papulatus", 1500, false));
        achievements.put(14, new MapleAchievement("killed a Pianus", 500, false));
        achievements.put(15, new MapleAchievement("killed the almighty Zakum", 5000, false));
        achievements.put(16, new MapleAchievement("defeated Horntail", 10000, false));
        achievements.put(17, new MapleAchievement("defeated Pink Bean", 15000, false));
        achievements.put(18, new MapleAchievement("killed a boss", 1000, false));
        achievements.put(19, new MapleAchievement("won the event 'OX Quiz'", 50, false));
        achievements.put(20, new MapleAchievement("won the event 'MapleFitness'", 5, false));
        achievements.put(21, new MapleAchievement("won the event 'Ola Ola'", 50, false));
        achievements.put(22, new MapleAchievement("defeating BossQuest HELL mode", 5000));
        achievements.put(23, new MapleAchievement("killed the almighty Chaos Zakum", 10000, false));
        achievements.put(24, new MapleAchievement("defeated Chaos Horntail", 15000, false));
        achievements.put(25, new MapleAchievement("won the event 'Survival Challenge'", 50, false));
        achievements.put(26, new MapleAchievement("hit over 10,000 damage", 100, false));
        achievements.put(27, new MapleAchievement("hit over 50,000 damage", 500, false));
        achievements.put(28, new MapleAchievement("hit over 100,000 damage", 1000, false));
        achievements.put(29, new MapleAchievement("hit over 500,000 damage", 2000, false));
        achievements.put(30, new MapleAchievement("hit 1,000,000 damage", 5000, false));
        achievements.put(31, new MapleAchievement("got over 1 000 000 mesos", 100, false));
        achievements.put(32, new MapleAchievement("got over 10 000 000 mesos", 500, false));
        achievements.put(33, new MapleAchievement("got over 100 000 000 mesos", 1000, false));
        achievements.put(34, new MapleAchievement("got over 1 000 000 000 mesos", 2000, false));
        achievements.put(35, new MapleAchievement("made a guild", 250, false));
        achievements.put(36, new MapleAchievement("made a family", 250, false));
        achievements.put(37, new MapleAchievement("successfully beat the Crimsonwood Party Quest", 3000, false));
        achievements.put(38, new MapleAchievement("defeated Von Leon", 2000, false));
        achievements.put(39, new MapleAchievement("defeated Empress Cygnus", 10000, false));
        achievements.put(40, new MapleAchievement("equipped am item above level 130", 1000, false));
        achievements.put(41, new MapleAchievement("equipped am item above level 140", 1500, false));
        achievements.put(42, new MapleAchievement("for Completeing all Crusader Codex Sets, Congratulations!", 50000, true));
        achievements.put(43, new MapleAchievement("for Completeing 15 Crusader Codex Sets, Congratulations!", 20000, false));
        achievements.put(44, new MapleAchievement("for Completeing 10 Crusader Codex Sets, Congratulations!", 10000, false));
        achievements.put(45, new MapleAchievement("for Completeing 5 Crusader Codex Sets, Congratulations!", 5000, false));
        achievements.put(46, new MapleAchievement("for Completeing 3 Crusader Codex Sets, Congratulations!", 1000, false));
        achievements.put(47, new MapleAchievement("for Completeing 1 Crusader Codex Set, Congratulations!", 500, false));
        achievements.put(48, new MapleAchievement("for Defeating Gollux and all his parts!", 25000, false));
        achievements.put(49, new MapleAchievement("killed an elite boss", 3000, false));
        achievements.put(50, new MapleAchievement("hit 10,000,000 damage", 10000, false));
        achievements.put(51, new MapleAchievement("hit 100,000,000 damage", 15000, false));
        achievements.put(52, new MapleAchievement("hit 1,000,000,000 damage", 20000, false));
        achievements.put(53, new MapleAchievement("hit 10,000,000,000 damage, are you a hacker?", 30000, false));
    }

    public static MapleAchievements getInstance() {
        return instance;
    }

    public MapleAchievement getById(int id) {
        return achievements.get(id);
    }

    public Integer getByMapleAchievement(MapleAchievement ma) {
        for (Entry<Integer, MapleAchievement> achievement : this.achievements.entrySet()) {
            if (achievement.getValue() == ma) {
                return achievement.getKey();
            }
        }
        return null;
    }
}
