/* guild emblem npc */
var status = 0;
var sel;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;

    if (status == 0)
	cm.sendSimple("B�n mu�n t�i gi�p g�?\r\n#b#L0#T�o/Thay ��i bi�u t��ng H�i#l#k");
    else if (status == 1) {
	sel = selection;
	if (selection == 0) {
	    if (cm.getPlayerStat("GRANK") == 1)
		cm.sendYesNo("T�o bi�u t��ng ho�c thay ��i v�i chi ph� #b1,500,000 mesos#k, B�n c� mu�n ti�p t�c?");
	    else
		cm.sendOk("B�n ch� c� th� l�m c�ng vi�c n�y n�u b�n l� ch� H�i.");
	}
				
    } else if (status == 2) {
	if (sel == 0) {
	    cm.genericGuildMessage(17);
	    cm.dispose();
	}
    }
}
