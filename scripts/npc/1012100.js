/* Athena Pierce
	Bowman Job Advancement
	Victoria Road : Bowman Instructional School (100000201)

	Custom Quest 100000, 100002
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
	    if (cm.getPlayerStat("LVL") >= 10 && cm.getJob() == 0) {
		cm.sendNext("V� v�y, b�n quy�t ��nh tr� th�nh m�t #rCung Th�#k?");
	    } else {
		cm.sendOk("��o t�o nhi�u h�n m�t ch�t v� t�i c� th� ch� cho b�n c�ch train c�a #rCung Th�#k.")
		cm.dispose();
	    }
	} else {
	    if (cm.getPlayerStat("LVL") >= 30 && cm.getJob() == 300) { // BOWMAN
			if (cm.getQuestStatus(100000) >= 1) {
				cm.completeQuest(100002);
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
		    cm.sendOk("���c r�i, b�y gi� h�y n�i chuy�n v�i #bRene#k.");
		} else {
		    cm.sendOk("Hey, #b#h0##k! T�i c�n m�t #bB�a �en#k.H�y �i t�m l� h�ng kh�ng gian.N� ch� ��u �� quanh ��y.");
		    cm.forceStartQuest(100101);
		}
		cm.dispose();
	    } else {
		cm.sendOk("B�n �� l�a ch�n kh�n ngoan.");
		cm.dispose();
	    }
	}
    } else if (status == 1) {
		cm.sendNextPrev("�� l� m�t l�a ch�n quan tr�ng. B�n s� kh�ng th� quay l�i.");
    } else if (status == 2) {
		cm.sendNext("V� v�y, b�n quy�t ��nh tr� th�nh m�t #rCung Th�#k?");
    } else if (status == 3) {
	if (cm.getJob() == 0) {
	    cm.resetStats(4, 25, 4, 4);
	    cm.expandInventory(1, 4);
	    cm.expandInventory(4, 4);
	    cm.gainSp(1);
	    cm.changeJob(300); // BOWMAN
	}
	cm.gainItem(1452002, 1);
	cm.gainItem(2060000, 1000);
	cm.sendOk("V� v�y, ���c n�! B�y gi� �i, v� �i v�i ni�m ki�u h�nh.");
	cm.dispose();
    } else if (status == 11) {
	cm.sendNextPrev("Ti�p theo b�n s� ���c l�a ch�n #rCung Th�#k ho�c #rN� Th�#k.")
    } else if (status == 12) {
	cm.askAcceptDecline("Nh�ng tr��c ti�n t�i ph�i ki�m tra k� n�ng c�a b�n. B�n �� s�n s�ng ch�a?");
    } else if (status == 13) {
	cm.forceStartQuest(100000);
	//cm.gainItem(4031010, 1);
	cm.sendOk("H�y n�i chuy�n v�i t�i m�t l�n n�a.");
	cm.dispose();
	
    } else if (status == 31) {
		cm.warp(910070000, 0);
		cm.dispose();
		
		
    } else if (status == 21) {
	cm.sendSimple("B�n mu�n l�a ch�n?#b\r\n#L0#Cung Th�#l\r\n#L1#N� Th�#l#k");
    } else if (status == 22) {
	var jobName;
	if (selection == 0) {
	    jobName = "Hunter";
	    job = 310; // HUNTER
	} else {
	    jobName = "Crossbowman";
	    job = 320; // CROSSBOWMAN
	}
	cm.sendYesNo("B�n s� ���c chuy�n th�nh #r" + jobName + "#k?");
    } else if (status == 23) {
	cm.changeJob(job);
	cm.gainSp(1);
	cm.removeAll(4031013);
	cm.sendOk("V� v�y, ���c n�! B�y gi� �i, v� �i v�i ni�m ki�u h�nh.");
    }
}	
