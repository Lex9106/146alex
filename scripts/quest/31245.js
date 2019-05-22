
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
	    qm.sendNextS("What did you find?\r\n\r\n#b(You tell Grendel everything you saw.)",1,0,1105004);
	} else if (status == 1) {
	    qm.sendNextPrevS("It sound as if Masteria is in chaos. Sacrifices and lawless\r\nclans... what a nightmare",1,0,1105004);	
    } else if (status == 2) {	 
	    qm.sendYesNoS("I believe there is more to this tale than meets the eye. Please help\r\nthe Demons, and see what more you can learn.",1,0,1105004);			
    }	
	else if (status == 3) {	
	    qm.sendNextS("Do take care not to get captured youself, won't you?\r\n\r\n#b(Return to #p9201103#.)",1,0,1105004);
		qm.forceStartQuest();
        qm.Dispose();		
    }		
	}
  }