var status = -1;
function action(mode, type, selection) {
    if (mode == 1) 
        status++;
    else 
	status--;
    if (cm.getPlayer().getMapId() == 931000011 || cm.getPlayer().getMapId() == 931000030) {
	cm.dispose();
	return;
    }
    if (cm.getInfoQuest(23007).indexOf("vel01=2") == -1) {
    	if (status == 0) {
    	    cm.sendNext("Whoa. C�i g� �� x�y ra? C�i ly b� v� ... �� l�m rung ��ng �� tr��c �� ph� v� n�?");
    	} else if (status == 1) {
	    cm.sendNextPrevS("B�y gi�, kh�ng c� g� ng�n c�n b�n ph�i kh�ng? H�y ra kh�i ��y!", 2);
        } else if (status == 2) {
	    cm.sendNextPrevS("Sau �� nhanh l�n! �i th�i!", 2);
        } else if (status == 3) {
	    cm.updateInfoQuest(23007, "vel00=2;vel01=2");
	    cm.warp(931000020,1);
	    cm.dispose();
	}
    } else if (cm.getInfoQuest(23007).indexOf("vel01=2") != -1) {
    	if (status == 0) {
    	    cm.sendNext("л l�u r�i ... k� t� khi t�i � ngo�i ph�ng th� nghi�m. Ch�ng ta �ang � ��u?");
    	} else if (status == 1) {
	    cm.sendNextPrevS("��y l� con ���ng d�n ��n Edelstein, n�i t�i s�ng! H�y ra kh�i ��y tr��c khi Black Wings theo ch�ng t�i.", 2);
        } else if (status == 2) {
	    cm.updateInfoQuest(23007, "vel00=2;vel01=3");
	    cm.ShowWZEffect("Effect/OnUserEff.img/guideEffect/aranTutorial/tutorialArrow1");
	    cm.dispose();
	}
    } else {
	cm.sendOk("л l�u r�i ... k� t� khi t�i � ngo�i ph�ng th� nghi�m.");
    	cm.dispose();
    }
}