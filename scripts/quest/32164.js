var status = -1;

function start(mode, type, selection) {
	qm.gainItem(4030022,1);
	qm.forceCompleteQuest();
	qm.dispose();
}
function end(mode, type, selection) {
   qm.forceCompleteQuest();
	qm.dispose();
}