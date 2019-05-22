function enter(pi) {
	if (pi.getPlayer().getQuestStatus(4325) == 1 && pi.getPlayer().getQuestStatus(10) != 2) {
	pi.resetMap(231050000);	
    pi.warp(231050000,1);
	}
	else {
	pi.getPlayer().dropMessage(5,"Unavailable.");
	}
}