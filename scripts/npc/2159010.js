var status = -1;
function action(mode, type, selection) {
    if (mode == 1) 
        status++;
    else 
	status--;
    if (status == 0) {
    	cm.sendNextS("C� v� nh� ch�ng t�i �� m�t anh �y. T�t nhi�n, t�i c� th� d� d�ng x� l� anh ta, nh�ng v�n ��, nh�ng t�i kh�ng ch�c ch�n t�i b�o v� b�n kiddos c�ng m�t l�c. * c��i * Hai b�n �ang l�m g� � ��y v�y? B� m� c�a b�n kh�ng c�nh b�o b�n tr�nh xa c�c m�?", 8);
    } else if (status == 1) {
	cm.sendNextPrevS("�� l� l�i c�a t�i! #h0# ch� �ang c� g�ng gi�p ��! #h0# �� c�u t�i!", 4, 2159007);
    } else if (status == 2) {
	cm.sendNextPrevS("Gi�i c�u b�n, eh? Hm, c� �n m�c vui v�, c� b�. Ooooh. B�n l� t� nh�n c�a C�nh �en?", 8);
    } else if (status == 3) {
	cm.sendNextPrevS("#b(Vita nhanh ch�ng gi�i th�ch t�nh h�nh.)#k", 4, 2159007);
    } else if (status == 4) {
	cm.sendNextPrevS("Nh�ng b�n l� ai? B�n ��n t� ��u? V� t�i sao b�n c�u ch�ng t�i?", 2);
    } else if (status == 5) {
	cm.sendNextPrevS("T�i l� m�t th�nh vi�n ��ng t� h�o c�a phe Kh�ng chi�n, m�t nh�m b� m�t chi�n ��u v� ph� ho�i C�nh �en. T�i kh�ng th� n�i cho b�n bi�t t�i l� ai, nh�ng t�i �i theo t�n m� c�a J.", 8);
    } else if (status == 6) {
	cm.sendNextPrevS("B�y gi�, xin vui l�ng tr� l�i th�nh ph� v� tr�nh xa c�c m�. ��i v�i b�n, Vita, �i v�i t�i. N�u b�n kh�ng ���c b�o v�, t�i s� Black Wings s� ��n t�m b�n. Kh�ng ai c� th� gi� cho b�n an to�n nh� t�i c� th�! B�y gi�, h�y gi� b� m�t l�i n�i c�a t�i. S� ph�n c�a kh�ng chi�n ph� thu�c v�o quy�t ��nh c�a b�n.", 8);
    } else if (status == 7) {
	cm.sendNextPrevS("Ch� ��, tr��c khi b�n �i, h�y n�i cho t�i m�t �i�u. L�m th� n�o t�i c� th� tham gia kh�ng chi�n?", 2);
    } else if (status == 8) {
	cm.sendNextPrevS("Ah, b�n tr�, b�n mu�n chi�n ��u v�i Black Wings, ph�i kh�ng? Tr�i tim c�a b�n l� cao qu�, nh�ng c� r�t �t b�n c� th� l�m �� gi�p nh�ng n� l�c c�a ch�ng t�i cho ��n khi b�n ��t Lv. 10. Qu� nhi�u, v� t�i s� c� m�t ng��i n�o �� t� Li�n h� kh�ng chi�n b�n. �� l� m�t l�i h�a, kiddo. B�y gi�, t�i ph�i �i, nh�ng c� l� ch�ng ta s� g�p l�i m�t ng�y n�o ��!", 8);
    } else if (status == 9) {
	cm.forceCompleteQuest(23007);
	cm.gainItem(2000000,3);
	cm.gainItem(2000003,3);
	cm.gainExp(4200);
	cm.MovieClipIntroUI(false);
	cm.warp(310000000,8);
    	cm.dispose();
    }
}