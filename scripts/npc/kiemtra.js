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
	if (cm.getMap().getAllMonstersThreadsafe().size() <= 0) {
	    cm.sendOk("Kh�ng c� qu�i v�t trong b�n ��.");
	    cm.dispose();
	    return;
	}
	var selStr = "H�y ch�n qu�i v�t b�n d��i �� xem t� l� r�i ��.\r\n\r\n#b";
	var iz = cm.getMap().getAllUniqueMonsters().iterator();
	while (iz.hasNext()) {
	    var zz = iz.next();
	    selStr += "#L" + zz + "##o" + zz + "# - Id : "+ zz +"#l\r\n";
	} 
	cm.sendSimple(selStr);
    } else if (status == 1) {
	cm.sendNext(cm.checkDrop(selection));
	cm.dispose();
    }
}