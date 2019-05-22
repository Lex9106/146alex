function enter(pi) {
	if (pi.getQuestStatus(2975) == 1 && pi.getQuestStatus(7) != 2) {
		pi.resetMap(120041900);
		pi.warp(120041900,1);
		pi.forceCompleteQuest(7);
	}
	else {
	pi.getPlayer().dropMessage(5,"Not Available.");	
	}
}