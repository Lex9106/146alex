/*
	NPC Name: 		Athena Pierce
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
	    cm.sendNext("N�u b�n mu�n tr�i nghi�m nh�ng g� n� gi�ng nh� m�t Cung Th�, ��n g�p t�i m�t l�n n�a.");
	    cm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	cm.sendNext("Cung Th� ���c ban ph��c v�i kh�o l�o v� quy�n l�c, ch�u tr�ch nhi�m v� c�c cu�c t�n c�ng ���ng d�i, cung c�p h� tr� cho nh�ng ng��i � h�ng ��u c�a tr�n chi�n. R�t gi�i trong vi�c s� d�ng c�nh quan nh� m�t ph�n c�a kho v� kh�.");
    } else if (status == 1) {
	cm.sendYesNo("B�n mu�n xem k� n�ng c�a Bowman?");
    } else if (status == 2) {
	cm.MovieClipIntroUI(true);
	cm.warp(1020300, 0); // Effect/Direction3.img/archer/Scene00
	cm.dispose();
    }
}