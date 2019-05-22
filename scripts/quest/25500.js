var status = -1;

function start(mode, type, selection) {
	qm.forceCompleteQuest();
	qm.forceCompleteQuest(25501);
	qm.dispose();
}
function end(mode, type, selection) {
    qm.sendNext("Thank you so much!");
	qm.forceCompleteQuest();
	qm.dispose();
}