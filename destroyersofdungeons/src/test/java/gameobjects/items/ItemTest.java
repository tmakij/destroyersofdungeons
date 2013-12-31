package gameobjects.items;

import constants.IntegerConstants;
import org.junit.Test;
import static org.junit.Assert.*;

public final class ItemTest {

    @Test
    public void testDefaultOnAttack() {
        Item shield = new WoodenShield(0);
        assertEquals(IntegerConstants.ACTOR_BASE_ATTACK.getValue(), shield.onAttack(IntegerConstants.ACTOR_BASE_ATTACK.getValue()));
    }

    @Test
    public void testDefaultOnDamageReceived() {
        Item sword = new WoodenSword(0);
        assertEquals(IntegerConstants.ACTOR_BASE_ATTACK.getValue(), sword.onDamageReceived(IntegerConstants.ACTOR_BASE_ATTACK.getValue()));
    }
}
