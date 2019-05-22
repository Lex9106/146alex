function enter(pi) {
	if (pi.getQuestStatus(31251) == 1) {
	pi.resetMap(301050200);
    pi.warp(301050200,3);
	}
	else if (pi.getQuestStatus(31254) == 1) {
	pi.resetMap(301050300);
    pi.warp(301050300,0);
	}
	else {
	pi.getPlayer().dropMessage(5,"This is the Mini Game Room, but you can't go at the moment.");
	}
}