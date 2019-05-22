/*
	[Silent Crusade] Dark Energy Rising
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	 if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendYesNo("Hey, you and Starling are tight, right? That means you can do this\r\nmission in her place.");
	}
	 else if (status == 1) {
     qm.showEffect(true,"crossHunter/chapter/start2");
     qm.forceStartQuest();
	 qm.dispose(); 
	  }
    }