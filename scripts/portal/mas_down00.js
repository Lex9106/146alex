function enter(pi) {
	if (pi.getQuestStatus(17515) >= 1) {
    pi.warp(863100006,2);
	}
	else {
	pi.getPlayer.dropMessage(5,"You may not enter.");
	pi.Dispose();
	}
}