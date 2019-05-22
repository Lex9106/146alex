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
	    qm.sendNextS("I can't believe the Goddess would create such a vile and vicious\r\nmonster.",16);
	} else if (status == 1) {
	    qm.sendNextPrevS("This is only the beginning. There is so much more in store...",4,2159402);	
    } else if (status == 2) {	 
	    qm.sendNextPrevS("#b(The stranger's voice fades with the passing wind.)",16);			
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