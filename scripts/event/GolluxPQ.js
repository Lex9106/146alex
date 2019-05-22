/* Gollux PQ
By AlexRmz
*/

importPackage(Packages.tools.packet);
importPackage(Packages.server.life);
importPackage(java.lang);
importPackage(java.awt);
importPackage(Packages.tools.RandomStream);
importPackage(Packages.main.world);
importPackage(Packages.tools.packet);

function init() {
em.setProperty("state", "0");
em.setProperty("leader", "true");
}

function setup(level, leaderid) {
	em.setProperty("state", "1");
	em.setProperty("leader", "true");
    var eim = em.newInstance("GolluxPQ" + leaderid);
    //863010000 Entrance
    eim.startEventTimer(1800000); //30 min
    return eim;
}

function playerEntry(eim, player) {
    var map = eim.getMapInstance(863010100);
    player.changeMap(map, map.getPortal(5));
}

function playerRevive(eim, player) {
}

function scheduledTimeout(eim) {
    end(eim);
}

function changedMap(eim, player, mapid) {

if (!(mapid >= 863010100 && mapid <= 863010700)) {
	eim.unregisterPlayer(player);
	if (eim.disposeIfPlayerBelow(0, 0)) {
		em.setProperty("state", "0");
		em.setProperty("leader", "true");
	}
    }
}

function playerDisconnected(eim, player) {
eim.unregisterPlayer(player);
    return 0;
}

function monsterValue(eim, mobId, player) {
	if (player.getMapId() == 863010420) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	}
} 
if (player.getMapId() == 863010200) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	}
} 
if (player.getMapId() == 863010210) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	}
} 
if (player.getMapId() == 863010300) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	}
} 
	if (player.getMapId() == 863010400) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	}
} 
if (player.getMapId() == 863010310) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	eim.broadcastPacket(CField.environmentChange("open",2));
    eim.broadcastPacket(CField.environmentChange("clear",2));
	}
} 
if (player.getMapId() == 863010320) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	eim.broadcastPacket(CField.environmentChange("open",2));
    eim.broadcastPacket(CField.environmentChange("clear",2));
	}
} 
if (player.getMapId() == 863010410) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	eim.broadcastPacket(CField.environmentChange("open",2));
    eim.broadcastPacket(CField.environmentChange("clear",2));
	}
} 
if (player.getMapId() == 863010220) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	}
} 
if (player.getMapId() == 863010230) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	}
}
if (player.getMapId() == 863010320) {
    if (player.getMap().getAllMonstersThreadsafe().size() > 3) {
    eim.broadcastPlayerMsg(-1, "There are " + (player.getMap().getAllMonstersThreadsafe().size()-3) + " doses of evil energy remaining in this area."); 
    }
	else {
	eim.broadcastPlayerMsg(-1, "There are 0 doses of evil energy remaining in this area."); 
    eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	}
}
if (player.getMapId() == 863010240) {//Abdomen
    if (player.getMap().getAllMonstersThreadsafe().size() == 0) {
    eim.broadcastPacket(CField.environmentChange("change",2));
	eim.broadcastPacket(CField.environmentChange("clear1",2));
    eim.broadcastPacket(CField.environmentChange("clear2",2));
	eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	var party = eim.getPlayers();
	for (var i = 0; i < party.size(); i++) {
	party.get(i).forceCompleteQuest(45);
	}
    }
}
if (player.getMapId() == 863010430) {
    if (player.getMap().getAllMonstersThreadsafe().size() == 0) {
	if (player.getQuestStatus(46) != 2) {
	eim.broadcastPlayerMsg(-1, "You demolished the Left Shoulder. Move through the tunnel."); 
	}
    eim.broadcastPacket(CField.environmentChange("clear",2));
	eim.broadcastPacket(CField.environmentChange("phase3",2));
	eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	var party = eim.getPlayers();
	for (var i = 0; i < party.size(); i++) {
	party.get(i).forceCompleteQuest(46);
	}
    }
}
if (player.getMapId() == 863010330) {
    if (player.getMap().getAllMonstersThreadsafe().size() == 0) {
	if (player.getQuestStatus(47) != 2) {
	eim.broadcastPlayerMsg(-1, "You demolished the Right Shoulder. Move through the tunnel."); 
	}
    eim.broadcastPacket(CField.environmentChange("clear",2));
	eim.broadcastPacket(CField.environmentChange("phase3",2));
	eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	var party = eim.getPlayers();
	for (var i = 0; i < party.size(); i++) {
	party.get(i).forceCompleteQuest(47);
	}
    }
}
if (player.getMapId() == 863010600) {
    if (player.getMap().getAllMonstersThreadsafe().size() == 1 && player.getQuestStatus(48) != 2 && player.getQuestStatus(49) != 2) {
	if (player.getQuestStatus(48) != 2) {
	eim.broadcastPlayerMsg(-1, "You have taken out Gollux's jaw. Strike the eyes.");
	eim.ApplyBuff(player,1146);
	eim.ApplyBuff(player,1142);
	eim.RemoveBuff(player,1146);
	var party = eim.getPlayers();
	for (var i = 0; i < party.size(); i++) {
	party.get(i).forceCompleteQuest(48);
	}
    }
	var map = eim.setInstanceMap(863010600);
	var mob = em.getMonster(9390601);
    eim.registerMonster(mob);
    map.spawnMonsterOnGroudBelow(mob, new java.awt.Point(41, -220));
	}
	else if (player.getMap().getAllMonstersThreadsafe().size() == 1 && player.getQuestStatus(48) == 2 && player.getQuestStatus(49) != 2) {
	if (player.getQuestStatus(49) != 2) {
	eim.broadcastPlayerMsg(-1, "The eyes have been destroyed, now attack on it's forehead.");
	var party = eim.getPlayers();
	for (var i = 0; i < party.size(); i++) {
	party.get(i).forceCompleteQuest(49);
	}
    }
	var map = eim.setInstanceMap(863010600);
	var mob = em.getMonster(9390602);
    eim.registerMonster(mob);
    map.spawnMonsterOnGroudBelow(mob, new java.awt.Point(41, -220));
	}
	else if (player.getMap().getAllMonstersThreadsafe().size() == 1 && player.getQuestStatus(48) == 2 && player.getQuestStatus(49) == 2) {
	eim.broadcastPacket(CField.showEffect("event/clear"));
	eim.broadcastPacket(CField.playSound("Party1/Clear"));
	var map = eim.setInstanceMap(863010600);
	eim.RemoveBuff(player,1142);
    map.spawnNpc(9390125, new java.awt.Point(-10, -220));
}
}
	return 1;
}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(0, 0)) {
	em.setProperty("state", "0");
		em.setProperty("leader", "true");
	}
}

function end(eim) {
    eim.disposeIfPlayerBelow(100, 863010000);
	em.setProperty("state", "0");
		em.setProperty("leader", "true");
}

function clearPQ(eim) {
    end(eim);
}

function allMonstersDead(eim) {
}

function leftParty (eim, player) {
    // If only 2 players are left, uncompletable:
	//end(eim);
}
function disbandParty (eim) {
	//end(eim);
}
function playerDead(eim, player) {
}
function cancelSchedule() {}