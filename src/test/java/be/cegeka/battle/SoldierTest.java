package be.cegeka.battle;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SoldierTest {
    IHeadquarters headquarters = mock(IHeadquarters.class);

    @Test
    void construction_aSoldierMustHaveAName() {
        Soldier soldier = new Soldier("name", headquarters);

        assertThat(soldier.getName()).isEqualTo("name");
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeNull() {
        assertThatThrownBy(() -> new Soldier(null, headquarters))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeEmpty() {
        assertThatThrownBy(() -> new Soldier("", headquarters))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeBlank() {
        assertThatThrownBy(() -> new Soldier("     ", headquarters))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAWeapen_cannotBeNull() {
        Soldier soldier = new Soldier("name", headquarters);
        assertThat(soldier.getWeapen()).isEqualTo(Weapen.BAREFIST);
    }

    @Test
    void fight_attackerWinsIfWeaponsAreEqual() {
        Soldier attacker = new Soldier("Attacker", Weapen.SWORD, headquarters);
        Soldier defender = new Soldier("Defender", Weapen.SWORD, headquarters);

        assertThat(attacker.fight(defender)).isTrue();
    }

    @Test
    void fight_strongerWeaponWins() {
        Soldier attacker = new Soldier("Attacker", Weapen.AXE, headquarters);
        Soldier defender = new Soldier("Defender", Weapen.SWORD, headquarters);

        assertThat(attacker.fight(defender)).isTrue();
    }

    @Test
    void fight_weakerWeaponLoses() {
        Soldier attacker = new Soldier("Attacker", Weapen.SWORD, headquarters);
        Soldier defender = new Soldier("Defender", Weapen.AXE, headquarters);

        assertThat(attacker.fight(defender)).isFalse();
    }

    @Test
    void construction_aSoldierHasNoId_thenIdIsNull() {
        Soldier soldier = new Soldier("name", headquarters);
        assertEquals(0, soldier.getId());
    }

    @Test
    void fight_whenSoldierDies_thenReportCausality() {
        Soldier attacker = new Soldier("Attacker", Weapen.AXE, headquarters);
        Soldier defender = new Soldier("Defender", Weapen.SWORD, headquarters);
        attacker.setId(1);
        defender.setId(2);
        attacker.fight(defender);
        verify(headquarters).reportCasualty(defender.getId());
    }

}