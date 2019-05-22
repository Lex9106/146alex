/* Dawnveil
	Ellinel Fairy Academy
    Made by Daenerys
*/
function enter(pi) {
    pi.playPortalSE();
    if (pi.getMap().getAllMonstersThreadsafe().size() == 0) {
    pi.warp(301000000,10);
	} else {
    pi.getPlayer().dropMessage(5,"You must kill all the monsters.");
	}
}