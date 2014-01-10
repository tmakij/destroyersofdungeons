package gameobjects.items;

import constants.Constants;
import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import logic.BattleAction;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public final class WoodenSwordTest extends ItemTestBase {

    @Before
    public void setUp() {
        i = new WoodenSword();
        a = new Player(0, "", null);
    }

    @Test
    public void testOnAttack() {
        a.addItem(i);
        Actor b = new Player(1, "", null);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SWORD_ATTACK)), b.getHealth());
    }
}
