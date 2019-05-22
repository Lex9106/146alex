/* Dawnveil
	Portal in Tynerum
    Made by Daenerys
*/
function enter(pi) {
	if (pi.getQuestStatus(17534) == 2) {
    pi.playPortalSE();
    pi.warp(863010000,1);
	}
	else {
    pi.getPlayer().dropMessage(5,"Unavailable.");
	}
}