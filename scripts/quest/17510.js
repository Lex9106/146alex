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
	if (status == 0 && qm.getMapId() != 863000002) {
	    qm.sendOkS("I should be heading toward #m863000002#.",16);
		qm.Dispose();
	} 
	else if (status == 0 && qm.getMapId() == 863000002) {
	    qm.sendNextS("Aw, man! Monsters here too?",16);
	}
	else if (status == 1) {	
	qm.sendYesNoS("Maybe i can just beat up a few?",16);
    }	
	else if (status == 2) {	
	qm.sendNextS("Hunting 50 should do.",16);
    }
	else if (status == 3) {	
	qm.forceStartQuest();
	qm.Dispose();
    }	
	}
  }