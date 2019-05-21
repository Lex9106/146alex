/*
	NPC Name: 		Dark Lord
	Map(s): 		Maple Road : Spilt road of choice
	Description: 		Job tutorial, movie clip
*/

var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 1) {
	    cm.sendNext("N�u b�n mu�n tr�i nghi�m nh�ng g� n� gi�ng nh� m�t Du Hi�p, ��n g�p t�i m�t l�n n�a.");
	    cm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	cm.sendNext("K� tr�m l� m�t s� pha tr�n ho�n h�o c�a may m�n, kh�o l�o, v� s�c m�nh m� l� l�o luy�n t�i c�c cu�c t�n c�ng b�t ng� ch�ng l�i k� th� b�t l�c. M�c �� tr�nh v� t�c �� cao cho ph�p k� tr�m t�n c�ng k� ��ch b�ng c�c g�c kh�c nhau.");
    } else if (status == 1) {
	cm.sendYesNo("B�n mu�n xem k� n�ng c�a Thief?");
    } else if (status == 2) {
	cm.MovieClipIntroUI(true);
	cm.warp(1020400, 0); // Effect/Direction3.img/rouge/Scene00
	cm.dispose();
    }
}