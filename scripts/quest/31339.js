var status = -1;
function start(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    qm.sendYesNo("Okay. We're all fueled up. Ready to head out?");
	} else if (status == 1) {
		qm.ApplyBuff(80001277);
		qm.forceStartQuest();
		qm.warp(240090800);
		//qm.RemoveBuff(80001277);
		//1930000 Rien
		//1932165
		//1932154,1932152 look out
	    qm.Dispose();
    }	
	}
  }