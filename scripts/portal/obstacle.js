function enter(pi) {
	    if (pi.haveItem(4000507,1)) {
		pi.warp(106020400,2);
		pi.gainItem(4000507,-1);
		}
		if (pi.haveItem(2430014,1)) {
		pi.warp(106020400,2);
		pi.forceCompleteQuest(13);
		pi.getPlayer().dropMessage(5,"You have removed the magic barrier.");
		pi.gainItem(2430014,-1);
		}
		if (pi.getQuestStatus(13) == 2) {
		pi.warp(106020400,2);
		}
		else {
        pi.getPlayer().dropMessage(5,"You can't procced.");
		}
}