var status = -1;

function end(mode, type, selection) {
	start(mode,type,selection);
}

function end(mode, type, selection) {
    if (mode == 0) {
	if (status == 0) {
	    qm.sendNext("This is an important decision to make.");
	    qm.safeDispose();
	    return;
	}
	status--;
    } else {
	status++;
    }
    if (status == 0) {
	qm.sendYesNo("B�n �� quy�t ��nh ch�a? Quy�t ��nh s� l� quy�t ��nh cu�i c�ng, v� v�y h�y suy ngh� c�n th�n tr��c khi quy�t ��nh ph�i l�m g�. B�n c� ch�c ch�n mu�n tr� th�nh m�t �c qu�?");
    } else if (status == 1) {
	qm.sendNextPrevS("T�i v�a ��c c� th� c�a b�n �� l�m cho n� ho�n h�o cho m�t �c qu�. N�u b�n mu�n tr� n�n m�nh h�n, h�y s� d�ng Stat Window (S) �� t�ng s� li�u th�ng k� th�ch h�p. N�u b�n kh�ng ch�c ch�n nh�ng g� �� n�ng cao,h�y �n Auto!", 1);
	if (qm.getJob() == 3110) {
		qm.teachSkill(31110010, 1, 1);
	    qm.changeJob(3111);
		qm.teachSkill(30011109, 1, 1);
	}
	qm.forceCompleteQuest();
    } else if (status == 2) {
	qm.sendNextPrev("B�n h�y ch�m ch� luy�n t�p �� gi�i h�n nh�.");
    } else if (status == 3) {
	qm.sendNextPrev("B�y gi� b�n c� th� �i.");
	qm.safeDispose();
    }
}