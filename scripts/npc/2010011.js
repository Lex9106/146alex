var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 0) {
	    cm.dispose();
	}
	status--;
    }
    if (status == 0) {
	cm.sendYesNo("B�n mu�n �i ��n tr� s� Bang H�i?");
    } else if (status == 1) {
	cm.warp(200000301);
	cm.dispose();
    }
}