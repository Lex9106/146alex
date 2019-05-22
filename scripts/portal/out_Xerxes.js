function enter(pi) {
	if (pi.getQuestStatus(31013) == 1) {
	if (pi.getMap().getAllMonstersThreadsafe().size() == 0) {
    pi.warp(200101400, 1);
	pi.forceCompleteQuest(31013);
	pi.gainExp(67200 * 2);
    pi.addTrait("charisma", 10);
	}
	else {
    pi.getPlayer().dropMessage(5,"You must eliminate this monster.");
	}
	}
	else 
    {
     pi.warp(200101400, 1);
    }
}