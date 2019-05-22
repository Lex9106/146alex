/* Dawnveil
    [Ellinel Fairy Academy] Cootie's Suggestion
	Cootie
    Made by Daenerys
*/

var status = -1;

function start(mode, type, selection) {
	if (mode == 1)
	    status++;
	 else
	    status--;
	if (status == 0) {
		qm.forceStartQuest();
		qm.dispose();
	}
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
	    qm.sendNext("You're here. And the thing you're holding in your hand is...?\r\n#fUI/UIWindow.img/QuestIcon/8/0#\r\n39913 exp");
	} else if (status == 1) {
		qm.forceCompleteQuest(32189);
	    qm.sendNextPrev("#i4030030##bA Fragment of Glacier Core#k\r\nThis is a fragment of thr glacier core... glacier core is a\r\nmysterious thing that has the power of extreme freezing\r\neffect. Needing not only one, but many of these... are they\r\ntrying to run a huge engine or something?");	
    } else if (status == 2) {	 
	    qm.sendNextPrevS("I sense something suspicious. What is their purpose\r\nbehind doing this?");
        qm.Dispose();		
    }		
	}
  }