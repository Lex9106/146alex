var status = -1;

function action(mode, type, selection) {
    if (cm.getQuestStatus(21002) == 0) {
	if (mode == 1) {
	    status++;
	} else {
	    status--;
	}
	if (status == 0) {
	    cm.sendNext("�i Aran, anh t�nh r�i! Ch�n th��ng th� n�o? ... C�i g�? B�n c� mu�n bi�t nh�ng g� �ang x�y ra ngay b�y gi�?");
	} else if (status == 1) {
	    cm.sendNextPrev("Ch�ng t�i �� s�n s�ng v� s�n s�ng r�i kh�i n�i n�y. Ch�ng t�i c� t�t c� m�i ng��i tr�n t�u, v� Chim thi�ng ���c �� ngh� b�o v� h�m c�a ch�ng t�i trong su�t chuy�n bay, v� v�y b�n kh�ng c�n ph�i lo l�ng g�. Khi ch�ng t�i ho�n t�t m�i th�, ch�ng t�i s� ti�p t�c v� tr�n tho�t ��n ?�o Victoria.");
	} else if (status == 2) {
	    cm.sendNextPrev("?�ng ch� c�a Aran ...? Ch� ... h� �� �i qua �� chi�n ��u v�i Ph� th�y �en. H� quy�t ��nh nh�n Black Wizard trong khi ch�ng t�i tr�n tho�t. G�? B�n mu�n tham gia c�ng h� trong tr�n chi�n? Kh�ng kh�ng ��i n�o! B�n b� th��ng! B�n n�n l�n t�u ngay b�y gi�!");
	} else if (status == 3) {
	    cm.forceStartQuest(21002, "1");
	    // Ahh, Oh No. The kid is missing
	    cm.showWZEffect("Effect/Direction1.img/aranTutorial/Trio");
	    cm.dispose();
	}
    } else {
	if (mode == 1) {
	    status++;
	} else {
	    status--;
	}
	if (status == 0) {
	    cm.sendSimple("We're in a state of emergency. What would you like to know? \r #b#L0#Where's the Black Wizard?#l \r #b#L1#How's the preparation for the escape?#l \r #b#L2#How about the comrades?#l");
	} else if (status == 1) {
	    switch (selection) {
		case 0:
		    cm.sendOk("T�i nghe n�i r�ng Ph� th�y �en � g�n n�i ch�ng ta �ang � hi�n t�i. Ch�ng t�i th�m ch� kh�ng th� tr�n tho�t trong r�ng v� s� �i�u khi�n c�a Black Wizard. ?� l� l� do t�i sao ch�ng t�i ngh� ra chi�c thuy�n l� l�i tho�t c�a ch�ng t�i. C�ch duy nh�t ch�ng ta c� th� r�i kh�i n�i n�y l� bay v� ph�a ��o Victoria.");
		    break;
		case 1:
		    cm.sendOk("Ch�ng t�i c� t�t c� m�i ng��i tr�n t�u, v� ch�ng t�i �� s�n s�ng v� s�n s�ng tho�t kh�i n�i n�y. Ch�ng t�i ch� c�n th�m m�t v�i th� tr�n t�u, v� ch�ng t�i s� ��n ��o Victoria. Trong chuy�n bay c�a ch�ng t�i, Chim th�n ��a ra s� b�o v�, th�y r�ng c� �y kh�ng c� ai �� b�o v� t�i Erev v�o th�i �i�m n�y.");
		    break;
		case 2:
		    cm.sendOk("C�c ��ng ��i c�a b�n ... �� r�i kh�i ��y �� t� m�nh chi�n ��u v�i Ph� th�y �en, mua m�t �t th�i gian khi ch�ng t�i tr�n tho�t. H� quy�t ��nh kh�ng l�y b�n, v� b�n b� th��ng v� t�t c�. Khi ch�ng t�i c�u ��a tr�, b�n n�n l�n t�u v� r�i �i v�i ch�ng t�i, Aran!");
		    break;
	    }
	    cm.safeDispose();
	}
    }
}