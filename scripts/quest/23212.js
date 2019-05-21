/* Dawnveil
    [Maple Castle] A Tall Order
	Cygnus
    Made by Daenerys
*/
var status = -1;

function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
	if (status == 0) {
		qm.forceStartQuest();
		qm.dispose();
	}
}

function end(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
		if (status == 0) {
			qm.sendNextS("B�n �� quy�t ��nh ch�a? Quy�t ��nh s� l� quy�t ��nh cu�i c�ng, v� v�y h�y suy ngh� c�n th�n tr��c khi quy�t ��nh ph�i l�m g�. B�n c� ch�c ch�n mu�n tr� th�nh m�t Demon Slayer.",1);
		} else if (status == 1) {
			qm.sendNextPrevS("T�i v�a ��c c� th� c�a b�n �� l�m cho n� ho�n h�o cho m�t �c qu�. N�u b�n mu�n tr� n�n m�nh h�n, h�y s� d�ng Stat Window (S) �� t�ng s� li�u th�ng k� th�ch h�p. N�u b�n kh�ng ch�c ch�n nh�ng g� �� n�ng cao,h�y �n Auto!", 1);
		} else if (status == 2) {
			qm.sendNextPrevS("T�i s� m� r�ng h�nh l� trang b� c�a b�n.H�y c� g�ng cho h�nh tr�nh ti�p theo.!",1);	
		} else if (status == 3) {
			if (qm.getJob() == 3100) {
				qm.expandInventory(1, 4);
				qm.expandInventory(2, 4);
				qm.expandInventory(4, 4);
				qm.teachSkill(31100007, 1, 1);
				qm.changeJob(3110);
				qm.dispose();
			}
		}
	}
}