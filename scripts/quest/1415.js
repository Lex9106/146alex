var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	
	if (status == 0) {
	    qm.sendNext("Wow. B�n �� th�c s� ph�t tri�n r�t nhi�u k� t� l�n cu�i t�i nh�n th�y b�n. Nh�ng b�n �� nghe v� c�ng vi�c m�i? ");
	} else if (status == 1) {
	    qm.sendNextPrev("�i tr�i th�t tuy�t! N� d�nh cho t�t c� c�c ph�p s� t� 30 tr� l�n!");
	} else if (status == 2) {
	    qm.askAcceptDecline("V� v�y ..... B�n c� mu�n ki�m tra k� n�ng c�a b�n ch�ng l�i k� th� m�nh m�, v� xem n�u b�n c� nh�ng g� n� c�n? T�t c� b�n c�n l� 30 Marbles t�i t� nh�ng con qu�i v�t! Cho ph�p �i.");
		qm.forceStartQuest();
	} else if (status == 3) {
	    if (!qm.haveItem(4031013, 30)) {
                qm.warp(910140000);//mage test
                qm.dispose();
	    }else {
		qm.dispose();
		}
            }
            }
            }

function end(mode, type, selection) {
    if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    if (qm.haveItem(4031013, 30) ) {
			qm.removeAll(4031013);
			qm.sendOk("Xin ch�c m�ng b�n b�y gi� l� m�t Fire / Poison!");
            qm.changeJob(210);
			//qm.gainSp(3);
			qm.forceCompleteQuest();
            qm.dispose();
	    }
	}
	}
}