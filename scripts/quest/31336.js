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
	    qm.sendNext("Who are you? Come to meet the famous driver, master of the\r\nwheel, Kupo?! I knew it. My fame is spreading across Maple\r\nWorld. It'll be a hundred million mesos for an autograph, but i'll let\r\nyou take a picture with me for 75.");
	} else if (status == 1) {
	    qm.sendNextPrev("Not even a chuckle?\r\nLet me introduce myself like a proper gent. The name's Kupo.\r\nRhymes with 'eww, slow'.");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("You're not much for small talk, are you? I get it. I respect your\r\nneed for speed.\r\nGetting up to the Stone Colossus ain't exactly a walk in the park,\r\nbut i've got the ride that will make it a trip to remember. Of course,\r\nnobody rides for free...");			
    } else if (status == 3) {	 
	    qm.sendYesNo("Your job's simple enough. I keep the motor running, you do\r\neverything else. Don't worry about messing up, just trust your\r\ninstincts. You think you can handle that?");			
    } else if (status == 4) {
	    qm.sendNext("That's right! Let's do this!");	
    } else if (status == 5) {
		qm.forceStartQuest();
        qm.Dispose();
    }		
	}
  }