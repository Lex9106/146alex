/*
	Title - Silent Crusade Champion
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	 if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendNext("We've been watching your progress closely, #namehere.\r\nWe've seen you rise to the challenge again and again, and i think\r\nI speak on behalf of all the crusaders that it's an honor to work\r\nwith you. I hereby name you the #b<Silent Crusade Champion>.");
	}
	 else if (status == 1) {
     qm.sendOk("No matter what, we've got your back #b<Silent Crusade Champion>.");
     qm.gainItem(1142354,1);
     qm.forceCompleteQuest();
	 qm.dispose(); 
	  }
    }