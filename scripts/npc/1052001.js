/* Dark Lord
	Thief Job Advancement
	Victoria Road : Thieves' Hideout (103000003)

	Custom Quest 100009, 100011
 */

var status = 0;
var job;

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 0 && status == 2) {
		cm.sendOk("B�n bi�t kh�ng c� l�a ch�n n�o kh�c...");
		cm.dispose();
		return;
	}
	if (mode == 1)
		status++;
	else
		status--;
	if (status == 0) {
		if (cm.getJob() >= 400 && cm.getJob() <= 434
				&& cm.getQuestStatus(2351) == 1) {
			cm.forceCompleteQuest(2351);
			cm.gainItem(1032076, 1); // owl earring
		}
		if (cm.getJob() == 0) {
			if (cm.getPlayerStat("LVL") >= 10 && cm.getJob() == 0)
				cm.sendNext("V� v�y, b�n quy�t ��nh tr� th�nh m�t #rDu Hi�p#k?");
			else {
				cm.sendOk("��o t�o nhi�u h�n m�t ch�t v� t�i c� th� ch� cho b�n c�ch train c�a ##rDu Hi�p#k.")
				cm.dispose();
			}
		} else {
			if (cm.getPlayerStat("LVL") >= 30 && cm.getJob() == 400) {
				if (cm.getQuestStatus(100009) >= 1) {
					cm.completeQuest(100011);
					if (cm.getQuestStatus(100011) == 2) {
						status = 20;
							cm.sendNext("T�i th�y b�n �� l�m t�t. T�i s� �� b�n th�c hi�n b��c ti�p theo tr�n con ���ng d�i c�a b�n.");
					} else {
						if (!cm.haveItem(4031011)) {
							cm.gainItem(4031011, 1);
						}
						cm.sendOk("�i t�m #rTh�y du hi�p#k.")
						cm.dispose();
					}
				} else {
					status = 10;
					cm.sendNext("Ti�n tr�nh b�n �� th�c hi�n l� ��ng kinh ng�c.");
				}
			} else if (cm.getQuestStatus(100100) == 1) {
				cm.completeQuest(100101);
				if (cm.getQuestStatus(100101) == 2) {
					cm.sendOk("���c r�i, b�y gi� h�y n�i chuy�n v�i #bArec#k.");
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
				cm.sendNext("V� v�y, b�n quy�t ��nh tr� th�nh m�t #rDu Hi�p#k?");
	} else if (status == 3) {
		if (cm.getJob() == 0) {
			cm.resetStats(4, 25, 4, 4);
			cm.expandInventory(1, 4);
			cm.expandInventory(4, 4);
			cm.gainSp(1);
			cm.changeJob(400); // THIEF
			if (cm.getQuestStatus(2351) == 1) {
				cm.forceCompleteQuest(2351);
				cm.gainItem(1032076, 1); // owl earring
			}
		}
		cm.gainItem(1332063, 1);
		cm.gainItem(1472000, 1);
		cm.gainItem(2070015, 500);
		cm.sendOk("V� v�y, ���c n�! B�y gi� �i, v� �i v�i ni�m ki�u h�nh.");
		cm.dispose();
	} else if (status == 11) {
		cm.sendNextPrev("Ti�p theo b�n s� ���c l�a ch�n #rTh�ch Kh�ch#k ho�c #rL�ng Kh�ch#k.");
	} else if (status == 12) {
		cm.askAcceptDecline("Nh�ng tr��c ti�n t�i ph�i ki�m tra k� n�ng c�a b�n. B�n �� s�n s�ng ch�a?");
	} else if (status == 13) {
		cm.forceStartQuest(100009);
		cm.gainItem(4031011, 1);
		cm.sendOk("�i t�m #bTh�y du hi�p#k Anh �y � ��u ��y quanh th�nh ph� h�n lo�n.");
		cm.dispose();
	} else if (status == 21) {
		cm.sendSimple("B�n mu�n l�a ch�n?#b\r\n#L0#Th�ch Kh�ch#l\r\n#L1#L�ng Kh�ch#l#k");
	} else if (status == 22) {
		var jobName;
		if (selection == 0) {
			jobName = "Assassin";
			job = 410; // ASSASIN
		} else {
			jobName = "Bandit";
			job = 420; // BANDIT
		}
		cm.sendYesNo("B�n s� ���c chuy�n th�nh #r" + jobName + "#k?");
	} else if (status == 23) {
		cm.changeJob(job);
		cm.gainSp(1);
		cm.gainItem(4031012, -1);
	cm.sendOk("V� v�y, ���c n�! B�y gi� �i, v� �i v�i ni�m ki�u h�nh.");
		cm.dispose();
	}
}
