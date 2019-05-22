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
	if (status == 0 && qm.getMapId() != 863000001) {
	    qm.sendOkS("I need to find #m863000001#.",16);
		qm.Dispose();
	} else if (status == 0 && qm.getMapId() == 863000001) {
    qm.sendNextS("There are monster all over. Maybe they're blending in with the\r\nfires? There WAS that fire in the forest...",16);		
    }
	else if (status == 1 && qm.getMapId() == 863000001) {
    qm.sendYesNoS("Maybe i can just beat up a few?",16);		
    }	
	else if (status == 2 && qm.getMapId() == 863000001) {
    qm.sendNextS("I should take down 50 of these guys for now.?",16);		
    }
	else if (status == 3 && qm.getMapId() == 863000001) {
    qm.forceStartQuest();
    qm.Dispose();	
    }
	}
  }