/*
	[Silent Crusade] Progress Report
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	 if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendNext("The Mystic Gates are connected to the Black Mage\r\nsomehow. i'm sure of it! The gates seem to control nearby\r\nmonsters and drain the life right out anyone who gets\r\nnear them. Actually, why don't you tell him for me? i'll follow you\r\nwhen i'm done here.");
	}
	 else if (status == 1) {
     qm.sendYesNo("All right! I'll send you straight to Edelstein!");
	 }
	 else if (status == 2) {
     qm.forceStartQuest();
     qm.warp(310000000,0);
	 qm.dispose(); 
	 }
    }
	
function end(mode, type, selection) {
	 if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendNext("Did you find anything in Leafre? Come, let's discuss this\r\nsomewhere more private.");
	}
	 else if (status == 1) {
     qm.playSound(true, "Quest/Clear");
     qm.ShowWZEffect("Effect/BasicEff.img/QuestClear");
     qm.forceCompleteQuest();
     qm.warp(931050500,0);
	 qm.dispose(); 
	 }
    }