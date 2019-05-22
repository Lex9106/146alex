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
	if (status == 0) {
	    qm.sendNextS("#b(The Stone Colossus's trembling subsied. A feeling of calm and \r\npeace washes over the area.)#k\r\nWhew, i think the Stone Colossus is out of harm's way for now.",16);
	} else if (status == 1) {
	    qm.sendNextPrevS("I'd better see how old rock-face is doing.",16);	
    } else if (status == 2) {
        qm.gainItem(2431733,1);	
        qm.gainItem(1182059,1);			
	    qm.forceCompleteQuest();
		qm.gainQuestExp(4084953*5);
		qm.dispose();
    }
	else if (status == 3) {
        qm.warp(240092000);	
        qm.forceCompleteQuest();
        qm.Dispose();		
	    //qm.sendNextPrevS("But this is unnatural, and dangerous. If these spirits were\r\ndispersed across the world, the corruption of a few would not\r\nbe a concern. But if they were fainted by dark energy in this\r\nconfined space, i fear the corruption would spread too fast to stop.",4,2133008);			
    }
    else if (status == 4) {	 
	    qm.sendNextPrevS("The catastrophes that colossus could create, if left under the\r\ninfluence of evil powers... I cannot begin to think. If only i had not\r\nallower myself to fall victim to the Black Mage's idiotic plans.",4,2133008);			
    }	
	else if (status == 5) {	 
	    qm.sendNextPrevS("You must help the Stone Colossus regain its pure heart. It would\r\nbe disastrous if the creature were to fall to evil.",4,2133008);			
    }
	else if (status == 6) {	 
		qm.forceStartQuest();
        qm.Dispose();		
    }		
	}
  }