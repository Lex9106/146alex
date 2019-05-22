function enter(pi) {
	if (pi.getQuestStatus(32187) == 1 || pi.getQuestStatus(32187) == 2) {
    pi.warp(141050000,2);
	pi.forceCompleteQuest(32187);
	}
}