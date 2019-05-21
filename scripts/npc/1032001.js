/* Grendel the Really Old
	Magician Job Advancement
	Victoria Road : Magic Library (101000003)

	Custom Quest 100006, 100008, 100100, 100101
*/

var status = 0;
var job;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 2) {
	cm.sendOk("H�y l�m ��p v� t�i g�p t�i.");
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	if (cm.getJob() == 0) {
	    if (cm.getPlayerStat("LVL") >= 8) {
		cm.sendNext("V� v�y, b�n quy�t ��nh tr� th�nh m�t #rPh� Th�y#k?");
	    } else {
		cm.sendOk("��o t�o nhi�u h�n m�t ch�t v� t�i c� th� ch� cho b�n c�ch tr� th�nh #rMagician#k.")
		cm.dispose();
	    }
	} else {
	    if (cm.getPlayerStat("LVL") >= 30 && cm.getJob() == 200) { // MAGICIAN
			if (cm.getQuestStatus(100006) >= 1) {
				cm.completeQuest(100008);
				if (cm.haveItem(4031013, 30)) {
					status = 20;
					cm.sendNext("T�i th�y b�n �� l�m t�t. T�i s� �� b�n th�c hi�n b��c ti�p theo tr�n con ���ng d�i c�a b�n.");
				} else {
					status = 30;
					cm.sendNext("H�y thu th�p 30 vi�n #i4031013##t4031013#.")
				}
			} else {
				status = 10;
				cm.sendNext("Ti�n tr�nh b�n �� th�c hi�n l� ��ng kinh ng�c.");
			}
	    } else if (cm.getQuestStatus(100100) == 1) {
		cm.completeQuest(100101);
		if (cm.getQuestStatus(100101) == 2) {
		    cm.sendOk("���c r�i, b�y gi� h�y l�m �i�u n�y #bRobeira#k.");
		} else {
		    cm.sendOk("Hey, #b#h0##k! T�i c�n m�t #bB�a �en#k.B�n c� th� qua khu ph� th�y bay t�m l� h�ng kh�ng gian �� v�o �� l�y cho t�i.�i sang tr�i l�i b�n d��i �� t�m n�.");
		    cm.forceStartQuest(100101);
		}
		cm.dispose();
	    } else {
		cm.sendOk("B�n �� ch�n m�t c�ch kh�n ngoan.");
		cm.dispose();
	    }
	}
    } else if (status == 1) {
	cm.sendNextPrev("�� l� m�t l�a ch�n quan tr�ng. B�n s� kh�ng th� quay l�i.");
    } else if (status == 2) {
	cm.sendYesNo("B�n mu�n tr� th�nh #rPh� Th�y#k?");
    } else if (status == 3) {
	if (cm.getJob() == 0) {
	    cm.resetStats(4, 4, 20, 4);
	    cm.expandInventory(1, 4);
	    cm.expandInventory(4, 4);
	    cm.gainSp(1);
	    cm.changeJob(200); // MAGICIAN
	}
	cm.gainItem(1372005, 1);
	cm.sendOk("V� v�y, ���c n�! B�y gi� �i, v� �i v�i ni�m ki�u h�nh.");
	cm.dispose();
    } else if (status == 11) {
	cm.sendNextPrev("B�n c� th� s�n s�ng th�c hi�n b��c ti�p theo v�i t� c�ch l� m�t #rPh� th�y l�a ��c#k, #rPh� th�y b�ng s�m#k or #rTu S�#k.");
    } else if (status == 12) {
	cm.askAcceptDecline("Nh�ng tr��c ti�n t�i ph�i ki�m tra k� n�ng c�a b�n. B�n �� s�n s�ng ch�a?");
    } else if (status == 13) {
	cm.forceStartQuest(100006);
	//cm.gainItem(4031009, 1);
	cm.sendOk("H�y �i sang b�n tr�i l�i b�n tr�n 2 b�n ��.Leo l�n tr�n c�ng �� g�p #rTh�y Ph� Th�y#k.");
	cm.dispose();
    } else if (status == 31) {
		cm.warp(910140000, 0);
		cm.dispose();
    } else if (status == 21) {
		cm.sendSimple("B�n mu�n tr� th�nh?#b\r\n#L0#Ph� th�y l�a ��c#l\r\n#L1#Ph� th�y b�ng s�m#l\r\n#L2#Tu S�#l#k");
    } else if (status == 22) {
	var jobName;
	if (selection == 0) {
	    jobName = "Fire/Poison Wizard";
	    job = 210; // FP
	} else if (selection == 1) {
	    jobName = "Ice/Lightning Wizard";
	    job = 220; // IL
	} else {
	    jobName = "Cleric";
	    job = 230; // CLERIC
	}
	cm.sendYesNo("B�n mu�n tr� th�nh m�t #r" + jobName + "#k?");
    } else if (status == 23) {
	cm.changeJob(job);
	cm.removeAll(4031013);
	cm.gainSp(1);
	cm.sendOk("V� v�y, ���c n�! B�y gi� �i, v� �i v�i ni�m ki�u h�nh.");
	cm.dispose();
    }
}	
