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
	    qm.sendNext("One thing still bothers me. I just can't seem to figure out what\r\ncause the accident. During all my time traveling. I've never run\r\nacross anything that pointed at the truth.");
	} else if (status == 1) {
	    qm.sendNextPrevS("We did everything we could, right?");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("If we can't figure out what caused the accident, we can't stop it.\r\nI can't keep jumping around time for no reason. Now that the Time\r\nDiver is in one piece, maybe we can use it for good.");			
    }	
	else if (status == 3) {	 
	    qm.sendNextPrevS("Does the Time Diver tell you that too?");	
    }
    	else if (status == 4) {	 
	    qm.sendYesNo("No, but it might lead us to people who have close ties to the\r\npower of time. There must be someone out there that can help.\r\nWhat do you say?");	
    }
else if (status == 5) {	 
	    qm.sendNext("Trust your body to the power of the Time Diver. It will take you to\r\n the nearest time user.");			
    }	
	else if (status == 6) {	 
	    qm.forceCompleteQuest(12);
	    qm.forceStartQuest();
	    qm.warp(240000000,0);
		qm.Dispose();
		}
	}
  }