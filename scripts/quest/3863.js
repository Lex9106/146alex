var status = -1;

function end(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    qm.sendNext("#b(A voice resonates in your head\r\nagain.)'The sacrifice will protect three, but it shall be destroyed\r\nthe moment thou set foot in front of the golden altar if thou dost\r\nnot enter, the sacrifice will not be taken.'");
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