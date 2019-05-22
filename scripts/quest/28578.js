/* AlexRmz
    Prize Wheel
	Maple Administrator
*/
var status = -1;

function end(mode, type, selection) {
	   if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    qm.sendNext("Oh my... you're so lucky did you know?");
	} else if (status == 1) {
	    qm.sendNextPrevS("Ummm... if you say so... it just look like an useless old piece of paper.",16);	
    } else if (status == 2) {	 
	    qm.sendNextPrev("I'm not going to scam you, i'll reward you like you deserve, just don't tell anyone about this.\r\n#fUI/UIWindow2.img/QuestIcon/4/0#\r\n#i4400002#x1 Wheel of Marvels\r\n#fUI/UIWindow2.img/QuestIcon/7/0#\r\n5,000,000 Mesos\r\n#fUI/UIWindow2.img/QuestIcon/8/0#\r\n50,000\r\n#fUI/UIWindow2.img/QuestIcon/11/0#\r\nAll Trait Exp: 100");			
    }	
	else if (status == 3) {
	    qm.sendOkS("!!!",16);	
    }
	else if (status == 4) {	 
	    qm.gainItem(4400002,1);
		qm.gainItem(4031349,-1);
		qm.gainMeso(5000000);
		qm.gainQuestExp(50000);
		qm.addTrait("will",100);
		qm.addTrait("charm",100);
		qm.addTrait("sense",100);
		qm.addTrait("charisma",100);
		qm.addTrait("insight",100);
		qm.addTrait("craft",100);
		qm.forceCompleteQuest();
        qm.dispose();		
    }	
}
}