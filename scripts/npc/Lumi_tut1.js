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
		//cm.sendDirectionStatus(3, 1);
		cm.sendPlayerToNpc("Ch�o m�ng b�n ��n v�i MapleLove.Ch�c b�n ch�i game vui v�");
    } else if (status == 1) {
		cm.showLumiVid();
		cm.dispose();
    } 
}