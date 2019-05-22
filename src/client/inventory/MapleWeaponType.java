package client.inventory;

public enum MapleWeaponType {

    ROD(1.0f, 30),
    SOUL_SHOOTER(1.0f, 30),
    WHIP_BLADE(1.0f, 30),
    FAN(1.0f, 30),
    ZERO_SWORD(1.0f, 30),
    NOT_A_WEAPON(1.0f, 20),
    BOW(1.0f, 15),
    CLAW(1.0f, 15),
    CANE(1.0f, 15), // TODO: Renegades
    DAGGER(1.0f, 20),
    CROSSBOW(1.0f, 15),
    AXE1H(1.0f, 20),
    SWORD1H(1.0f, 20),
    BLUNT1H(1.0f, 20),
    AXE2H(1.0f, 20),
    SWORD2H(1.0f, 20),
    BLUNT2H(1.0f, 20),
    POLE_ARM(1.0f, 20),
    SPEAR(1.0f, 20),
    STAFF(1.0f, 25),
    WAND(1.0f, 25),
    KNUCKLE(1.0f, 20),
    GUN(1.0f, 15),
    CANNON(1.0f, 15),
    DUAL_BOW(1.0f, 15), //beyond op
    MAGIC_ARROW(1.0f, 15),
    CARTE(1.0f, 15),
    KATARA(1.0f, 20),
    BIG_SWORD(1.0f, 15),
    LONG_SWORD(1.0f, 15);
    private final float damageMultiplier;
    private final int baseMastery;

    private MapleWeaponType(final float maxDamageMultiplier, int baseMastery) {
        this.damageMultiplier = maxDamageMultiplier;
        this.baseMastery = baseMastery;
    }

    public final float getMaxDamageMultiplier() {
        return damageMultiplier;
    }

    public final int getBaseMastery() {
        return baseMastery;
    }
};
