function act() {
    rm.changeMusic("Bgm34/LuminousSword");
    rm.spawnMonster(8810130, 71, 260);
    rm.mapMessage("The cave shakes and rattles, Chaos Horned Tail is summoned.");
	//rm.scheduleWarp(43200, 240000000);
	if (!rm.getPlayer().isGM()) {
		rm.getMap().startSpeedRun();
	}
}