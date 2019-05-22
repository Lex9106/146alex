/*
	[Silent Crusade] Mu Lung Crusade
    Made by Alex
*/

var status = -1;
function start(mode, type, selection) {
	  qm.forceStartQuest();
	  qm.dispose();
    }
function end(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
    if (status == 0) {
	  qm.sendNext("I'm glad you joined up! and so's my jaguar! just don't pet him. He'd\r\nbite you! hand clear off.");
	} else if (status == 1) {
     qm.forceCompleteQuest();
	 qm.dispose(); 
	  }
}