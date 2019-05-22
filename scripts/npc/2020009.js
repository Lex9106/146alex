/* Robeira
	Magician 3rd job advancement
	El Nath: Chief's Residence (211000001)

	Custom Quest 100100, 100102
*/

var status = -1;
var job;

function start() {
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
	if (!(cm.getJob() == 210 || cm.getJob() == 220 || cm.getJob() == 230)) { // CLERIC
	    cm.sendOk("B�n d��ng nh� c� ti�m n�ng, luy�n t�p nhi�u h�n v� m�t ng�y n�o �� c� l� t�i s� xem x�t ��o t�o b�n");
	    cm.dispose();
	    return;
	}
	if ((cm.getJob() == 210 || cm.getJob() == 220 || cm.getJob() == 230) && cm.getPlayerStat("LVL") >= 70) {
	    if (cm.getPlayerStat("RSP") > (cm.getPlayerStat("LVL") - 70) * 3) {
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
	    if (cm.getPlayerStat("LVL") >= 70 && cm.getPlayerStat("RSP") <= (cm.getPlayerStat("LVL") - 70) * 3) {
	    if (cm.getJob() == 210) { // FP
		cm.changeJob(211); // FP MAGE
		cm.sendOk("B�n mu�n tr� th�nh m�t a #bFire/Poison Mage#k");
		cm.dispose();
	    } else if (cm.getJob() == 220) { // IL
		cm.changeJob(221); // IL MAGE
		cm.sendOk("B�n mu�n tr� th�nh m�t an #bIce/Lightning Mage#k.");
		cm.dispose();
	    } else if (cm.getJob() == 230) { // CLERIC
		cm.changeJob(231); // PRIEST
		cm.sendOk("B�n mu�n tr� th�nh m�t a #bPriest#k.");
		cm.dispose();
	    }
	    } else {
		cm.sendOk("Quay l�i khi b�n � c�p 70 v� s� d�ng �i�m k� n�ng.");
		cm.dispose();
	    }
    }
}