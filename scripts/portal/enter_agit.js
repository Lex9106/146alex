function enter(pi) {
	if (pi.getQuestStatus(31001) == 2) {
    pi.warp(200100010, 0);
	}
	else {
	pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}