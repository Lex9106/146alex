/*
	Title - Silent Crusade Officer
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	 if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendNext("Lora tells me you made quick work of the #bKing Centipede#k. I think\r\nit's time i promoted you to #b<Silent Crusade Officer>.");
	}
	 else if (status == 1) {
     qm.sendOk("Congratulations on becoming a #b<Silent Crusade Officer>#k! We're\r\nexpecting great things from you.");
     qm.gainItem(1142352,1);
     qm.forceCompleteQuest();
	 qm.dispose(); 
	  }
    }