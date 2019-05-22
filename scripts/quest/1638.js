/*
	[Silent Crusade] Arkarium, the Guardian of Time
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	 if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.forceStartQuest();
	  qm.dispose(); 
	  }
	 else if (status == 1) {
     qm.playSound(true, "Quest/Clear");
     qm.ShowWZEffect("Effect/BasicEff.img/QuestClear");
     qm.forceCompleteQuest();
     qm.warp(931050500,0);
	 qm.dispose(); 
	 }
    }
function end(mode, type, selection) {
	 if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendNext("I think Arkarium's back! Someone saw him at the Temple of Time,\r\nthough just for a moment...");
	}
	else if (status == 1) {
	  qm.sendNextPrevS("Who's Arkarium?",3);
	}
	else if (status == 2) {
	  qm.sendNextPrevS("You don't know THE Arkarium? He was one of the Black Mage's\r\nCommanders. After the Black Mage was sealed, he dissapeared.",0,3,9120219);
	}
	else if (status == 3) {
	  qm.sendNextPrevS("if one of the Black Mage's right-hand guys is back, doest that\r\nmean the Black Mage is back, too?",3);
	}
	else if (status == 4) {
	  qm.sendNextPrevS("I'm not sure, But something is amiss in Maple World, that much is\r\ncertain. The Return of Arkarium, the appearance of the Mystic\r\nGates... Evil forces are in motion.",0,3,9120219);
	}
	 else if (status == 5) {
     qm.playSound(true, "Quest/Clear");
     qm.ShowWZEffect("Effect/BasicEff.img/QuestClear");
     qm.forceCompleteQuest();
     qm.gainQuestExp(1642000);
	 qm.showEffect(true,"crossHunter/chapter/end2");
	 qm.dispose(); 
	 }
    }