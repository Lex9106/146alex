var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
         status++;
    } else {
	cm.dispose();
	return;
    }
    if (status == 0) {
    	cm.sendNext("#b(Th�t l� m�t c�i l� ��ng ng�. C� l� Von �ang tr�n b�n trong. Peek b�n trong?)#k");
    } else if (status == 1) {
        cm.gainExp(35);
        cm.warp(931000010,0);
    	cm.dispose();
    }
}