package actors;

import dungeon.Tunnel;
import items.Item;
import items.WoodenShield;
import items.WoodenSword;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class ActorTest {

    private Actor a;

    @Before
    public void setUp() {
        a = new Player(0, "TEST_PLAYER");
    }

    private Item addItem() {
        Item i = new WoodenSword(0);
        a.addItem(i);
        return i;
    }

    @Test
    public void testGetAndGetMyBlock() {
        Tunnel t = new Tunnel(0);
        a.setMyBlock(t);
        assertEquals(t, a.getMyBlock());
    }

    @Test
    public void testAddItem() {
        Item i = addItem();
        assertEquals(a.getItems().get(0), i);
    }

    @Test
    public void testAttack() {
        Actor b = new Player(123, "TEST_PLAYER_NO2");
        a.attack(b);
        assertEquals(Actor.BASE_HEALTH - Actor.BASE_ATTACK, b.health);
    }

    @Test
    public void testAttackWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2");
        addItem();
        a.attack(b);
        assertEquals(Actor.BASE_HEALTH - ((int) (Actor.BASE_ATTACK * WoodenSword.ATTACK)), b.health);
    }

    @Test
    public void testTakeHitWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2");
        b.addItem(new WoodenShield(1));
        a.attack(b);
        assertEquals(Actor.BASE_HEALTH - ((int) (Actor.BASE_ATTACK * WoodenShield.DEFENCE)), b.health);
    }
}
