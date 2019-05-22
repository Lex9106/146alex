function enter(pi) {
	if (pi.getQuestStatus(31252) > 0) {
    pi.warp(301030000,1);
	}
	else {
	pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}