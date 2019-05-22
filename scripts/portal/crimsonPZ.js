function enter(pi) {
	if (pi.getQuestStatus(31260) > 0) {
	pi.warp(301060000,2);
	}
	else {
    pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}