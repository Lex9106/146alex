/*
	NPC Name: 		Kyrin
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
	    cm.sendNext("N�u b�n mu�n tr�i nghi�m nh�ng g� n� gi�ng nh� m�t H�i T�c, ��n g�p t�i m�t l�n n�a.");
	    cm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	cm.sendNext("C��p ���c may m�n v�i s� kh�o l�o v� s�c m�nh xu�t s�c, s� d�ng s�ng c�a h� cho c�c cu�c t�n c�ng t�m xa trong khi s� d�ng s�c m�nh c�a h� tr�n c�c t�nh hu�ng chi�n ��u c�n chi�n. Gunslingers s� d�ng ��n d�a tr�n nguy�n t� �� t�ng s�t th��ng, trong khi Infighters bi�n th�nh m�t sinh v�t kh�c nhau cho hi�u �ng t�i �a.");
    } else if (status == 1) {
	cm.sendYesNo("B�n mu�n xem k� n�ng c�a Pirate?");
    } else if (status == 2) {
	cm.MovieClipIntroUI(true);
	cm.warp(1020500, 0); // Effect/Direction3.img/pirate/Scene00
	cm.dispose();
    }
}