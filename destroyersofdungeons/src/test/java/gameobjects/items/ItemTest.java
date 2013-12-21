package gameobjects.items;

import gameobjects.actors.Actor;
import org.junit.Test;
import static org.junit.Assert.*;

public final class ItemTest {

    @Test
    public void testDefaultOnAttack() {
        Item shield = new WoodenShield(0);
        assertEquals(Actor.BASE_ATTACK, shield.onAttack(Actor.BASE_ATTACK));
    }

    @Test
    public void testDefaultOnDamageReceived() {
        Item sword = new WoodenSword(0);
        assertEquals(Actor.BASE_ATTACK, sword.onDamageReceived(Actor.BASE_ATTACK));
    }
}
