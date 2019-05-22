/*
	[Silent Crusade] Ludus Lake Dispatch
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendNext("I've heard reports of a Mystic Gate near Ludus Lake. Meet #b#p9120215##k at the #r#m220040200##k. He'll fill you in.");
	}
	else if (status == 1) {
	  qm.sendYesNo("Please go now.");
	}
	else if (status == 2) {
	  qm.sendYesNo("Good luck young Crusader!");
	}
	else if (status == 3) {
      qm.forceStartQuest();
      qm.dispose();
}
}