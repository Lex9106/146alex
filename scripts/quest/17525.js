var status = -1;

function start(mode, type, selection) {
	qm.sendOk("Please Defeat Gollux's Right Shoulder.");
	qm.forceStartQuest();
	qm.dispose();
}
function end(mode, type, selection) {
	qm.forceCompleteQuest();
	qm.gainItem(4310097,1);
	qm.gainQuestExp(10000000);
	qm.dispose();
}