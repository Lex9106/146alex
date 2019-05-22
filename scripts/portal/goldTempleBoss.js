
function enter(pi) {
if (pi.getPlayer().haveItem(4033176,1) && pi.getQuestStatus(3863) != 2) {
pi.warp(252030100);
pi.gainItem(4033176,-1);
pi.spawnMonster(9100025, 895, 513);
} 
else if (pi.getPlayer().haveItem(4001684,1) && pi.getQuestStatus(3863) == 2) {
pi.resetMap(252030100);
pi.warp(252030100);
pi.gainItem(4001684,-1);
} 
else 
{
pi.getPlayer().dropMessage(5,"You need a Sunburst in order to enter.");	
}
}
