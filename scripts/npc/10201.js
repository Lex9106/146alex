/*
	NPC Name: 		Grendel the Really Old
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
	    cm.sendNext("N�u b�n mu�n tr�i nghi�m nh�ng g� n� gi�ng nh� m�t Ph� Th�y, ��n g�p t�i m�t l�n n�a.");
	    cm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	cm.sendNext("C�c nh� �o thu�t ���c trang b� c�c ph�p thu�t d�a tr�n y�u t� h�o nho�ng v� ph�p thu�t th� c�p h� tr� cho c� nh�m. Sau c�ng vi�c th� hai, ph�p thu�t d�a tr�n nguy�n t� s� cung c�p l��ng s�t th��ng l�n cho k� ��ch c�a y�u t� ��i l�p.");
    } else if (status == 1) {
	cm.sendYesNo("B�n mu�n xem k� n�ng c�a Magician?");
    } else if (status == 2) {
	cm.MovieClipIntroUI(true);
	cm.warp(1020200, 0); // Effect/Direction3.img/magician/Scene00
	cm.dispose();
    }
}