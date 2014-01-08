package gameobjects.items;

import constants.Constants;
import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import logic.BattleAction;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class ItemTest {
    
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
    public void testDefaultOnAttack() {
        Item shield = new WoodenShield();
        assertEquals(Constants.ACTOR_BASE_ATTACK, shield.onAttack(Constants.ACTOR_BASE_ATTACK));
    }
    
    @Test
    public void testDefaultOnDamageReceived() {
        Item sword = new WoodenSword();
        assertEquals(Constants.ACTOR_BASE_ATTACK, sword.onDamageReceived(Constants.ACTOR_BASE_ATTACK));
    }
    
    @Test
    public void testAttackWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        Actor a = new Player(33, "TEST_PLAYER", null);
        a.addItem(new WoodenSword());
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SWORD_ATTACK)), b.getHealth());
    }
    
    @Test
    public void testTakeHitWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        b.addItem(new WoodenShield());
        Actor a = new Player(33, "TEST_PLAYER", null);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SHIELD_DEFENSE)), b.getHealth());
    }
    
    @Test
    public void testToString() {
        assertEquals("Woodensword", i.toString());
    }
    
    @Test
    public void testOnHealWhenDoesntAffect() {
        final int amount = 100;
        assertEquals(amount, i.onHeal(amount));
    }
    
    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testEqualsNullFalse() {
        assertFalse(i.equals(null));
    }
    
    @Test
    public void testEqualsPlayerFalse() {
        assertNotEquals(i, new Player(33333, "TEST_PLAYER", null));
    }
    
    @Test
    public void testEqualsWhenSameItems() {
        Item i2 = new WoodenSword();
        assertEquals(i, i2);
    }
}
