/* RED 1st impact
    Maple Tree Hill
    Made by Daenerys
*/

var status = -1;

function action(mode, type, selection) {
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	    cm.sendSelfTalk("C� g�i �� l� ai? T�i sao c� �y b� ch�y khi c� �y nh�n th�y t�i?");
	} else if (status == 1) {	
	    cm.sendSelfTalk("C� l� t�i s� theo c� �y..");
	} else if (status == 2) {
        cm.introEnableUI(0);
        cm.introDisableUI(false);
        cm.dispose();
    }
}