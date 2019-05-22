function enter(pi) {
	if (pi.getQuestStatus(31258) > 0) {
	pi.warp(301070010,4);
	}
	else {
    pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}