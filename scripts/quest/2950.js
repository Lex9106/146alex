var status = -1;

function start(mode, type, selection) {
	qm.forceCompleteQuest();
	qm.sendOk("Please, go see Pilot Irvin in Six Path Crossway to more information.");
	qm.dispose();
}
function end(mode, type, selection) {
	qm.dispose();
}