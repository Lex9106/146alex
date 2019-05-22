var status = -1;

function start(mode, type, selection) {
	qm.sendOk("Please Defeat Gollux's Head.");
	qm.forceStartQuest();
	qm.dispose();
}
function end(mode, type, selection) {
	qm.dispose();
}