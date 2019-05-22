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
	    qm.sendNextS("#b(You told Pepper why you are here.)",16);
	} else if (status == 1) {
	    qm.sendNext("You don't look like the other people of Masteria. Are you from\r\nVersal, like us? No... my people are too weak to protect\r\nthemselves.\r\n\r\n#L0##bI came here through the Dimensional Gate. Is here someone I\r\ncan get to help you?");	
    } else if (status == 2) {	
        if (selection == 0)	{
	    qm.sendNext("You-you're from another dimension? ...I don't know if i can\r\nbelieve you, but i guess i don't have any other choice. Besides,\r\nyou are the first person who hasn't attempted to harm us in ages.\r\nMasteria is a lawless zone. No countries or cities exist... It's\r\ncomplete anarchy. We were foraging for food when monsters\r\ncame and took us.\r\n#L0##bWhy did they kidnap you?");	
    }		
    }	
	else if (status == 3) {	
        if (selection == 0)	{
	    qm.sendNext("I-I believe we are meant to be #bsacrificed.\r\n#L0##bSacrificed?!");	
    }		
    }
	else if (status == 4) {	
        if (selection == 0)	{
	    qm.sendNext("Maybe you really ARE from a different dimension... People have\r\ndissapearing around here for years. I'm not sure why you\r\ncame here though... we never sent out a distress call.\r\n#L0##bIs there a sorcered named #p9201103# here?");	
    }		
    }
	else if (status == 5) {	
	    qm.sendYesNo("Never head of him.");			
    }	
	else if (status == 6) {	
	    qm.sendOk("There's no one named #p9201103# here.\r\n#b(Search for #p9201103#.)");		
        qm.forceStartQuest();
	}
	}
  }