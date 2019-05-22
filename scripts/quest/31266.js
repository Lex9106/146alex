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
	    qm.forceCompleteQuest();
		qm.dispose();
	}
	else if (status == 1) {
	    qm.sendNextPrevS("I see. This investigation can go no further. The barrier must be\r\nrepeling all outsiders.",0,0,1105004);
	} else if (status == 2) {
	    qm.sendNextPrevS("I feel that this is not the end of our Masterian search. That barrier\r\ncannot stay up forever...",1,0,1105004);	
    } else if (status == 3) {	 
	    qm.sendNextPrevS("Your investigation was quite interesting, wasn't it? As a scholar,\r\nI could not resist pushing you through it.",1,0,1105004);			
    }	
	else if (status == 4) {	 
	    qm.sendNextPrevS("I will contact you should some passageway to knowledge open\r\nitself.\r\n#i1142619##b#z1142619#",1,0,1105004);			
    }
	else if (status == 5) {
	qm.gainItem(1142619,1);
	qm.forceCompleteQuest();
	qm.dispose();	
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
        qm.dispose();		
    }	
  }
  }