var status = -1;
function action(mode, type, selection) {
    if (mode == 1) 
        status++;
    else 
	status--;
    if (status == 0) {
    	cm.sendNext("Chu�t nh�. T�i n�i, l�m th� n�o DARE b�n c� g�ng tho�t kh�i n�i n�y?");
    } else if (status == 1) {
	cm.sendNextPrevS("Shoot, we were spotted!", 2);
    } else if (status == 2) {
	cm.sendNextPrev("B�y gi�, b�y gi�, tr� em. ��ng l�m �i�u n�y kh� h�n n� c�n. Ch� c�n �i v� ph�a t�i, ��p v� d� d�ng ... ��i ��, b�n kh�ng ph�i l� m�t trong nh�ng m�n thi. B�n l� m�t trong nh�ng ng��i d�n th� tr�n, kh�ng ph�i b�n?");
    } else if (status == 3) {
	cm.sendNextPrevS("��ng v�y. T�i l� m�t c� d�n c�a Edelstein, kh�ng ph�i l� m�t ch� �� th� nghi�m. B�n kh�ng th� ��a t�i �i v�ng quanh.", 2);
    } else if (status == 4) {
	cm.sendNextPrev("�i tr�i, �i. T�i n�i v�i h� �� ch�c ch�n r�ng nh�ng ng��i d�n th� tr�n gi� con c�i c�a h� tr�nh xa c�c m� ... Than �i, �� qu� mu�n r�i. T�i kh�ng th� cho ph�p b�n n�i v�i b�t c� ai v� ph�ng th� nghi�m n�y, v� v�y t�i �o�n b�n s� ch� ph�i � l�i ��y v� ... gi�p �� v�i c�c th� nghi�m. *snicker*");
    } else if (status == 5) {
	cm.sendNextPrevS("Hmph. T� l�n, nh�ng h�y xem b�n c� th� b�t ���c t�i tr��c kh�ng.", 2);
    } else if (status == 6) {
	cm.sendNextPrev("T�i sao, b�n x�c l�o, �t - Ahem, ahem, ahem. L�i n�i c�a b�n kh�ng quan tr�ng. л ��n l�c t�i r�t s�ng l�n. T�i hy v�ng b�n �� s�n s�ng. N�u kh�ng, b�n s� ph�i ch�u ��ng.");
    } else if (status == 7) {
	cm.sendNextPrev("T�i n�i, c� b�t k� t� ng� l�n h�n, kiddo? T�i s� ��m b�o Gelimer th�c hi�n m�t s� th� nghi�m ��c bi�t t�n b�o v� b�n. Nh�ng t�i s� r�t tuy�t n�u b�n ��n v�i t�i gi�ng nh� y�n t�nh.");
    } else if (status == 8) {
	cm.sendNextPrevS("Gi� n� ngay t�i ��!", 4, 2159010);
    } else if (status == 9) {
	cm.warp(931000021,1);
    	cm.dispose();
    }
}