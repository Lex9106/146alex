function enter(pi) {
    if (pi.getQuestStatus(21011) == 2) {
	pi.playPortalSE();
	pi.warp(140090300, 1);
    } else {
	pi.playerMessage(5, "B�n h�y ho�n th�nh nhi�m v� tr��c khi di chuy�n sang b�n �� m�i.");
    }
}