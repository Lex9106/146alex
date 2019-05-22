var status = -1;

function start(mode, type, selection) {
	qm.sendNext("Welcome to SERVER NAME HERE");
	qm.forceCompleteQuest();
	qm.dispose();
}
function end(mode, type, selection) {
	qm.dispose();
}