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
	    qm.sendNext("Kona tells me i'm in your debt. Just remember, you can help out \r\nall you want, just don't come crying when you lose a foot. Now\r\nwhat do you want?");
	} else if (status == 1) {
	    qm.sendNextPrev("You want my story? i guess i can do that...");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("I brought this expedition out to take a look into the earthquakes.\r\nHad a pretty good team up here before the bugs drove them off.");			
    } else if (status == 3) {	 
	    qm.sendNextPrev("Of course, we lost a few good explorer when they found out\r\nthe mountain itself was moving. You should've seen the look on\r\nThumbo's face... But i don't scare so easily. I've been studying\r\nthis mountain since i was fifteen, and i'm gonna keep exploring it\r\nuntil i'm old and gray.");			
    } else if (status == 4) {
	    qm.sendNextPrev("I'll tell you what though, fifteen year old me did not expect to see\r\nthis mountain start moving by itself.");	
    } else if (status == 5) {
	    qm.sendNextPrev("The shaking was so bad, i thought the whole world was ending.\r\nI saw that big mouth start moving and thought we were all going\r\ninto its belly, but i think it was trying to say something.");	
    } else if (status == 6) {
	    qm.sendNextPrev("Then it just stopped. And the monster came... That's when most\r\nof my crew went back down the hill. I couldn't let them stay in danger, but i couldn't give up either. And that's my story.");	
    } else if (status == 7) {	 
		qm.forceStartQuest();
        qm.Dispose();		
    }		
	}
  }