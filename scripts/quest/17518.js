/* Dawnveil
	[Tynerum] A Brush with Hilla
	Hilla
    Made by Daenerys
*/
var status = -1;

function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
	if (status == 0) {
	  qm.sendNext("So, you came.");
	} else if (status == 1) {
      qm.sendNextPrevS("...Hilla?",16);
    } else if (status == 2) {	 
	  qm.sendNextPrev("I told you to go away, but you wouldn't listen.");
    } else if (status == 3) {		
	  qm.sendNextPrevS("YOU're the one behind all the nonsense!",16);
	} else if (status == 4) {	
	  qm.sendNextPrev("What of it?");
	} else if (status == 5) {	
	  qm.sendNextPrevS("What are you scheming?!",16);
	} else if (status == 6) {	
	  qm.sendNextPrev("Scheming? I'm not scheming. I'm working on getting what I want.");
	} else if (status == 7) {
	  qm.sendNextPrevS("I'll stop whatever horrible ritual you've got cooked up!",16);
	} else if (status == 8) {
	  qm.sendYesNo("You really don't have to beg me to destroy you. I'll happily oblige.");
	} else if (status == 9) {
	  qm.sendNext("Are you ready to join my undead army? It'll only take a minute.");
	} else if (status == 10) {
	  qm.warp(863100105);
	  qm.spawnMonster(9391015,-401,131);
	  qm.spawnMonster(9391016,-401,131);
	  qm.spawnMonster(9391017,-401,131);
	  qm.spawnMonster(9391018,-401,131);
	  qm.spawnMonster(9391019,-401,131);
      qm.forceStartQuest();
	  qm.getPlayer().dropMessage(5,"Hilla's spell is sucking you in.");
      qm.dispose();
	}
}
function end(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
	if (status == 0) {
	  qm.sendNext("You broke through my servants? Not bad.");
	} else if (status == 1) {
      qm.sendNextPrev("Unfortunately for you, you won't be able to do something agains't me");
    } else if (status == 2) {	 
	  qm.sendNextPrev("You sure you have time to chase me? Should't you be making\r\nyour bed or something?");
    } else if (status == 3) {		
	  qm.sendNextPrev("You'll eventually find out. Ciao!");
	} else if (status == 4) {	
	  qm.gainQuestExp(73780 * 5);
	  qm.forceCompleteQuest();
	  qm.Dispose();
	} 
}