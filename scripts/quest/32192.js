
var status = -1;

function start(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    qm.sendNext("Thank you brave warrior.");
	} else if (status == 1) {
	    qm.sendNextPrev("I want to give you this present.\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#i1012389# Sailor's Mask 1\r\n#i1132292# Sailor Belt 1\r\n#fUI/UIWindow.img/QuestIcon/8/0#\r\n11087 exp\r\n#fUI/UIWindow.img/QuestIcon/7/0#\r\n2,000,000 mesos");	
    } else if (status == 2) {	 
	    qm.gainItem(1012389,1);
		qm.gainItem(1132292,1);
		qm.gainMeso(2000000);
		qm.gainQuestExp(11087 * 3);
		qm.forceCompleteQuest();
        qm.Dispose();		
    }		
	}
  }