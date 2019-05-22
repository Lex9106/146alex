var status = -1;

function action(mode, type, selection) {
	if (cm.getMap().getAllMonstersThreadsafe().size() > 0) {
		cm.dispose();	
		return;
	}
    if (mode == 1) {
	status++;
    } else {
	if (status == 0) {
	    cm.dispose();
	}
	status--;
    }
    if (status == 0) {
		cm.sendNextNoESC("nh�n k�a, �� l� #h0#? Chuy�n �i c�a b�n th� n�o? N� c� ��ng gi� kh�ng tu�n l�nh? V� gia ��nh b�n th� n�o? Heh heh...", 2159308);
    } else if (status == 1) {
		cm.sendPlayerToNpc("T�i kh�ng c� th�i gian cho b�n. Tr�nh sang m�t b�n.");
    } else if (status == 2) {
		cm.sendDirectionInfo("Effect/Direction6.img/effect/tuto/balloonMsg1/14");
		//cm.sendDirectionStatus(0, 325);
		cm.showMapEffect("demonSlayer/31111003");
		cm.sendDirectionInfo("Skill/3111.img/skill/31111003/effect");
		cm.sendDirectionStatus(1, 1000);
		cm.sendNextNoESC("Th�t sao? ��y l� m�t ph�n qu�c, b�n bi�t ��y! B�n c� th�c s� y�u ��n m�c m�t �i gia ��nh khi�n b�n l�m �i�u n�y kh�ng? Th�m h�i!", 2159308);
	} else if (status == 3) {
		cm.sendDirectionInfo("Effect/Direction6.img/effect/tuto/balloonMsg1/15");
		//cm.sendDirectionStatus(0, 365);
		cm.showMapEffect("demonSlayer/31121001");
		cm.sendDirectionInfo("Skill/3112.img/skill/31121001/effect");
		cm.sendDirectionStatus(1, 1000);
		cm.sendNextNoESC("B�n l�m t�i th�t v�ng. B�n kh�ng hi�u Black Mage! Lo�i b� k� ph�n b�i!", 2159308);
	} else if (status == 4) {
		//cm.sendDirectionStatus(4, 0);
		cm.EnableUI(0);
		cm.DisableUI(false);
		cm.spawnMonster(9300455, 3);
		cm.forceStartQuest(23205);
		cm.showWZEffect("Effect/Direction6.img/DemonTutorial/Scene4");
		cm.dispose();
	}
}