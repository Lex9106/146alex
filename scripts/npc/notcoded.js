function action(mode, type, selection) {
	if (cm.getNpc() >= 9901000) {
		cm.sendNext("Hello #h0#, I am in the Hall of Fame for reaching LEVEL 200.");
	} else {
		cm.sendNext("T�i kh�ng c� d� li�u.Vui l�ng li�n h� GM..");
	}
	cm.safeDispose();
}