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
	    qm.sendNext("...");
	} else if (status == 1) {
	    qm.sendNextPrevS("(Someone bumps into you as they pass.)");	
    } else if (status == 2) {	 
	    qm.sendNextPrevS("Hey! Watch where are you're going!");			
    }	
	else if (status == 3) {	 
	    qm.sendYesNo("(it seems the man dropped something as he passed. Maybe you\r\nshould see what it is?)");	
    }
    	else if (status == 4) {	 
	    qm.sendNextPrevS("(When you take a closer look, you see that the object is a Pocket\r\nWatch. There's nothing odd about it, though the hands aren't\r\nmoving and the name #b#p2082004##k is engraved on the back.)");	
    }
else if (status == 5) {	 
	    qm.sendNextPrevS("Perhaps pressing the large button on the top will make the watch\r\nrun again...");			
    }	
	else if (status == 6 && qm.getMapId() != 240070000) {	 
	    qm.sendYesNo("Would you like to press the button on the watch?\r\n#b(Pressing#k#r Yes#k#b will teleport you to the Tera Forest Time Gate.)");			
    }
	else if (status == 6 && qm.getMapId() == 240070000) {	 	
    qm.sendNextPrevS("(You've decied to press the button.)");	
    }
	else if (status == 7 && qm.getMapId() != 240070000) {	 
	   qm.sendNextPrevS("(You've decied to press the button.)");		
    }
	else if (status == 7 && qm.getMapId() == 240070000) {	 	
    qm.forceCompleteQuest();
    }
	else if (status == 8 && qm.getMapId() != 240070000) {	 
	    qm.warp(240070000,1);
		qm.forceCompleteQuest();	
        qm.Dispose();			
    }
	}
  }