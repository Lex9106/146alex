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
	    qm.sendYesNo("Okay, are you set to go? We're going to take the elevator up the\r\nside of the Stone Colossus, and it's going to be a long trip. Make\r\nsure you're ready");
	} else if (status == 1) {
	    qm.sendNext("If the bugs come, you're going to need a gun. Martino's gonna be \r\nat my side, but i'll give you a loaner rifle.\r\n\r\n#r(Left-click to fire your gun.)");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("#fMob/8147005.img/stand/0#\r\nDon't give those hornets a change to get close, but be careful\r\nabout your ammo. We only have a limited supply.");			
    }
	else if (status == 3) {	 
	    qm.sendNextPrev("#fMob/8147006.img/stand/0#\r\nDon't shoot at the Poison Hornets when they attack. That poison\r\nof theirs can be fatal.");			
    }
    else if (status == 4) {	 
	    qm.sendNextPrev("#fMob/8147007.img/stand/0#\r\nThe General Hornets are though to hit and hard to see, but take one of them out, and the others should chicken out.");			
    }	
	else if (status == 5) {	 
	    qm.sendNextPrev("Enough small talk. Let's get started.");			
    }
	else if (status == 6) {	 
	    qm.warp(240091600);
		qm.forceStartQuest();
        qm.Dispose();		
    }		
	}
  }