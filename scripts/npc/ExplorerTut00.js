/* RED 1st impact
    Above the Maple tree
    Made by Daenerys
*/

var status = -1;

function action(mode, type, selection) {
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	    cm.sendSelfTalk("T�i ... tr�n ��nh m�t c�y Maple?");
	} else if (status == 1) {	
	    cm.sendSelfTalk("Ch� c� c�ch �� �i l� xu�ng.");
	} else if (status == 2) {	
      //  cm.introEnableUI(0);
     //   cm.introDisableUI(false);
        cm.dispose();
    }
}