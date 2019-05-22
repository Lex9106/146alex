
function enter(pi) {
	if (pi.getQuestStatus(3870) == 1) {
	pi.resetMap(925120000);	
    pi.warp(925120000,0);
	pi.Dispose();
	}
else {
pi.getPlayer().dropMessage(5,"You may not enter");
}
}
