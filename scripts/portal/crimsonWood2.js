function enter(pi) {
	if (pi.getQuestStatus(31249) > 0) {
    pi.warp(301020000,1);
	}
	else {
	pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}