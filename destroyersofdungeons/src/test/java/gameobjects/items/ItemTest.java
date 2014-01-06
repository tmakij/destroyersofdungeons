package gameobjects.items;

import constants.Constants;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public final class ItemTest {

    @BeforeClass
    public static void onlyOnce() {
        Item.loadItemTypes();
    }

    @Test
    public void testDefaultOnAttack() {
        Item shield = new WoodenShield();
        assertEquals(Constants.ACTOR_BASE_ATTACK, shield.onAttack(Constants.ACTOR_BASE_ATTACK));
    }

    @Test
    public void testDefaultOnDamageReceived() {
        Item sword = new WoodenSword();
        assertEquals(Constants.ACTOR_BASE_ATTACK, sword.onDamageReceived(Constants.ACTOR_BASE_ATTACK));
    }
}
