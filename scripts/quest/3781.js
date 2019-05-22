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
	    qm.sendNext("You did not take your time, did you? Or perhaps an old man's\r\nmemories just move at such a slow pace that the young seem to\r\nflit about like mosquitoes, haha.");
	} else if (status == 1) {
	    qm.sendNextPrevS("Do you remember anything?");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("Among us Halflingers, there was a dragon trainer by the name of\r\n#b#p2411006##k. It was from him, where i heard something weird about\r\ntime. Mentioned some sort of seal and other stuff... I don't really\r\nremember.");			
    }	
	else if (status == 3) {	 
	    qm.sendNextPrevS("Where can i find this #p2411006#?");	
    }
    else if (status == 4) {	 
	    qm.sendNextPrev("A brilliant question. Unfortunately, nobody knows where he went, or if he's even still alive! Quite a pickle, I know.");	
    }
	else if (status == 5) {	 
	    qm.sendNextPrevS("(This seems to be a dead end... Wait, is the Timer Diver glowing?!)");	
     }	
	else if (status == 6) {	 
	    qm.warp(240000110,2);
		qm.forceStartQuest();
		qm.Dispose();
		}
	}
  }