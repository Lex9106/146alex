/*
	[Silent Crusade] A Cry for Help
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendYesNo("Somebody! Anybody! Help!");
	} else if (status == 1) {
      qm.sendNext("A lady followed a bunch of creeps into the Subway. They\r\nlookked really dangerous. Can you head into the #bSubway\r\nConstruction Site#k and make sure she's okay?");
	} else if (status == 2) {
    qm.sendNextPrev("I'll send you into the Subway Tickering Booth, Hurry!");
    }
	 else if (status == 3) {
    qm.gainItem(2030028,1);
	qm.showEffect(true, "crossHunter/chapter/start1"); 
	qm.forceStartQuest();
	qm.dispose();
    }
}