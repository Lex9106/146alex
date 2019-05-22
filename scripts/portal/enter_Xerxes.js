function enter(pi) {
if (pi.getQuestStatus(31013) == 1) {
pi.resetMap(200101500);
pi.warp(200101500);
}
else {
pi.getPlayer().dropMessage(5,"You may not enter.");
}
}