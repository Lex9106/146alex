
var status = -1;

function start(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (qm.getMapId() != 200000200) {
	if (status == 0) {
	    qm.sendNext("Where did Chryse go? I'd go get my telescope, but... ehhh.");
	} else if (status == 1) {
	    qm.sendNextPrev("Chryse? I'ts one of the floating island s up above Orbis. Hey,\r\nmaybe you should come see me. i can tell you all about it. Sound\r\ngood?");	
    } else if (status == 2) {	 
	    qm.sendYesNo("Great! I got this neat gizmo that'll zap you right to Orbis Park. You\r\nready?");			
    }	
	else if (status == 3) {	 
	qm.sendNext("Great, I will teleport you here in a jiffy.");   	
    }
	else if (status == 4) {	 
	qm.warp(200000200,0);
	qm.Dispose();
	qm.forceStartQuest();
    }	
	}
	else if (qm.getMapId() == 200000200) {
	if (status == 0) {
	    qm.sendNext("You ready to go now?");
	} else if (status == 1) {
	    qm.warp(200100001,0);
		qm.forceCompleteQuest();
		qm.Dispose();
    } 	
	}
	}
  }