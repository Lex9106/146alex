/* 
 Berserk 4th job quest rock - Warp you away
*/

function act() {
    rm.playerMessage(6, "Chaos Vellum has spawned! Be ready to fight!");
    rm.getMap().startMapEffect("You ignore my warnings? I will show you no mercy!", 5120103);
	rm.spawnMonster(8930000, 1, new java.awt.Point(-195, 443)); 
}