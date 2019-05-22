
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
	    qm.sendYesNo("I heard about your deeds, You've done some great things\r\n\here, right?");
	} else if (status == 1) {
	    qm.sendNextPrev("So what did you find out?\r\n\r\n\r\nThe penguins, the malamutes, and the seals have been\r\non bad terms. I am glad to find them reconciled for now,\r\nbut on the other hand i think the incident could have been\r\nprevented if they had this sort of unity to begin with.");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("it's not necessary that everyone has the same heart and \r\nmind. But when there is a common enemy, it is\r\nnecessary that everyone unite their strenght. Please do\r\nremember this, as a part of Maple World.\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#i1142629# Deckhand\r\n#fUI/UIWindow.img/QuestIcon/8/0#\r\n38805 exp");			
    }	
	else if (status == 3) {	 
	    qm.gainItem(1142629,1);
		qm.gainQuestExp(38805 * 3);
		qm.forceCompleteQuest();
        qm.Dispose();		
    }		
	}
  }