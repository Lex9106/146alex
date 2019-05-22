function enter(pi) {
	if (pi.getQuestStatus(32190) == 1) {
    pi.resetMap(141050300);
    pi.warp(141050300,1);
	} else {
	pi.getPlayer.dropMessage(5,"Unavailable.");	
	}
}