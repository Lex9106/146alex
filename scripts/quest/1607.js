/*
	[Silent Crusade] Stranger at the Gate
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  if (qm.getMapId() != 102040200) {
      qm.sendNextS("I should make a visit to Starling in the Relic Excavation Camp, (she gave me an item to go there).",3);
	  qm.dispose();
	  } else {
	  qm.sendNext("What're the odds... i didn't expect to see you again.");
	  }
	} else if (status == 1) {
      qm.sendNextPrevS("(Isn't that the woman you rescued in the Kening City\r\nSubway?) Hey, long time no see!",3);
	} else if (status == 2) {	 
	  qm.sendNextPrev("I came here to help out the Excavation Site, but it looks like\r\ni'm too late. Say, did you see a strange gate around here by\r\nany chance?");
    }
	else if (status == 3) {
      qm.sendNextPrevS("Yeah, i was it when i was fightning the Commander Skeleton\r\nin the Prohibited Area. It gave me the creeps.",3);
	}
	else if (status == 4) {	 
	  qm.sendYesNo("Really? i'd love to see it! Mind showing me where it is?");
    }
	else if (status == 5) {	 
	  qm.sendYesNo("All right! To the prohibited Area!");
    }
	else if (status == 6) {	 
	  qm.sendNext("Let's move!");
    }
	else if (status == 7) {	 
	  qm.warp(102040600,1);
	  qm.forceStartQuest();
	  qm.dispose();
    }
}