/* Dawnveil
	Portal in Tynerum
    Made by Daenerys
*/
function enter(pi) {
	if (pi.getMap().getAllMonstersThreadsafe().size() == 0) {
    pi.playPortalSE();
    pi.warp(863100104,"west00");
	}
	else {
    pi.getPlayer().dropMessage(5,"You must kill all the monsters.");
	}
}