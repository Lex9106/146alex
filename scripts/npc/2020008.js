/** 
	Tylus: Warrior 3rd job advancement
	El Nath: Chief's Residence (211000001)

	Custom Quest 100100, 100102
*/

var status = 0;
var job;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 1) {
	cm.sendOk("H�y quy�t ��nh v� gh� th�m t�i l�n n�a.");
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	if (!(cm.getJob() == 110 || cm.getJob() == 120 || cm.getJob() == 130 || cm.getJob() == 2110)) {
	    if (cm.getQuestStatus(6192) == 1) {
		if (cm.getParty() != null) {
		    var ddz = cm.getEventManager("ProtectTylus");
		    if (ddz == null) {
			cm.sendOk("Unknown error occured");
		    } else {
			var prop = ddz.getProperty("state");
			if (prop == null || prop.equals("0")) {
			    ddz.startInstance(cm.getParty(), cm.getMap());
			} else {
			    cm.sendOk("Ng��i kh�c �� c� g�ng b�o v� Tylus, vui l�ng th� l�i sau m�t l�t.");
			}
		    }
		} else {
		    cm.sendOk("H�y th�nh l�p m�t nh�m �� b�o v� Tylus!");
		}
	    } else if (cm.getQuestStatus(6192) == 2) {
		cm.sendOk("B�n �� b�o v� t�i. C�m �n b�n. T�i s� d�y b�n k� n�ng l�p tr��ng.");
		if (cm.getJob() == 112) {
			if (cm.getPlayer().getMasterLevel(1121002) <= 0) {
				cm.teachSkill(1121002, 0, 10);
			}
		} else if (cm.getJob() == 122) {
			if (cm.getPlayer().getMasterLevel(1221002) <= 0) {
				cm.teachSkill(1221002, 0, 10);
			}
		} else if (cm.getJob() == 132) {
			if (cm.getPlayer().getMasterLevel(1321002) <= 0) {
				cm.teachSkill(1321002, 0, 10);
			}
		}
	    } else {
		cm.sendOk("B�n d��ng nh� c� ti�m n�ng, luy�n t�p nhi�u h�n v� m�t ng�y n�o �� c� l� t�i s� xem x�t ��o t�o b�n");
	    }
	    cm.dispose();
	    return;
	}
	if ((cm.getJob() == 110 || cm.getJob() == 120 || cm.getJob() == 130 || cm.getJob() == 2110 ) && cm.getPlayerStat("LVL") >= 70) {
	    if (cm.getPlayerStat("RSP") > (cm.getPlayerStat("LVL") - 70) * 3) {
	        if (cm.getPlayer().getAllSkillLevels() > cm.getPlayerStat("LVL") * 3) { //player used too much SP means they have assigned to their skills.. conflict
		    cm.sendOk("It appears that you have a great number of SP yet you have used enough SP on your skills already. Your SP has been reset. #ePlease talk to me again to make the job advancement.#n");
		    cm.getPlayer().resetSP((cm.getPlayerStat("LVL") - 70) * 3);
	        } else {
	    	    cm.sendOk("Hmm ... B�n c� qu� nhi�u #b�i�m k� n�ng #k. B�n kh�ng th� th�c hi�n ti�n tr�nh c�ng vi�c v�i qu� nhi�u �i�m k� n�ng c�n l�i.");
	        }
		cm.safeDispose();
	    } else {
	        cm.sendNext("B�n th�c s� l� m�t ng��i m�nh m�.");
	    }
	} else {
	    cm.sendOk("Vui l�ng ��m b�o r�ng b�n �� �i�u ki�n cho ti�n tr�nh c�ng vi�c. (level 70+)");
	    cm.safeDispose();
	}
    } else if (status == 1) {
	    if (cm.getPlayerStat("LVL") >= 70 && cm.getPlayerStat("RSP") <= (cm.getPlayerStat("LVL") - 70) * 3) {
	    if (cm.getJob() == 110) { // FIGHTER
		cm.changeJob(111); // CRUSADER
		cm.sendOk("B�n mu�n tr� th�nh #bCrusader(Ki�m S�)#k.");
		cm.dispose();
	    } else if (cm.getJob() == 120) { // PAGE
		cm.changeJob(121); // WHITEKNIHT
		cm.sendOk("B�n mu�n tr� th�nh #bWhite Knight (K� s�)#k.");
		cm.dispose();
	    } else if (cm.getJob() == 130) { // SPEARMAN
		cm.changeJob(131); // DRAGONKNIGHT
		cm.sendOk("B�n mu�n tr� th�nh #bDragon Knight(Th��ng S�)#k");
		cm.dispose();
	    } else if (cm.getJob() == 2110) { // ARAN
		cm.changeJob(2111); // ARAN
		if (cm.canHold(1142131,1)) {
		    cm.forceCompleteQuest(29926);
		    cm.gainItem(1142131,1); //temp fix
		}
		cm.sendOk("You are now a #bAran#k.");
		cm.dispose();
	    }
	    } else {
		cm.sendOk("Quay l�i khi b�n � c�p 70 v� s� d�ng �i�m k� n�ng.");
		cm.dispose();
	    }
    }
}