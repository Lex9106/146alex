function enter(pi) {
	if (pi.isQuestActive(2324) || pi.getQuestStatus(2324) == 2) {
	    pi.forceCompleteQuest(2324);
	    pi.removeAll(2430015);
	    pi.playerMessage("You've used the thorns remover.");
		pi.warp(106020501,1);
		pi.forceCompleteQuest(2332);
	}
	else {
    pi.playerMessage("You can see that the next portal is full of thorns and may not procced.");
	}
}