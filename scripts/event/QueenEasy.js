function init() {
    // 0 = Not started, 1 = started, 2 = first head defeated, 3 = second head defeated
	em.setProperty("leader", "true");
	em.setProperty("leader", "true");
}

function setup(eim, leaderid) {
    em.setProperty("state", "1");
    em.setProperty("preheadCheck", "0");
	em.setProperty("leader", "true");

    var eim = em.newInstance("QueenEasy");
		var map = eim.setInstanceMap(105200310);
    map.resetFully();
    eim.startEventTimer(1* 1800000); //now changed to 1 hour 15 mins
    return eim;
}

function playerEntry(eim, player) {
    var map = eim.getMapFactory().getMap(105200310);
    player.changeMap(map, map.getPortal(0));
}

function playerRevive(eim, player) {
    player.addHP(1000);
    var map = eim.getMapFactory().getMap(105200310);
    player.changeMap(map, map.getPortal(0));
    return true;
}

function changedMap(eim, player, mapid) {
    switch (mapid) {
	case 105200310:
	    return;
    }
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(0, 0)) {
	em.setProperty("state", "0");
		em.setProperty("leader", "true");
    }
}

function playerDisconnected(eim, player) {
eim.unregisterPlayer(player);
    return 0;
}

function monsterValue(eim, mobId) {	
		return 1;
}

function scheduledTimeout(eim) {
    eim.disposeIfPlayerBelow(100, 105200310);
    em.setProperty("state", "0");
		em.setProperty("leader", "true");
}

function end(eim) {
    if (eim.disposeIfPlayerBelow(100, 105200310)) {
	em.setProperty("state", "0");
		em.setProperty("leader", "true");
   // eim.broadcastPlayerMsg(5, "Enter the Portal to your left, to leave.");
    }
}

function clearPQ(eim) {
    end(eim);
}

function allMonstersDead(eim) {
eim.changeMusic("Bgm13/CokeTown");
}

function playerRevive(eim, player) {
    return false;
}

function clearPQ(eim) {}
function leftParty (eim, player) {}
function disbandParty (eim) {}
function playerDead(eim, player) {}
function cancelSchedule() {}