/*
	[Silent Crusade] The Desert Crusade
    Made by Alex
*/

var status = -1;
function end(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendNext("You're the new guy? Hmph. You look like a weakling! how'd you\r\npass Bastille's test?");
	} else if (status == 1) {
      qm.sendOk("What a pain, just don't get in my way!");
	} else if (status == 2) {
	  qm.forceCompleteQuest();
	  qm.dispose();
    }
}