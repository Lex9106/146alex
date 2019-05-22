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
	    qm.sendNextS("(Tell Grendel everything you heard from Ridley.)",16);
	}
	else if (status == 1) {
	    qm.sendNextS("A war let by the demon Naricain, eh? Fascinating...",0,0,1105004);
	} else if (status == 2) {
	    qm.sendYesNoS("Can you see what's outside Crimsonwood Keep? Perhaps a\r\ngreater perspective would help.",1,0,1105004);	
    } else if (status == 3) {	 
	    qm.sendNextS("Very well.\r\n(Exit the keep through the door on the bottom right.)",1,0,1105004);			
    }	
	else if (status == 4) {
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
	    qm.sendNextS("Who was that? He didn't seem like an ordinary demon...",16);
	} else if (status == 1) {
	    qm.sendNextPrevS("This barrier is too strong to get through... I'll have to turn back.",16);	
    } else if (status == 2) {	 
	    qm.forceCompleteQuest();
        qm.gainQuestExp(1132024 * 5);		
    }	
  }
  }