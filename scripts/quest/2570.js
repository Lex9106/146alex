var status = -1;

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
	qm.sendYesNo("B�n �� quy�t ��nh ch�a? Quy�t ��nh s� l� quy�t ��nh cu�i c�ng, v� v�y h�y suy ngh� c�n th�n tr��c khi quy�t ��nh ph�i l�m g�. B�n c� ch�c ch�n mu�n tr� th�nh m�t Cannoneer?");
    } else if (status == 1) {
	qm.sendNext("I have just molded your body to make it perfect for a Cannoneer. If you wish to become more powerful, use Stat Window (S) to raise the appropriate stats. If you arn't sure what to raise, just click on #bAuto#k.");
	if (qm.getJob() == 1 || qm.getJob() == 0) {
	    qm.gainItem(1532000,1);
	    qm.expandInventory(1, 4);
	    qm.expandInventory(2, 4);
	    qm.expandInventory(4, 4);
	    qm.changeJob(501);
	}
	qm.forceCompleteQuest();
    } else if (status == 2) {
	qm.sendNextPrev("I have also expanded your inventory slot counts for your equipment and etc. inventory. Use those slots wisely and fill them up with items required for Explorer to carry.");
    } else if (status == 3) {
	qm.sendNextPrev("Now... I want you to go out there and show the world how the Explorers operate.");
	qm.safeDispose();
    }
}