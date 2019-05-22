/* Dawnveil
	Portal in Tynerum
    Made by Daenerys
*/
function enter(pi) {
	if (17500 >= 1) {
    pi.playPortalSE();
    pi.warp(863100008,"west00");
    return true;
	}
	else {
	pi.getPlayer().dropMessage(5,"You may not enter.");
	}
}