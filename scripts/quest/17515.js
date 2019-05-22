var status = -1;
//sendNextPrevS("",16 Left side)
function end(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0 && qm.getMapId() != 863100006) {
	    qm.sendOkS("I should be heading toward #m863100006#.",16);
		qm.Dispose();
	} 
	else if (status == 0 && qm.getMapId() == 863100006) {
	    qm.sendNextS("This must be the place #p2134008# was talking about.",16);
	}
	else if (status == 1) {	
	qm.sendNextPrevS("I can't stop here... I must press on.",16);
    }	
	else if (status == 2) {	
	qm.gainQuestExp(73780 * 5);
	qm.forceCompleteQuest();
	qm.Dispose();
    }	
	}
  }