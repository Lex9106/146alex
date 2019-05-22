function enter(pi) {
	if (pi.getPlayer().haveItem(1003609) || pi.getPlayer().hasEquipped(1003609)) {
	pi.forceCompleteQuest(10);
	}
	pi.warp(231040400,2);
}