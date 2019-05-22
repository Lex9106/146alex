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
	    qm.sendNextS("This is just like the Shadow Veil Forest, where demons lived.",16);
	} 
	else if (status == 1) {
	    qm.sendNextPrevS("I see... something",16);
	}
	else if (status == 2) {	
	qm.sendNextPrev("Get out, peon.");
    }	
	else if (status == 3) {	
	qm.sendNextPrevS("Nobody ever invites me in, and i'm such a nice house guest. Who\r\nare you?",16);
    }
	else if (status == 4) {	
	qm.sendNextPrev("How dare you set friendly talk to me?");  
    }	
	else if (status == 5) {	
	qm.sendNextPrevS("This place is abandoned. And i asked you a question. Who are you?",16);  
    }
	else if (status == 6) {	
	qm.sendNextPrev("Leave.");  
    }
	else if (status == 7) {	
	qm.forceStartQuest();
    qm.Dispose();	
    }
	}
  }
  
  function end(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    qm.sendNextS("Well, she's gone.",16);
	} 
	else if (status == 1) {
	    qm.sendNextPrevS("Something weird is going on.",16);
	}
	else if (status == 2) {	
	qm.gainQuestExp(73780 * 5);
	qm.forceCompleteQuest();
    qm.Dispose();
    }	
	}
  }