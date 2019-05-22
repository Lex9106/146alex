/*
	[Silent Crusade] Chance? Or Fate?
    Made by Alex
*/
var status = -1;

function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
	if (status == 0) {
	  qm.sendNext("They're going to launch their attack any moment! we'll all be\r\nbeaten if we let it happen! Take them down!");
	} else if (status == 1) {
      qm.spawnMonster(9300470,290,64);
	  qm.spawnMonster(9300470,270,64);
	  qm.spawnMonster(9300470,260,64);
	  qm.spawnMonster(9300470,240,64);
	  qm.spawnMonster(9300470,220,64);
	  qm.spawnMonster(9300470,180,64);
	  qm.spawnMonster(9300470,160,64);
      qm.forceStartQuest();
	  qm.dispose();
    }   
	}
function end(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
	if (status == 0) {
	  qm.sendNext("You saved me. My hero. (This one did better than i expected...)\r\n#b(Wait, wasn't she injured?)");
	} else if (status == 1) {
      qm.sendNextPrev("Thanks again for everything.\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#fUI/UIWindow.img/QuestIcon/8/0#76900 exp");
      qm.gainQuestExp(76900);
	} else if (status == 2) {	 
	  qm.dispose();
   }
}