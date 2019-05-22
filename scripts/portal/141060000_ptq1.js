function enter(pi) {
	if (pi.getQuestStatus(32194) != 2) {
	pi.resetMap(141010400);
    pi.warp(141010400,1);
	pi.openNpc(1514002);
	}
}