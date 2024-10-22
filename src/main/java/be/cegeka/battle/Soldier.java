package be.cegeka.battle;


import com.google.common.base.Strings;

public class Soldier {

    private final String name;
    private Weapen weapen = Weapen.BAREFIST;
    private int id;
    private IHeadquarters headquarters;

    public Soldier(String name, IHeadquarters headquarters) {
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(name.trim())) {
            throw new IllegalArgumentException("A soldier must have a name");
        }
        this.headquarters = headquarters;
        this.name = name;
    }

    public Soldier(String name, Weapen weapen, IHeadquarters headquarters) {
        this(name, headquarters);
        this.weapen = weapen;
    }

    public Weapen getWeapen() {
        return weapen;
    }

    String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean fight(Soldier opponent) {
        if (this.weapen.getDamage() > opponent.getWeapen().getDamage()) {
            headquarters.reportCasualty(opponent.id);
            return true;
        } else if (this.weapen.getDamage() < opponent.getWeapen().getDamage()) {
            headquarters.reportCasualty(this.id);
            return false;
        } else {
            return true;
        }
    }
}
