// Phantom Warper : To Henesys

function start() {
	cm.sendYesNo("B�n mu�n t�i Th� tr�n xanh?");
}

function action (m, t, s) {
  if (m > 0) {
	cm.warp(100000000, 0);
  }
  cm.dispose();
}