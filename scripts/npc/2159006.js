var status = -1;
function action(mode, type, selection) {
    if (mode == 1) 
        status++;
    else 
	status--;
    if (cm.getPlayer().getMapId() == 931000011) {
	cm.dispose();
	return;
    }
    if (cm.getInfoQuest(23007).indexOf("vel00=1") == -1 && cm.getInfoQuest(23007).indexOf("vel01=1") == -1) {
    	if (status == 0) {
    	    cm.sendNext("? l�i!");
    	} else if (status == 1) {
	    cm.sendNextPrevS("Ai n�i chuy�n v�i b�n?B�n l� ai!", 2);
        } else if (status == 2) {
	    cm.sendNextPrev("Look up.");
        } else if (status == 3) {
	    cm.updateInfoQuest(23007, "vel00=1");
	    cm.showWZEffect("Effect/Direction4.img/Resistance/ClickVel");
	    cm.dispose();
	}
    } else if (cm.getInfoQuest(23007).indexOf("vel00=1") != -1 && cm.getInfoQuest(23007).indexOf("vel01=1") == -1) {
    	if (status == 0) {
    	    cm.sendNext("T�n t�i l� #bVita #k. T�i l� m�t trong nh�ng ��i t��ng th� nghi�m #k c�a #rDoctor Gelimer. Nh�ng �i�u �� kh�ng quan tr�ng ngay b�y gi�. B�n ph�i ra kh�i ��y tr��c khi ai �� nh�n th�y b�n!");
    	} else if (status == 1) {
	    cm.sendNextPrevS("��i ��, b�n �ang n�i g� v�y? Ai �� �ang l�m th� nghi�m cho b�n?! V� ai l� Gelimer?", 2);
        } else if (status == 2) {
	    cm.sendNextPrev("Shhh! B�n c� nghe th�y �i�u �� kh�ng? Ai �� �ang ��n! N� ph�i l� Doctor Gelimer! ? kh�ng!");
        } else if (status == 3) {
	    cm.updateInfoQuest(23007, "vel00=2");
	    cm.warp(931000011,0);
	    cm.dispose();
	}
    } else if (cm.getInfoQuest(23007).indexOf("vel01=1") != -1) {
    	if (status == 0) {
    	    cm.sendNext("? ��u, m�t c�i g� �� ph�i c� ph�n t�m ch�ng. B�y gi� l� c� h�i c�a b�n. GO!");
    	} else if (status == 1) {
	    cm.sendNextPrevS("#b(Vita nh�m m�t l�i nh� th� c� �y �� t� b�. B�n n�n l�m g�? L�m th� n�o v� c� g�ng �� ph� v� wad?)#k", 2);
        } else if (status == 2) {
	    cm.sendNextPrev("#b(B�n �� c� g�ng �� ��t watt v�i t�t c� s�c m�nh c�a b�n, nh�ng b�n tay c�a b�n tr��t!)#k");
        } else if (status == 3) {
	    cm.gainExp(60);
	    cm.warp(931000013,0);
	    cm.dispose();
	}
    }
}