package gameobjects.items;

import gameobjects.actors.Actor;
import gameobjects.actors.monsters.Minotaur;
import logic.BattleAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class WoodenSwordTest {

    private Item i;

    @Before
    public void setUp() {
        i = new WoodenSword(0);
    }

    @Test
    public void testOnAttack() {
        Actor a = new Minotaur(0);
        a.addItem(i);
        Actor b = new Minotaur(1);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Actor.BASE_HEALTH - ((int) (Actor.BASE_ATTACK * WoodenSword.ATTACK)), b.getHealth());
    }
}
