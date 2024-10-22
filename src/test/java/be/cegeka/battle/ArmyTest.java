package be.cegeka.battle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArmyTest {
    IHeadquarters headquarters = mock(IHeadquarters.class);

    @Test
    void givenArmy_whenEnrollingSoldier_thenSoldierIsEnrolled() {
        Army army = new Army(headquarters);
        Soldier soldier = new Soldier("John", headquarters);
        army.enrollSoldier(soldier);

        assertEquals(soldier, army.getFrontMan());
    }

    @Test
    void givenArmy_whenEnrollingMultipleSoldiers_thenMultipleSoldiersAreEnrolled() {
        Army army = new Army(headquarters);
        Soldier soldier = new Soldier("Jane", headquarters);
        Soldier soldier1 = new Soldier("John", headquarters);
        army.enrollSoldier(soldier);
        army.enrollSoldier(soldier1);

        assertEquals(soldier, army.getFrontMan());
        assertEquals(2, army.getArmy().size());
    }

    @Test
    void givenArmy_whenInWar_thenDefeatedArmyHasNoSoldiers() {
        Army army = new Army(headquarters);
        Soldier soldier = new Soldier("Jane", headquarters);
        Soldier soldier1 = new Soldier("John", headquarters);
        army.enrollSoldier(soldier);
        army.enrollSoldier(soldier1);

        Army opponent = new Army(headquarters);
        Soldier soldier2 = new Soldier("Jack", Weapen.AXE, headquarters);
        Soldier soldier3 = new Soldier("Jill", headquarters);
        opponent.enrollSoldier(soldier2);
        opponent.enrollSoldier(soldier3);

        army.engageInWar(opponent);

        assertTrue(army.isDefeated());
        assertFalse(opponent.isDefeated());
    }

    @Test
    void givenArmy_whenInWarWithUnequalOpponent_thenDefeatedArmyHasNoSoldiers() {
        Army army = new Army(headquarters);
        Soldier soldier = new Soldier("Jane", headquarters);
        Soldier soldier1 = new Soldier("John", headquarters);
        army.enrollSoldier(soldier);
        army.enrollSoldier(soldier1);

        Army opponent = new Army(headquarters);
        Soldier soldier2 = new Soldier("Jack", Weapen.AXE, headquarters);
        Soldier soldier3 = new Soldier("Jill", headquarters);
        Soldier soldier4 = new Soldier("Joe", headquarters);
        Soldier soldier5 = new Soldier("Jeff", headquarters);
        opponent.enrollSoldier(soldier2);
        opponent.enrollSoldier(soldier3);
        opponent.enrollSoldier(soldier4);
        opponent.enrollSoldier(soldier5);

        army.engageInWar(opponent);

        assertTrue(army.isDefeated());
        assertFalse(opponent.isDefeated());
    }

    @Test
    void givenArmy_whenSoldierEnrolled_thenReportEnlistmentIsCalled() {
        Army army = new Army(headquarters);
        Soldier soldier = new Soldier("Jane", headquarters);
        army.enrollSoldier(soldier);
        verify(headquarters).reportEnlistment(soldier.getName());
    }

    @Test
    void givenArmy_whenSoldierEnrolled_thenIntegerIsReturned() {
        Army army = new Army(headquarters);
        Soldier soldier = new Soldier("Jane", headquarters);
        army.enrollSoldier(soldier);
        when(headquarters.reportEnlistment(soldier.getName())).thenReturn(1);
        verify(headquarters).reportEnlistment(soldier.getName());
        assertEquals(1, headquarters.reportEnlistment(soldier.getName()));
    }

    @Test
    void fight_whenSoldierDies_thenReportCausality() {
        Soldier attackerSoldier = new Soldier("Attacker", Weapen.AXE, headquarters);
        Soldier defenderSoldier = new Soldier("Defender", Weapen.SWORD, headquarters);
        Army attacker = new Army(headquarters);
        Army defender = new Army(headquarters);
        attacker.enrollSoldier(attackerSoldier);
        defender.enrollSoldier(defenderSoldier);

        attacker.engageInWar(defender);

        verify(headquarters).reportCasualty(defenderSoldier.getId());
    }

    @Test
    void fight_whenArmyWins_thenNumberOfRemainingReturned() {
        Soldier attackerSoldier = new Soldier("Attacker", Weapen.BAREFIST, headquarters);
        Soldier attackerSoldier1 = new Soldier("Attacker", Weapen.BAREFIST, headquarters);
        Soldier attackerSoldier2 = new Soldier("Attacker", Weapen.BAREFIST, headquarters);

        Soldier defenderSoldier = new Soldier("Defender", Weapen.AXE, headquarters);
        Army attacker = new Army(headquarters);
        Army defender = new Army(headquarters);
        attacker.enrollSoldier(attackerSoldier);
        attacker.enrollSoldier(attackerSoldier1);
        attacker.enrollSoldier(attackerSoldier2);
        defender.enrollSoldier(defenderSoldier);

        attacker.engageInWar(defender);

        verify(headquarters).reportVictory(1);
    }

}