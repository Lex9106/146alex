var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 0) {
	    cm.dispose();
	}
	status--;
    }
    if (status == 0) {
		cm.sendNextNoESC("B�n th�c s� c� ��i c�nh...");
    } else if (status == 1) {
		cm.sendNextNoESC("B�n l� ai B�n l� m�t ph�n c�a Black Wings? M�t �i�p vi�n? Th�c ra, ch� ��, kh�ng. �i�u �� kh�ng c� � ngh�a...", 2159312);
    } else if (status == 2) {
		cm.sendNextNoESC("Gi� b�o v� c�a b�n l�n. Ch�ng t�i v�n kh�ng bi�t g�...", 2159313);
	} else if (status == 3) {
		cm.sendNextNoESC("B�n l� ai M�i quan h� c�a b�n v�i ��i c�nh �en l� g�?", 2159315);
	} else if (status == 4) {
		cm.sendPlayerToNpc("T�i kh�ng bi�t Black Wings l� ai. B�n mu�n bi�t g� v� t�i? T�i th�m ch� kh�ng ch�c b�t ��u t� ��u.");
	} else if (status == 5) {
		cm.sendNextNoESC("H�y b�t ��u v�i t�n, t� ch�c, n�n t�ng ... v� nh�ng ��i c�nh tr�n l�ng c�a b�n.", 2159342);
	} else if (status == 6) {
		cm.sendPlayerToNpc("T�n t�i l� #h0#. T�i hi�n kh�ng ph�i l� th�nh vi�n c�a b�t k� t� ch�c n�o, m�c d� t�i t�ng l� m�t trong nh�ng Ch� huy c�a Black Mage. T�i n�i lo�n ch�ng l�i anh ta, v� ch�ng t�i �� chi�n ��u, nh�ng anh ta �� ��nh b�i t�i. Khi t�i th�c d�y, t�i th�y C�nh �en. Oh, v� t�i ���c sinh ra v�i ��i c�nh n�y. T�i l� qu�.");
	} else if (status == 7) {
		cm.sendNextNoESC("B�n l� m�t ch� huy d��i Black Mage? L�m th� n�o? �ng �� b� phong �n h�ng tr�m n�m!", 2159315);
	} else if (status == 8) {
		cm.sendDirectionStatus(1, 2000);
		cm.sendDirectionInfo("Effect/Direction6.img/effect/tuto/balloonMsg1/3");
		cm.sendNextNoESC("Hmm .. anh ta c� th� �o t��ng.");
	} else if (status == 9) {
		cm.sendPlayerToNpc("(H�ng tr�m n�m tr��c? Nh�ng, n�i n�y th�t k� l�. T�i �� ng� ���c bao l�u r�i? C�c anh h�ng c� th� phong �n Black Mage kh�ng??)");
	} else if (status == 10) {
		cm.sendNextNoESC("�i�u n�y kh�ng c� � ngh�a. B�n �ang n�i d�i?");
	} else if (status == 11) {
		cm.sendNextNoESC("S� Anh ta c� th� �i�n, nh�ng anh ta kh�ng n�i d�i.", 2159345);
	} else if (status == 12) {
		cm.sendNextNoESC("V� v�y ... Anh ta �i�n ho�c n�i s� th�t. Jack �en kh�ng bao gi� sai.", 2159316);
	} else if (status == 13) {
		cm.sendNextNoESC("Anh �y �� t�ng � trong qu� kh�, tr��c khi Black Mage b� phong �n. T�i sao b�n n�i lo�n, n�u b�n l� T� l�nh?", 2159315);
	} else if (status == 14) {
		cm.sendPlayerToNpc("�� l� c� nh�n. B�y gi�, b�n tr� l�i c�u h�i c�a t�i. B�n l� ai? ��i c�nh �en?");
	} else if (status == 15) {
		cm.sendNextNoESC("Ch�ng t�i l� kh�ng chi�n, m�t nh�m ���c h�nh th�nh b� m�t �� b�o v� ng�i nh� c�a ch�ng t�i, Edelstein, t� Black Wings. Nh�ng ng��i kh� ch�u �n c�p n�ng l��ng c�a b�n l� Black Wings. H� �� ti�u hao n�ng l��ng t� th�nh ph� t� m�t th�i gian tr��c, v� h� l�m vi�c cho Black Mage.", 2159342);
	} else if (status == 16) {
		cm.sendPlayerToNpc("H� theo Black Mage? Kh�ng ph�i l� �ng �� b� phong �n?");
	} else if (status == 17) {
		cm.sendNextNoESC("Anh ta, nh�ng h� �ang c� g�ng th� anh ta m�t l�n n�a.", 2159342);
	} else if (status == 18) {
		cm.sendPlayerToNpc("Black Mage �ang quay tr� l�i? �� l� tin tuy�t v�i ... �i�u �� c� ngh�a l� t�i v�n c� th� tr� th�.");
	} else if (status == 19) {
		cm.sendNextNoESC("B�n �ang �i�n, nh�ng ch�ng t�i �ang � c�ng m�t ph�a. T�i sao b�n kh�ng tham gia v�i ch�ng t�i?", 2159342);
	} else if (status == 20) {
		cm.sendNextNoESC("B�n �ang n�i g� v�y? B�n th�c s� tin anh ta? Ngay c� khi anh ta �ang n�i s� th�t, anh ta l� m�t T� l�nh!", 2159315);
	} else if (status == 21) {
		cm.sendNextNoESC("Anh ta d��ng nh� gh�t Black Mage nhi�u nh� ch�ng t�i, n�u kh�ng nhi�u h�n. Ngay c� khi anh ta kh�ng ���c ch� huy, anh ta kh�ng c�n n�a. Ch�ng t�i lu�n c� th� s� d�ng nhi�u th�nh vi�n h�n, mi�n l� m�c ti�u c�a ch�ng t�i l� nh� nhau. Ch�ng ta c� th� l�m vi�c c�ng nhau.", 2159342);
	} else if (status == 22) {
		cm.sendPlayerToNpc("��i ��, chuy�n g� �ang di�n ra?");
	} else if (status == 23) {
		cm.sendNextNoESC("Kh�ng c�n ph�i b�t k�p. Quy�t ��nh �� ���c ��a ra. N�u b�n mu�n chi�n ��u v�i Black Mage, b�n ph�i tr�i qua Black Wings. H�y l�m vi�c c�ng nhau �� ��a h� xu�ng.");
	} else if (status == 24) {
		cm.sendNextNoESC("T�i kh�ng mong ��i b�n ho�n to�n tin t��ng ch�ng t�i, nh�ng ch�ng t�i c� th� l�m vi�c tr�n ��, t�ng m�nh m�t, nh� ch�ng t�i l�y Black Wings ngo�i.", 2159342);
	} else if (status == 25) {
		cm.sendPlayerToNpc("��ng. T�i s� tham gia c�ng b�n, b�y gi� ... H�y �� t�i c�m �n b�n v� �� c�u t�i.");
	} else if (status == 26) {
		cm.sendNextNoESC("Nghe th�y �� l� m�t s� nh� nh�m. B�n ���c ch�o ��n.", 2159342);
	} else if (status == 27) {
		cm.sendPlayerToNpc("T�i trung th�nh v�i nh�ng ng��i trung th�nh v�i t�i.");
	} else if (status == 28) {
		cm.sendNextNoESC("L�m vi�c cho t�i. ���c r�i, l�m �n � nh�.", 2159315);
	} else if (status == 29) {
		cm.EnableUI(0);
		cm.DisableUI(false);
		//cm.sendDirectionStatus(4, 0);
		cm.forceStartQuest(23209, "1");
		cm.forceCompleteQuest(23279);
		cm.forceCompleteQuest(7621);
		cm.forceCompleteQuest(29958);
		cm.gainItem(1142341, 1);
		cm.getPlayer().changeJob(3100);
		cm.warp(310010000,0);
		cm.dispose();
	}
}