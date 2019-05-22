function enter(pi) {
	var returnMap = pi.getSavedLocation("MULUNG_TC");
	if (returnMap < 0 || returnMap == 950000100) {
		returnMap = 100000000; // to fix people who entered the fm trough an unconventional way
	}
	if (pi.getPlayer().isSuperGM()) {
	pi.getPlayer().dropMessage(-5,"" + returnMap)
	}
	pi.clearSavedLocation("MULUNG_TC");
	pi.warp(returnMap, "unityPortal2"); 
	return true;
}