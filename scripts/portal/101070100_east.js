/* Dawnveil
    Ellinel Fairy Academy
    Made by Daenerys
*/
function enter(pi) {
    pi.playPortalSE();
    if (pi.getQuestStatus(32102)==2){
	    pi.warp(101070010,2);
	} else {
		pi.warp(101070000,2);
        pi.dispose();
    }
    return true;
}