function enter(pi) {
	if (pi.getQuestStatus(31274) > 0) {
	pi.warp(930100000,3);
	}
	else {
    pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}