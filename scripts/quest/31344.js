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
	    qm.sendNextS("Greetings #h \r\n! Can you hear me? Have you spoken\r\nwith the colossus?\r\n#b(it sounds like Guwaru. You tell him about the Stone Colossus.)",4,2133008);
	} else if (status == 1) {
	    qm.sendNextPrevS("The birth of this creature is something i had long thought\r\nimpossible. The spirits of hundreds, if not thousands, of lesser\r\nlife forms have combined to become one, gigantic entity.",4,2133008);	
    } else if (status == 2) {	 
	    qm.sendNextPrevS("This all began when my powers were abused by Magnus long\r\nago. The smaller spirits huddled together in fear at the corruption\r\nthat held me. Alone, they were mere insects. Together, they\r\ncould be safe. The lack of my guidance forced them to coalesce.",4,2133008);			
    }
	else if (status == 3) {	 
	    qm.sendNextPrevS("But this is unnatural, and dangerous. If these spirits were\r\ndispersed across the world, the corruption of a few would not\r\nbe a concern. But if they were fainted by dark energy in this\r\nconfined space, i fear the corruption would spread too fast to stop.",4,2133008);			
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