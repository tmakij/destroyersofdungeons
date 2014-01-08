package gameobjects.items;

import constants.Constants;
import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import logic.BattleAction;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class WoodenSwordTest {

    @BeforeClass
    public static void onlyOnce() {
        Item.loadItemTypes();
    }

    private Item i;

    @Before
    public void setUp() {
        i = new WoodenSword();
    }

    @Test
    public void testOnAttack() {
        Actor a = new Player(0, "", null);
        a.addItem(i);
        Actor b = new Player(1, "", null);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SWORD_ATTACK)), b.getHealth());
    }
}
