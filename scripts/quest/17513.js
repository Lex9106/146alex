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
	    qm.sendNextS("It's a dead end. There's nowhere to go...",16);
	} else if (status == 1) {
		qm.sendNextPrevS("Who knows what's beyond that illusion...",16);	
    }
	else if (status == 2) {
		qm.sendYesNoS("Better go talk to #b#p2134008##k for now.\r\n(Click Yes to Teleport.)",16);	
    }	
	else if (status == 3) {
		qm.getPlayer().dropMessage(5,"Teleporting to Jay-Jay.");
		qm.forceStartQuest();
		qm.warp(863100005,0);
		qm.Dispose();
		}
	}
  }