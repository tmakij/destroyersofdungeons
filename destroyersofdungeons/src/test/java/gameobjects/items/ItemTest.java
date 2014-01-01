package gameobjects.items;

import constants.Constants;
import org.junit.Test;
import static org.junit.Assert.*;

public final class ItemTest {

    @Test
    public void testDefaultOnAttack() {
        Item shield = new WoodenShield(0);
        assertEquals(Constants.ACTOR_BASE_ATTACK, shield.onAttack(Constants.ACTOR_BASE_ATTACK));
    }

    @Test
    public void testDefaultOnDamageReceived() {
        Item sword = new WoodenSword(0);
        assertEquals(Constants.ACTOR_BASE_ATTACK, sword.onDamageReceived(Constants.ACTOR_BASE_ATTACK));
    }
}
