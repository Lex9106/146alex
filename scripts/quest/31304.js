var status = -1;

function start(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    qm.sendNext("I was waiting for you. Unfortunately, Gryo Drop is currently\r\nout of service due to a maintenance.");
	} else if (status == 1) {
	    qm.sendNext("Yes? Do you need a Confirmation Stamp for the event? if\r\nyou could handle my request, then i will give you a stamp.\r\n#b#L01#Yes,what can i do for you?\r\n#L02#It is currently out of service. Could you not just give me\r\n a stamp?");	
    }
	else if (status == 2) {	 
	    if (selection == 1)	 {
	    qm.sendNext("I have called for a technician to repair? But i wish to know\r\n what is causing the problem. Could you climb up to the\r\ntop of Gyro Drop and see if there is anything strange?");
		}
		if (selection == 2) {
		qm.sendOk("...");	
		cm.Dispose();
    }	
	}
	else if (status == 3) {
    qm.forceStartQuest();
    }
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
	    qm.sendNext("I was waiting for you. Unfortunately, Gryo Drop is currently\r\nout of service due to a maintenance.");
	} else if (status == 1) {
	    qm.sendNext("Yes? Do you need a Confirmation Stamp for the event? if\r\nyou could handle my request, then i will give you a stamp.\r\n#b#L01#Yes,what can i do for you?\r\n#L02#It is currently out of service. Could you not just give me\r\n a stamp?");	
    }
	else if (status == 2) {	 
	    if (selection == 1)	 {
	    qm.sendNext("I have called for a technician to repair? But i wish to know\r\n what is causing the problem. Could you climb up to the\r\ntop of Gyro Drop and see if there is anything strange?");
		}
		if (selection == 2) {
		qm.sendOk("...");	
		cm.Dispose();
    }	
	}
	else if (status == 3) {
    qm.forceStartQuest();
    }
  }
}