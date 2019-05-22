/*
	[Silent Crusade] The Silent Crusade
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendYesNo("Yes, yes, i'm sure you're eager to know all about our little\r\noperation. But first thing's first! I must test your aptitude, Are\r\nyou ready to join the Silent Crusade? Well, are you?");
	} else if (status == 1) {
      qm.sendNext("We shall see. Your test is simple enough: Defeat my puppy,\r\nCoco. Shall we begin?");
	} else if (status == 2) {
    qm.resetMap(931050510);
    qm.warp(931050510, 0);
    qm.forceStartQuest();
    qm.dispose();
    }
}