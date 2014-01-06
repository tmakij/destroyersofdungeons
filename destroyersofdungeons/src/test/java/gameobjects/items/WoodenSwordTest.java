package gameobjects.items;

import constants.Constants;
import gameobjects.actors.Actor;
import gameobjects.actors.monsters.Minotaur;
import logic.BattleAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public final class WoodenSwordTest {

    private Item i;

    @BeforeClass
    public static void onlyOnce() {
        Item.loadItemTypes();
    }

    @Before
    public void setUp() {
        i = new WoodenSword();
    }

    @Test
    public void testOnAttack() {
        Actor a = new Minotaur(0);
        a.addItem(i);
        Actor b = new Minotaur(1);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SWORD_ATTACK)), b.getHealth());
    }
}
