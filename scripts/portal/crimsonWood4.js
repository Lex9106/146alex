function enter(pi) {
	if (pi.getQuestStatus(31255) > 0) {
    pi.warp(301040000,1);
	}
	else {
	pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}