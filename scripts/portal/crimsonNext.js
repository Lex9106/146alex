function enter(pi) {
	if (pi.getQuestStatus(31258) == 1) {
	pi.resetMap(301070010);
	pi.warp(301070010,4);
	pi.spawnMonster(8148012,309,323);
	}
	else if (pi.getQuestStatus(31274) > 0) {
	pi.warp(301070000,4);
	}
	else {
    pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}