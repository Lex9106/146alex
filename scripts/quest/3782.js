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
	    qm.sendNext("It was such a mysterious story. Grandma met an Explorer when she was young. And that Explorer told her about the Black Mage's Seal, as he left for the Temple of Time.");
	} else if (status == 1) {
	    qm.sendNextPrevS("The Black Mage...?! What does the Power of Time have to do with that Seal?");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("The Black Mage's Seal was made by Freud, one of the five heroes, and Afrien, the king of the Onyx Dragons. Freud and Afriend poured all of their magic powers into creating that seal. As an extra measure of caution. Afrien made it so that the seal could stop time itself.");			
    }	
	else if (status == 3) {	 
	    qm.sendNextPrevS("So, you're saying that time itself was sealed off??");	
    }
    else if (status == 4) {	 
	    qm.sendNextPrev("You could say that. The timestream was sort of... tied off at the precise moment the seal was created. No one could go back in time before the seal was created, even someone who was otherwise able to move through time. It was a powerful spell.");	
    }
	/*else if (status == 5) {	 
	    qm.sendNextPrevS("(This seems to be a dead end... Wait, is the Timer Diver glowing?!)");	
     }	*/
	else if (status == 5) {	 
	    qm.warp(240070000,0);
		qm.forceStartQuest();
		qm.Dispose();
		}
	}
  }