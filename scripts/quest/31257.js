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
	    qm.sendYesNo("Did you rescue all those poor demon kids?");
	} else if (status == 1) {
	    qm.sendNext("I have to tell you, I was worried when you first showed up\r\nYou're not exactly the same kind of warrior we're used to seeing\r\naround these parts!");	
    } else if (status == 2) {	 
	    qm.sendNext("I'm guessing you got a look at the soldiers in the upper keep?\r\nThey used to be pupils under a man name Crimsonheart...\r\nbefore they lost their way.\r\n\r\n#b#L0#What happened to them?");			
    }	
	else if (status == 3) {
	    qm.sendNext("People change during war. Naricain had ways of corrupting\r\neven the best of us.");	
    }	
	else if (status == 4) {
	    qm.sendNextPrev("I wish I knew more about what's happened since I was cursed,\r\nbut i don't exactly get around too often...");	
    }
	else if (status == 5) {
	    qm.forceStartQuest();
		qm.forceCompleteQuest();
		qm.gainQuestExp(29665*5);
		}	
	}
  }