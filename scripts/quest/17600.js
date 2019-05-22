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
	    qm.forceStartQuest();
		qm.Dispose();
	} else if (status == 1) {
	    qm.sendNextPrev("");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("");			
    }	
	else if (status == 3) {	 
	    qm.gainItem(1142629,1);
		qm.gainQuestExp(38805 * 3);
		qm.forceCompleteQuest();
        qm.Dispose();		
    }		
	}
  }