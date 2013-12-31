package gameobjects.items;

import constants.DoubleConstants;
import constants.IntegerConstants;
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
        assertEquals(IntegerConstants.ACTOR_BASE_HEALTH.getValue()
                - ((int) (IntegerConstants.ACTOR_BASE_ATTACK.getValue() * DoubleConstants.WOODEN_SWORD_ATTACK.getValue())), b.getHealth());
    }
}
