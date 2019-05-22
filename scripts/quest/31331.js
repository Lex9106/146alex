var status = -1;
//sendNextPrevS("",16 Left side)
function start(mode, type, selection) {
if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    qm.sendNext("Minar Forest has always been known as a place of strange\r\noccurrences, but this one takes the provervial peach pie. A\r\nmountain that moves... amazing.");
	} else if (status == 1) {
	    qm.sendNextPrev("I don't mind telling you, I was as shocked as you are. My beard\r\nwas practically standing on end. But it's the truth, and that's a fact.");	
    } else if (status == 2) {	 
	    qm.sendNextPrev("When times like these come around, I pray to the great spirit\r\nGuwaru for guidance. Sure, sure, maybe the Black Mage had\r\ncontrol over him for a while, but he's rock-solid fella now, hehe.");			
    } else if (status == 3) {	 
	    qm.sendYesNo("We Halflingers are kin to the sky, wind, and forest. We know a\r\nfew tricks to contact the great spirits. If you'd like, I could arrange\r\na meeting right now.");			
    } else if (status == 4) {	 
	    qm.sendNextNoESC("All right then. Clear your noodle, focus on my topknot, and let's\r\ncontact a spirit! Lister carefully...");			
    } else if (status == 5) {	 
	    qm.sendNextPrevS("This is my fault. I could never have known a being like this would\r\nawaken...\r\n#b(You hear a strange and rumbling voice.)",4,2133008);			
    } else if (status == 6) {	 
	    qm.sendNextPrevS("I should have seen it coming. Long ago, when the Black Mage\r\ncompleted his betrayal, my powers were stonlen from me.\r\nEverything began dealt, and I neglected my duties as a guardian of the forest.",4,2133008);			
    } else if (status == 7) {	 
	    qm.sendNextPrevS("This bizarre scenario is my sole responsibility, but I am no more\r\nuse than an errant fly with my current powers. Perhaps a mortal\r\ntouch would be more apropiate. Please meet this colossus in\r\nmy place.",4,2133008);			
    } else if (status == 8) {	 
	    qm.sendNextPrevS("You will need some of my power to understand the great beast...\r\nThe transfer should not hurt, so long as it doesn't make you grow\r\nroots.\r\n#bGuwaru's light washes over you. You feel different, more...\r\nearthy.)",4,2133008);			
    }	
	  else if (status == 9) {	 
	    qm.sendNextPrevS("I will contact you again when i need you. Until then...",4,2133008);			
    } 	
	else if (status == 10) {	 
		qm.forceStartQuest();
        qm.Dispose();		
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
	    qm.sendNext("Well, You don't look like you just spoke to an ancient nature spirit,\r\nbut i suppose we'll know soon enough. Are you ready for a little\r\nadventure?\r\n\r\n#bYou know it! How do i get to the Stone Colossus?");
	} else if (status == 1) {
	    qm.sendNextPrev("Ah, humans. No patience, and not enough hair. I would advise\r\nyou yo seek out the Halflinger expedition that has already traveled\r\nto the area. They could help you.\r\n\r\n#bThere are Halflinger explorers?");	
    } else if (status == 2) {	 
	    qm.sendYesNo("Don't act so surprised! Our people are peaceful home-bodies for\r\nthe most part, but the blood of the explorer can shop up where\r\nyou least expect it. What kind of chief would I be if i held them\r\nback?\r\nIf you'd like, i can send you to their camp right away.");			
    } else if (status == 3) {	 
	    qm.sendNext("That's the spirit. Do an old-timer a favor and check on my villagers for me.");			
    } 
	  else if (status == 4) {	 
		qm.forceCompleteQuest();
		qm.gainQuestExp(875347 * 5);
		qm.warp(240090000);
        qm.Dispose();		
    }		
	}
  }