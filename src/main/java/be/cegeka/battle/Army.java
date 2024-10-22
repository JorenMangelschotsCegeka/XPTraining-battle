package be.cegeka.battle;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private List<Soldier> army;
    private IHeadquarters iHeadquarters;

    public Army(IHeadquarters iHeadquarters) {
        this.army = new ArrayList<>();
        this.iHeadquarters = iHeadquarters;
    }

    public Army(List<Soldier> army, IHeadquarters iHeadquarters) {
        this.army = new ArrayList<>(army);
        this.iHeadquarters = iHeadquarters;
    }

    public void enrollSoldier(Soldier soldier) {
        iHeadquarters.reportEnlistment(soldier.getName());
        army.add(soldier);
    }

    public Soldier getFrontMan() {
        if (army.isEmpty()) {
            throw new IllegalStateException("The army has no soldiers");
        }
        return army.get(0);
    }

    public List<Soldier> getArmy() {
        return army;
    }

    public void removeFrontMan() {
        if (!army.isEmpty()) {
            army.removeFirst();
        }
    }

    public boolean isDefeated() {
        return army.isEmpty();
    }

    public void engageInWar(Army opponent) {
        while (!this.isDefeated() && !opponent.isDefeated()) {
            Soldier thisFrontMan = this.getFrontMan();
            Soldier opponentFrontMan = opponent.getFrontMan();

            if (thisFrontMan.fight(opponentFrontMan)) {
                opponent.removeFrontMan();
            } else {
                this.removeFrontMan();
            }
        }
        if (this.isDefeated()) {
            iHeadquarters.reportVictory(opponent.getArmy().size());
        } else {
            iHeadquarters.reportVictory(this.getArmy().size());
        }
    }
}