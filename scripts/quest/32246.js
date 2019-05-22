var status = -1;
//sendNextPrevS("",16 Left side)
function start(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    qm.forceCompleteQuest();
		//qm.forceStartQuest(17517);
		qm.Dispose();
	} 
	}
  }