package gameobjects.items;

import constants.Constants;
import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import logic.BattleAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public final class WoodenShieldTest {

    private Item i;

    @BeforeClass
    public static void onlyOnce() {
        Item.loadItemTypes();
    }

    @Before
    public void setUp() {
        i = new WoodenShield();
    }

    @Test
    public void testOnDamageReceived() {
        Actor a = new Player(0, "", null);
        Actor b = new Player(1, "", null);
        b.addItem(i);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SHIELD_DEFENSE)), b.getHealth());
    }
}
