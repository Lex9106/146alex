/*
	[Silent Crusade] Into the Gate
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendYesNo("I bet this weird gate has something to do with all the\r\nmonsters going crazy. I think we oughtta take a closer look,\r\nyou ready?");
	} else if (status == 1) {
      qm.sendNext("I'm counting on you to keep me safe from the big, bad,\r\nscary monsters! let's go!");
	} else if (status == 2) {
      qm.resetMap(931050410);		
	  qm.warp(931050410,2);
	  qm.forceStartQuest();
	  qm.dispose();
    }
}