/* Dances with Balrog
	Warrior Job Advancement
	Victoria Road : Warriors' Sanctuary (102000003)

	Custom Quest 100003, 100005
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
		cm.sendNext("V� v�y, b�n quy�t ��nh tr� th�nh m�t #rChi�n binh#k?");
	    } else {
		cm.sendOk("��o t�o nhi�u h�n m�t ch�t v� t�i c� th� ch� cho b�n c�ch train c�a #rChi�n binh#k.");
		cm.dispose();
	    }
	} else {
	    if (cm.getPlayerStat("LVL") >= 30 && cm.getJob() == 100) { // WARROPR
		if (cm.getQuestStatus(100003) >= 1) {
		    cm.completeQuest(100005);
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
		cm.dispose();
	    } else if (cm.getQuestStatus(100100) == 1) {
		cm.completeQuest(100101);
		if (cm.getQuestStatus(100101) == 2) {
		    cm.sendOk("���c r�i, b�y gi� h�y n�i chuy�n v�i #bTylus#k.");
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
		cm.sendNext("V� v�y, b�n quy�t ��nh tr� th�nh m�t #rChi�n binh#k?");
    } else if (status == 3) {
	if (cm.getJob() == 0) {
	    cm.resetStats(4, 4, 4, 4);
	    cm.expandInventory(1, 4);
	    cm.expandInventory(4, 4);
	    cm.gainSp(1);
	    cm.changeJob(100); // WARRIOR
	}
	cm.gainItem(1402001, 1);
	cm.sendOk("V� v�y, ���c n�! B�y gi� �i, v� �i v�i ni�m ki�u h�nh.");
	cm.dispose();
    } else if (status == 11) {
	cm.sendNextPrev("Ti�p theo b�n s� ���c l�a ch�n #rKi�m S�#k, #rK� S�#k ho�c #rTh��ng S�#k.")
    } else if (status == 12) {
	cm.askAcceptDecline("Nh�ng tr��c ti�n t�i ph�i ki�m tra k� n�ng c�a b�n. B�n �� s�n s�ng ch�a?");
    } else if (status == 13) {
	//cm.gainItem(4031008, 1);
	cm.forceStartQuest(100003);
	cm.sendOk("H�y n�i chuy�n v�i t�i m�t l�n n�a.");
	cm.dispose();
	
    } else if (status == 31) {
		cm.warp(910230000, 0);
		cm.dispose();
	
    } else if (status == 21) {
	cm.sendSimple("B�n mu�n l�a ch�n?#b\r\n#L0#Ki�m S�#l\r\n#L1#K� S�#l\r\n#L2#Th��ng S�#l#k");
    } else if (status == 22) {
	var jobName;
	if (selection == 0) {
	    jobName = "Fighter";
	    job = 110; // FIGHTER
	} else if (selection == 1) {
	    jobName = "Page";
	    job = 120; // PAGE
	} else {
	    jobName = "Spearman";
	    job = 130; // SPEARMAN
	}
	cm.sendYesNo("B�n s� ���c chuy�n th�nh #r" + jobName + "#k?");
    } else if (status == 23) {
	cm.changeJob(job);
	cm.removeAll(4031013);
	cm.gainSp(1);
	cm.sendOk("V� v�y, ���c n�! B�y gi� �i, v� �i v�i ni�m ki�u h�nh.");
	cm.dispose();
    }
}	
