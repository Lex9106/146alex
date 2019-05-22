/* Gollux PQ
By AlexRmz
*/
function enter(pi) {
	if (pi.getMapId() == 863010100) {
    if (pi.getPortal().getId() == 9) {
    pi.warp(863010220,"out00");
	}
	if (pi.getPortal().getId() == 7) {
    pi.warp(863010400,2);
	}
	if (pi.getPortal().getId() == 6) {
    pi.warp(863010300,1);
	}
	}
	else if (pi.getMapId() == 863010400) {
	if (pi.getPortal().getId() == 2) {
	pi.warp(863010100,7);
	}
	else if (pi.getPortal().getId() == 1 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010410).getAllMonstersThreadsafe().size() == 3) {
    pi.warp(863010410,1);
	pi.environmentChange(true,"open");
	pi.environmentChange(true,"clear");
	}
	else if (pi.getPortal().getId() == 1 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010410).getAllMonstersThreadsafe().size() > 3) {
    pi.warp(863010410,1);
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
else if (pi.getMapId() == 863010410) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010400,1);
	}
	else if (pi.getPortal().getId() == 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3) {
    pi.warp(863010420,1);
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
else if (pi.getMapId() == 863010200) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010100,0);
	}
	else if (pi.getPortal().getId() == 3 && pi.getMap().getAllMonstersThreadsafe().size() == 3) {
    pi.warp(863010210,1);
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
else if (pi.getMapId() == 863010310) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010300,2);
	}
	else if (pi.getPortal().getId() == 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3) {
    pi.warp(863010320,2);
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
else if (pi.getMapId() == 863010300) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010100,6);
	}
		else if (pi.getPortal().getId() == 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010310).getAllMonstersThreadsafe().size() == 3) {
    pi.warp(863010310,1);
	pi.environmentChange(true,"open");
	pi.environmentChange(true,"clear");
	}
	else if (pi.getPortal().getId() == 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010310).getAllMonstersThreadsafe().size() > 3) {
    pi.warp(863010310,1);
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
else if (pi.getMapId() == 863010320) {
	if (pi.getPortal().getId() == 1 && pi.getMap().getAllMonstersThreadsafe().size() == 3) {
	pi.warp(863010500,1);
	}
	else if (pi.getPortal().getId() == 2 && pi.getMap(863010310).getAllMonstersThreadsafe().size() == 3) {
    pi.warp(863010310,2);
	pi.environmentChange(true,"open");
	pi.environmentChange(true,"clear");
	}
	else if (pi.getPortal().getId() == 2 && pi.getMap(863010310).getAllMonstersThreadsafe().size() > 3) {
    pi.warp(863010310,2);
	}
	else if (pi.getPortal().getId() == 3 && pi.getQuestStatus(47) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010330).getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010330,1);
	pi.spawnMonster(9390610, 1, new java.awt.Point(8, -179));
	}
	else if (pi.getPortal().getId() == 3 && pi.getQuestStatus(47) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010330).getAllMonstersThreadsafe().size() == 1) { 
    pi.warp(863010330,1);
	}
	else if (pi.getPortal().getId() == 3 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getQuestStatus(47) == 2) {
    pi.warp(863010330,1);
	pi.environmentChange(true,"clear");
	pi.environmentChange(true,"phase3");
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
	else if (pi.getMapId() == 863010220) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010100,"in02");
	}
	else if (pi.getPortal().getId() == 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3) {
    pi.warp(863010230,"out00");
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
    else if (pi.getMapId() == 863010230) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010220,"in00");
	}
	if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010240).getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010240,"out01");
	pi.spawnMonster(9390612, 1, new java.awt.Point(-1, -59));
	}
	else if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010240).getAllMonstersThreadsafe().size() == 1) { 
    pi.warp(863010240,"out01");
	}
	else if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) == 2) {
    pi.warp(863010240,"out01");
	pi.environmentChange(true,"change");
	pi.environmentChange(true,"clear1");
	pi.environmentChange(true,"clear2");
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
else if (pi.getMapId() == 863010420) {
	if (pi.getPortal().getId() == 1 && pi.getMap(863010410).getAllMonstersThreadsafe().size() == 3) {
	pi.warp(863010410,"in00");
	pi.environmentChange(true,"open");
	pi.environmentChange(true,"clear");
	}
	else if (pi.getPortal().getId() == 1 && pi.getMap(863010410).getAllMonstersThreadsafe().size() > 3) {
	pi.warp(863010410,"in00");
	}
	else if (pi.getPortal().getId() == 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3) {
	pi.warp(863010500,5);
	}
	else if (pi.getPortal().getId() == 3 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getQuestStatus(46) != 2 && pi.getMap(863010430).getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010430,1);
	pi.spawnMonster(9390611, 1, new java.awt.Point(76, -156));
	}
	else if (pi.getPortal().getId() == 3 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getQuestStatus(46) != 2 && pi.getMap(863010430).getAllMonstersThreadsafe().size() == 1) { 
    pi.warp(863010430,1);
	}
	else if (pi.getPortal().getId() == 3 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getQuestStatus(46) == 2) {
    pi.warp(863010430,1);
	pi.environmentChange(true,"clear");
	pi.environmentChange(true,"phase3");
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
else if (pi.getMapId() == 863010210) {
   if (pi.getPortal().getId() == 1) {
	pi.warp(863010200,2);
	}
	if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010240).getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010240,2);
	pi.spawnMonster(9390612, 1, new java.awt.Point(-1, -59));
	}
	else if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 3 && pi.getMap(863010240).getAllMonstersThreadsafe().size() == 1) { 
    pi.warp(863010240,2);
	}
	else if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) == 2) {
    pi.warp(863010240,2);
	pi.environmentChange(true,"change");
	pi.environmentChange(true,"clear1");
	pi.environmentChange(true,"clear2");
	}
	else {
	pi.getPlayer().dropMessage(5,"You must eliminate the evil energy in this map to pass.");	
	}
}
else if (pi.getMapId() == 863010240) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010230,"in00");
	}
	if (pi.getPortal().getId() == 2) {
	pi.warp(863010210,2);
	}
	else if (pi.getPortal().getId() == 3 && pi.getMap().getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010500,"out00");
	}
}
else if (pi.getMapId() == 863010430) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010420,3);
	}
	else if (pi.getPortal().getId() == 3 && pi.getMap().getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010500,6);
	}
}
else if (pi.getMapId() == 863010330) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010320,"in00");
	}
	else if (pi.getPortal().getId() == 3 && pi.getMap().getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010500,4);
	}
}
else if (pi.getMapId() == 863010600) {
	if (pi.getPortal().getId() == 2) {
	pi.warp(863010500,3);
	}
}
else if (pi.getMapId() == 863010500) {
	if (pi.getPortal().getId() == 1) {
	pi.warp(863010320,1);
	}
	if (pi.getPortal().getId() == 5) {
	pi.warp(863010420,2);
	}
	else if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 0 && pi.getMap(863010240).getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010240,"in00");
	pi.spawnMonster(9390612, 1, new java.awt.Point(-1, -59));
	}
	else if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 0 && pi.getMap(863010240).getAllMonstersThreadsafe().size() == 1) { 
    pi.warp(863010240,"in00");
	}
	else if (pi.getPortal().getId() == 2 && pi.getQuestStatus(45) == 2) {
    pi.warp(863010240,"in00");
	pi.environmentChange(true,"change");
	pi.environmentChange(true,"clear1");
	pi.environmentChange(true,"clear2");
	}
	else if (pi.getPortal().getId() == 6 && pi.getQuestStatus(46) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 0 && pi.getMap(863010430).getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010430,"east00");
	pi.spawnMonster(9390611, 1, new java.awt.Point(76, -156));
	}
	else if (pi.getPortal().getId() == 6 && pi.getQuestStatus(46) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 0 && pi.getMap(863010430).getAllMonstersThreadsafe().size() == 1) { 
    pi.warp(863010430,"east00");
	}
	else if (pi.getPortal().getId() == 6 && pi.getQuestStatus(46) == 2) {
    pi.warp(863010430,"east00");
	pi.environmentChange(true,"phase3");
	pi.environmentChange(true,"clear");
	}
	else if (pi.getPortal().getId() == 4 && pi.getQuestStatus(47) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 0 && pi.getMap(863010330).getAllMonstersThreadsafe().size() == 0) { 
    pi.warp(863010330,"west00");
	pi.spawnMonster(9390610, 1, new java.awt.Point(8, -179));
	}
	else if (pi.getPortal().getId() == 4 && pi.getQuestStatus(47) != 2 && pi.getMap().getAllMonstersThreadsafe().size() == 0 && pi.getMap(863010330).getAllMonstersThreadsafe().size() == 1) { 
    pi.warp(863010330,"west00");
	}
	else if (pi.getPortal().getId() == 4 && pi.getQuestStatus(47) == 2) {
    pi.warp(863010330,"west00");
	pi.environmentChange(true,"phase3");
	pi.environmentChange(true,"clear");
	}
	else if (pi.getPortal().getId() == 3 && pi.getQuestStatus(48) != 2 && pi.getMap(863010600).getAllMonstersThreadsafe().size() == 1) { 
    pi.warp(863010600,2);
	pi.spawnMonster(9390600, 1, new java.awt.Point(5, 61));
	}
	else if (pi.getPortal().getId() == 3 && pi.getQuestStatus(48) == 2) { 
    pi.warp(863010600,2);
	pi.ApplyBuff(1146);
	pi.ApplyBuff(1142);
	pi.RemoveBuff(1146);
	}
	else if (pi.getPortal().getId() == 3 && pi.getQuestStatus(48) != 2 && pi.getMap(863010600).getAllMonstersThreadsafe().size() == 2) { 
    pi.warp(863010600,2);
	}
	else if (pi.getPortal().getId() == 3 && pi.getQuestStatus(48) == 2) {
    pi.warp(863010600,2);
	}
}
}