/* Arec
	Thief 3rd job advancement
	El Nath: Chief's Residence (211000001)

	Custom Quest 100100, 100102
*/

var status = -1;
var job;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 1) {
	cm.sendOk("H�y quy�t ��nh v� gh� th�m t�i l�n n�a.");
	    cm.safeDispose();
	    return;
	}
	status--;
    }

    if (status == 0) {
	if ((cm.getJob() == 410 || cm.getJob() == 420 || cm.getJob() == 432)) {
	    cm.sendOk("B�n d��ng nh� c� ti�m n�ng, luy�n t�p nhi�u h�n v� m�t ng�y n�o �� c� l� t�i s� xem x�t ��o t�o b�n");
	    cm.safeDispose();
	    return;
	} if (cm.getJob() == 3612) {
        cm.sendOk("Who the fuq're you?");
	    cm.safeDispose();
	}if ((cm.getJob() == 410 || cm.getJob() == 420 || cm.getJob() == 432) && cm.getPlayerStat("LVL") >= 70) {
	    if (cm.getJob() != 432 && cm.getPlayerStat("RSP") > (cm.getPlayerStat("LVL") - 70) * 3) {
	        if (cm.getPlayer().getAllSkillLevels() > cm.getPlayerStat("LVL") * 3) { //player used too much SP means they have assigned to their skills.. conflict
		    cm.sendOk("C� v� nh� b�n c� s� l��ng �i�m k� n�ng l�n nh�ng b�n �� s� d�ng �� �i�m k� n�ng  cho k� n�ng c�a b�n r�i. �i�m k� n�ng c�a b�n �� ���c ��t l�i. #eVui l�ng n�i chuy�n v�i t�i m�t l�n n�a �� th�c hi�n ti�n b� c�ng vi�c#n");
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
	    if (cm.getPlayerStat("LVL") >= 70 && (cm.getJob() == 432 || cm.getPlayerStat("RSP") <= (cm.getPlayerStat("LVL") - 70) * 3)) {
	    	if (cm.getJob() == 410) { // ASSASIN
			cm.changeJob(411); // HERMIT
			cm.sendOk("You are B�n mu�n tr� th�nh m�t a #bHermit#k.");
			cm.safeDispose();
	    	} else if (cm.getJob() == 420) { // BANDIT
			cm.changeJob(421); // CDIT
			cm.sendOk("You are B�n mu�n tr� th�nh m�t a #bChief Bandit#.");
			cm.safeDispose();
		} else if (cm.getJob() == 432) { // 
			cm.changeJob(433); // 
			cm.sendOk("You are B�n mu�n tr� th�nh m�t a #bBlade Lord#k.");
			cm.safeDispose();
	    	}
	    } else {
		cm.sendOk("Quay l�i khi b�n � c�p 70 v� s� d�ng �i�m k� n�ng.");
		cm.dispose();
	    }
    }
}
