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
		cm.sendNextNoESC("C� ph�i t�t c� c�c ch� huy � ��y kh�ng? T�t, ch�ng ta h�y b�t ��u.");
    } else if (status == 1) {
		cm.sendNextNoESC("Cho ��n khi Black Mage h�ng m�nh ho�n th�nh k� ho�ch c�a m�nh, ch�ng ta kh�ng ���c th� gi�n m�t l�c! Ch�ng ta v�n c�n d� b� t�n th��ng. B�y gi�, #h0#, t�i nghe n�i b�n �� ph�t hi�n ra th�ng tin th� v�.", 2159308);
    } else if (status == 2) {
		cm.sendPlayerToNpc("V�ng .. T�i �� ph�t hi�n ra m�t nh�m kh�ng chi�n �� ���c h�nh th�nh trong b� m�t v� �ang x�y d�ng m�t l�c l��ng �� ch�ng l�i ch�ng t�i.");
	} else if (status == 3) {
		cm.sendNextNoESC("Kh�ng chi�n? Ha! Kh�ng ai c�n l�i trong th� gi�i n�y c� th� ch�ng l�i ch�ng ta. T�i th�m ch� c�n nghe th�y m�t s� ti�ng r�n r� g�i h� l� #rHeroes#k. Kh�ng ph�i l� qu� gi�?", 2159308);
	} else if (status == 4) {
		cm.sendNextNoESC("T�i h�i vui khi th�y h� tranh gi�nh xung quanh trong ho�ng s� c�a h�. H� ch�c ch�n �� kh�ng ��a ra nhi�u s�c �� kh�ng khi ch�ng t�i l�y Ereve ho�c khi t�i lo�i b� Castellan.", 2159339);
	} else if (status == 5) {
		cm.sendNextNoESC("Tr�n chi�n � Ereve th�t d� d�ng v� Black Mage, kh�ng ph�i b�n, m�t ��a tr�.", 2159308);
	} else if (status == 6) {
		cm.sendNextNoESC("V�ng, t�i kh�ng ph�i s� d�ng to�n b� s�c m�nh c�a m�nh. V� v�y, c�.", 2159339);
	} else if (status == 7) {
		cm.sendPlayerToNpc("B�n �ang l�m g� � ��y, Orchid? B�n c� �ang l�m vi�c v�i Lotus kh�ng??");
	} else if (status == 8) {
		cm.sendNextNoESC("Lotus �ang b�n v� c� �y lu�n t�m ki�m nhi�u vi�c h�n �� l�m! B�n kh�ng ph�i l�m phi�n t�i v� n�.", 2159339);
	} else if (status == 9) {
		cm.sendNextNoESC("Cu�c h�p n�y s� kh�ng �i ��n ��u c�.");
	} else if (status == 10) {
		cm.sendNextNoESC("B�t c� khi n�o Orchid n�i chuy�n, c�c cu�c h�p c�a ch�ng t�i ��u d�ng l�i! ��i v�i c�c anh h�ng, t�i ch�c ch�n #h0# c� m�t k� ho�ch �� ��i ph� v�i h�. T�i ch�c ch�n nh�ng 'anh h�ng' th�m h�i n�y s� kh�ng ph� h�p v�i anh ta.", 2159308);
	} else if (status == 11) {
		cm.sendPlayerToNpc("Kh�ng gi�ng nh� h�u h�t k� th�, c�c anh h�ng chi�n ��u cho ng��i kh�c, kh�ng ph�i b�n th�n h� ... h� l� ��c bi�t, b�i v� h� b�o v� th� gi�i. �i�u �� l�m cho ch�ng nguy hi�m. Ngo�i ra, t�i ch� cho�ng v�ng N� th�n. Black Mage l� ng��i ��nh b�i c�.");
	} else if (status == 12) {
		cm.sendNextNoESC("L�m th� n�o khi�m t�n c�a b�n! B�n y�u th�ch Black Mage nh� th� n�o ... T�i, T�i, T�i...", 2159308);
	} else if (status == 13) {
		cm.sendDirectionInfo("Effect/Direction6.img/effect/tuto/balloonMsg0/10");
		cm.sendNextNoESC("�� r�i! C� hai b�n.");
	} else if (status == 14) {
		cm.sendNextNoESC("T�i sao? T�i th�y n� kh� th� v�.", 2159339);
	} else if (status == 15) {
		cm.sendNextNoESC("V� t�i �ang khen ng�i HERO th�c s� c�a c�c l�c l��ng c�a ch�ng ta, MIGHTY #h0#! Ha ha ha...", 2159308);
	} else if (status == 16) {
		cm.sendDirectionInfo("Effect/Direction6.img/effect/tuto/balloonMsg0/10");
		cm.sendNextNoESC("�� r�i! Ch�p nh�n r�ng #h0# Cho�ng v�ng N� th�n, cho ph�p chi�n th�ng c�a ch�ng t�i. V� v�y, ��ng g�p c�a �ng l� quan tr�ng nh�t. Ngo�i ra, b�n ���c ghi c� �� l�m ch�i m�t N� th�n. B�n mu�n g� h�n?");
	} else if (status == 17) {
		cm.sendNextNoESC("Ah, nh�ng g� c�a nh�m kh�ng chi�n c�n l�i sau ��, n�u c�c anh h�ng ���c ch�m s�c? Ch�ng ta ph�i di chuy�n c�ng v�i cu�c h�p.", 2159308);
	} else if (status == 18) {
		cm.sendNextNoESC("Theo ch� huy, h� �� b� lo�i b� ho�n to�n.");
	} else if (status == 19) {
		cm.sendNextNoESC("?, t�i c� m�t c�u h�i. T�i sao Black Mage l�i b�o ch�ng ta ph� h�y m�i th�? N�u kh�ng c�n g�, kh�ng c� g� �� cai tr�.", 2159339);
	} else if (status == 20) {
		cm.sendDirectionInfo("Effect/Direction6.img/effect/tuto/balloonMsg1/18");
		cm.sendPlayerToNpc("C�i g�? Black Mage �� ra l�nh n�y khi n�o? T�i ch�a bao gi� nghe n�i v� �i�u n�y.");
	} else if (status == 21) {
		cm.sendNextNoESC("� v�ng. T�i g�n nh� qu�n �� c�p ��n c�c ��n ��t h�ng m�i cho b�n. Black Mage ra l�nh cho t�t c� ch�ng t�i, ngo�i tr� b�n, �� lo�i b� m�i th�.", 2159308);
	} else if (status == 22) {
		cm.sendNextNoESC("V�ng. V� d�, Leafre ch� b� ��t ch�y th�nh ch�t k�t d�nh..");
	} else if (status == 23) {
		cm.sendDirectionInfo("Effect/Direction6.img/effect/tuto/balloonMsg1/3");
		cm.sendPlayerToNpc("(R�ng th�n tho�i?�� l� g�n gia ��nh t�i...!)");
	} else if (status == 24) {
		cm.sendNextNoESC("T�i ngh� ch�ng t�i �� l�m t�t. Ch� c� m�t v�i m�y ch� r�ng duy tr� gi� kh�ng c�.", 2159308);
	} else if (status == 25) {
		cm.sendPlayerToNpc("Black Mage kh�ng h�a s� t�n c�ng Leafre? Ph�n n�o �� b� ph� h�y?");
	} else if (status == 26) {
		cm.sendNextNoESC("Ph�n? T�t c� b�n h�, t�t nhi�n r�i! B�n c� v�n �� g�?", 2159308);
	} else if (status == 27) {
		cm.sendDirectionInfo("Effect/Direction6.img/effect/tuto/balloonMsg0/11");
		cm.sendPlayerToNpc("L�m �n cho t�i. C� �i�u t�i ph�i tham d�.");
	} else if (status == 28) {
		cm.sendNextNoESC("V�n ng�i! Ch�a c� ai b�c b� b�n.", 2159308);
	} else if (status == 29) {
		cm.sendDirectionStatus(3, 2);
		cm.sendDirectionStatus(4, 0);
		cm.warp(924020010,0);
		cm.dispose();
	}
}