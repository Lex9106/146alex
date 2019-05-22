function enter(pi) {
	if (pi.getQuestStatus(2671) != 2) {
    pi.playPortalSE();
	pi.resetMap(103040430);
    pi.warp(pi.getPlayer().getMapId() + 10,"right01");
	}
}