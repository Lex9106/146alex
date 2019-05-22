var status = -1;

function start(mode, type, selection) {
	qm.forceStartQuest();
	qm.dispose();
}
function end(mode, type, selection) {
    if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;

	if (status == 0) {
		qm.sendNext("Thank you, it's finally done... just one sip... *glup*... It seems it doesn't work either!");
        qm.forceCompleteQuest();
    if (status == 1) {
	    qm.sendNextPrev("There is an obelisk at Sharp Cliff I near El Nath. Behind it, there's a path to the Holy Ground at the Snowfield. Touch the Holy Stone there, and you'll be warped to another dimension. Your enemy is waiting for you there.");
	} else if (status == 2) {
	    qm.sendNextPrev("Bring me proof of your victory, and we'll see if you're ready.");
	} else if (status == 3) {
	    qm.forceStartQuest();
	    qm.dispose();
	}
    }
}
}