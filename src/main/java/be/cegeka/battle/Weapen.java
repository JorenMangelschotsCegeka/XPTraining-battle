package be.cegeka.battle;

public enum Weapen {
    AXE(3),
    SWORD(2),
    SPEAR(2),
    BAREFIST(1);

    private final int damage;

    Weapen(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
