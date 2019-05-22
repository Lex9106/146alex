/*
	[Silent Crusade] Starling&apos;s Proposal
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
	  qm.sendNext("I've been expecting you! come, let's go somewhere where we can speak in private.");
	} else if (status == 1) {
	   qm.warp(931050500,0);
       qm.forceCompleteQuest();
	   qm.dispose();
	} 
}