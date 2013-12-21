package gameobjects.items;

import gameobjects.actors.Actor;
import gameobjects.actors.monsters.Minotaur;
import logic.BattleAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class WoodenShieldTest {

    private Item i;

    @Before
    public void setUp() {
        i = new WoodenShield(0);
    }

    @Test
    public void testOnDamageReceived() {
        Actor a = new Minotaur(0);
        Actor b = new Minotaur(1);
        b.addItem(i);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Actor.BASE_HEALTH - ((int) (Actor.BASE_ATTACK * WoodenShield.DEFENCE)), b.getHealth());
    }
}
