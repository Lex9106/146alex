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
	    qm.sendNext("#b(A voice resonates in your head\r\nagain.)'The sacrifice will protect three, but it shall be destroyed\r\nthe moment thou set foot in front of the golden altar if thou dost\r\nnot enter, the sacrifice will not be taken.'\r\n(Massive doors open the way the the #m252030000#.)");
	} else if (status == 1) {
	    qm.gainQuestExp(300000 * 3);	
		qm.warp(252030000,5);
		qm.forceCompleteQuest();
		qm.Dispose();
    }		
	}
  }