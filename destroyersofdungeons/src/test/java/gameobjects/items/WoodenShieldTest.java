package gameobjects.items;

import constants.Constants;
import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import logic.BattleAction;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public final class WoodenShieldTest extends ItemTestBase {

    @Before
    public void setUp() {
        i = new WoodenShield();
        a = new Player(0, "", null);
    }

    @Test
    public void testOnDamageReceived() {
        Actor b = new Player(1, "", null);
        b.addItem(i);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SHIELD_DEFENSE)), b.getHealth());
    }
}
