/* guild creation npc */
var status = -1;
var sel;

function start() {
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
	cm.sendSimple("B�n mu�n l�m g�?\r\n#b#L0#T�o Bang H�i#l\r\n#L1#X�a Bang H�i#l\r\n#L2#T�ng th�nh vi�n c�a Bang H�i l�n 100#l\r\n#L3#T�ng th�nh vi�n c�a Bang H�i l�n 200#l#k");
    else if (status == 1) {
	sel = selection;
	if (selection == 0) {
	    if (cm.getPlayerStat("GID") > 0) {
		cm.sendOk("B�n kh�ng th� t�o h�i m�i khi b�n �ang c� m�t h�i.");
		cm.dispose();
	    } else
		cm.sendYesNo("T�o Bang H�i v�i ph� #b500,000 mesos#k, b�n c� mu�n ti�p t�c?");
	} else if (selection == 1) {
	    if (cm.getPlayerStat("GID") <= 0 || cm.getPlayerStat("GRANK") != 1) {
		cm.sendOk("B�n c� th� h�y H�i n�u b�n l� ch� H�i.");
		cm.dispose();
	    } else
		cm.sendYesNo("B�n c� mu�n ti�p t�c h�y h�i c�a m�nh.�i�m GP c�a b�n s� m�t h�t.");
	} else if (selection == 2) {
	    if (cm.getPlayerStat("GID") <= 0 || cm.getPlayerStat("GRANK") != 1) {
		cm.sendOk("B�n c� th� t�ng t�nh n�ng c�a H�i n�u b�n l� ch� H�i.");
		cm.dispose();
	    } else
		cm.sendYesNo("Increasing your Guild capacity by #b5#k gi� #b500,000 mesos#k, are you sure you want to continue?");
	} else if (selection == 3) {
	    if (cm.getPlayerStat("GID") <= 0 || cm.getPlayerStat("GRANK") != 1) {
		cm.sendOk("You can only increase your Guild's capacity if you are the leader.");
		cm.dispose();
	    } else
		cm.sendYesNo("T�ng s� l��ng th�nh vi�n c�a Bang H�i l�n #b5#k gi� #b25,000 GP#k, B�n c� mu�n ti�p t�c?");
	}
    } else if (status == 2) {
	if (sel == 0 && cm.getPlayerStat("GID") <= 0) {
	    cm.genericGuildMessage(1);
	    cm.dispose();
	} else if (sel == 1 && cm.getPlayerStat("GID") > 0 && cm.getPlayerStat("GRANK") == 1) {
	    cm.disbandGuild();
	    cm.dispose();
	} else if (sel == 2 && cm.getPlayerStat("GID") > 0 && cm.getPlayerStat("GRANK") == 1) {
	    cm.increaseGuildCapacity(false);
	    cm.dispose();
	} else if (sel == 3 && cm.getPlayerStat("GID") > 0 && cm.getPlayerStat("GRANK") == 1) {
	    cm.increaseGuildCapacity(true);
	    cm.dispose();
	}
    }
}