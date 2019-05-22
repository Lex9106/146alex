var status = -1;

function start(mode, type, selection) {
	//qm.sendNext("Welcome to Crimsonstory!");
	qm.forceCompleteQuest();
	qm.sendOk("Now you have the permission to attempt Gollux.");
	qm.gainItem(4033981,3)
	qm.dispose();
}
function end(mode, type, selection) {
	qm.dispose();
}