var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else 
        if (status == 0) {
		    qm.sendNext("Speak to me again if you change your mind.");
            qm.dispose();
        status--;
    }
	if (status == 0) {
	qm.sendNext("Before we proceed, you must understand that the #bGrand Athenaeum#k is no simple archive of books. There is greart magic here.");
    } else if (status == 1) {
	    qm.sendNextPrev("It is a living, breathing space, where all the knowledge in the universe is stored. Miuch like the Akashic Records of old, where magicians who supassed time and space gathered to share\r\ntheir knowledge. Rather grandiose, is it not?");
    } 
	else if (status == 2) {
	    qm.sendNextPrev("The records there can be rather troublesome. Some of the books\r\nseem to have personalities of their own, fitting about from room\r\nto room. There is a rather peculiar tome that seems to have taken\r\na liking to the coffe percolator in my reading room. No matter\r\nhow often i return it to its rightful place, it returns to my chambers.");
    }
	else if (status == 3) {
	    qm.sendYesNo("Aren't you curious to know what that book contained?");
    }
	else if (status == 4) {
	    qm.sendNext("Luckly, as i was completing my morning routine, I found the book open to a page full of odd writing. Once i deciphered the meaning,\r\nI realized it was a call for help. It said...\r\n#bTynerum is on the verge of collapse. Chaos rules, law is gone.\r\nThe Demons of Versal have fallen to ruin. Naricain returns.\r\nSend help.\r\n - Ridley");
    }
	else if (status == 5) {
	    qm.sendNextPrev("The name #bTynerum#k brings to mind stirrings of knowledge i had\r\nthought forgotten. I can recall little, though I believe it may have\r\nonce been the #bhome of the Demons.");
    }
	else if (status == 6) {
	    qm.sendNextPrev("I believe this strange message could warrant an investigation.\r\nParticularly with the string of Demons that have arrived in Maple\r\nWorld as of late.");
    }
	else if (status == 7) {
	    qm.sendNextPrev("Tynerum could give us a better understanding of their people...\r\nand what happened in the past that led us to where we are today.");
    }
	else if (status == 8) {
	    qm.forceStartQuest();
		qm.Dispose();
    }
}