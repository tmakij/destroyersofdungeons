package gameobjects.actors;

import constants.Constants;
import gameobjects.actors.monsters.Monster;
import gameobjects.dungeon.Tunnel;
import gameobjects.items.Item;
import gameobjects.items.Treasure;
import java.util.Random;
import logic.BattleAction;
import logic.DestroyersOfDungeons;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class ActorTest {

    private static final Random rand = new Random();

    @BeforeClass
    public static void onlyOnce() {
        Item.loadItemTypes();
        Monster.loadRaces();
    }
    private Actor a;
    private Tunnel t;

    @Before
    public void setUp() {
        a = new Player(43, "TEST_PLAYER", new DestroyersOfDungeons());
    }

    private void setTunnel() {
        t = new Tunnel(0);
        a.setMyBlock(t);
    }

    @Test
    public void testGetMyBlockFirstNull() {
        assertEquals(null, a.getMyBlock());
    }

    @Test
    public void testGetMyBlock() {
        setTunnel();
        assertEquals(t, a.getMyBlock());
    }

    @Test
    public void testAttack() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH - Constants.ACTOR_BASE_ATTACK, b.getHealth());
    }

    @Test
    public void testAlive() {
        assertTrue(a.isAlive());
    }

    @Test
    public void testDyeingAndNotAlive() {
        a.takeHit(Constants.ACTOR_BASE_HEALTH);
        assertFalse(a.isAlive());
    }

    @Test
    public void testDyeingAndDropping() {
        setTunnel();
        a.addItem(new Treasure());
        a.takeHit(Constants.ACTOR_BASE_HEALTH);
        a.die();
        assertEquals(1, t.getItems().size());
    }

    @Test
    public void testDyeingAndRemovalLeaveFromBlock() {
        setTunnel();
        a.takeHit(Constants.ACTOR_BASE_HEALTH);
        a.die();
        assertFalse(t.getActors().contains(a));
    }

    @Test
    public void testLivingDontDieStayOnBlock() {
        setTunnel();
        a.takeHit(Constants.ACTOR_BASE_HEALTH - 1);
        a.die();
        assertTrue(t.getActors().contains(a));
    }

    @Test
    public void testLivingIfTryingToDie() {
        assertFalse(a.die());
    }

    @Test
    public void testDyeingIfTryingToDie() {
        a.takeHit(Constants.ACTOR_BASE_HEALTH);
        setTunnel();
        assertTrue(a.die());
    }

    @Test
    public void testPickItemsFromEmptyTunnel() {
        setTunnel();
        int itemsThen = a.getItems().size();
        a.pickUpItems();
        assertEquals(itemsThen, a.getItems().size());
    }

    @Test
    public void testPickItemsFromTunnelWithItems() {
        setTunnel();
        Item i = Item.getRandomItem(rand);
        t.addItem(i);
        a.pickUpItems();
        assertTrue(a.getItems().contains(i));
    }

    private Tunnel TunnelHistoryWorks(int addedToMax, int returnN) {
        Tunnel first = null;
        for (int i = 0; i < Constants.TUNNEL_HISTORY + addedToMax; i++) {
            t = new Tunnel((i + 1) * 12);
            if (i == returnN) {
                first = t;
            }
            a.setMyBlock(t);
        }
        a.retreat();
        return first;
    }

    @Test
    public void testTunnelHistoryAndRetreatWhenWithinLimit() {
        t = TunnelHistoryWorks(0, 0);
        assertEquals(t, a.getMyBlock());
    }

    @Test
    public void testTunnelHistoryAndRetreatWhenOverLimit() {
        t = TunnelHistoryWorks(1, 0);
        assertNotEquals(a.getMyBlock(), t);
    }

    @Test
    public void testTunnelHistoryAndRetreatReturnCorrectTunnel() {
        t = TunnelHistoryWorks(1, 1);
        assertEquals(t, a.getMyBlock());
    }

    @Test
    public void testTunnelHistoryAndRetreatHistoryClears() {
        t = TunnelHistoryWorks(0, 0);
        for (int i = 0; i < Constants.TUNNEL_HISTORY / 2; i++) {
            a.setMyBlock(new Tunnel(566 + i));
        }
        a.retreat();
        assertEquals(t, a.getMyBlock());
    }

    @Test
    public void testSetMyBlockClearsPreviousMemory() {
        setTunnel();
        Tunnel t2 = new Tunnel(555);
        a.setMyBlock(t2);
        assertTrue(t.getActors().isEmpty());
    }

    @Test
    public void testActorHeal() {
        final int damage = 25;
        a.takeHit(damage);
        a.heal();
        assertEquals(Constants.ACTOR_BASE_HEALTH - damage + Constants.ACTOR_HEAL_RATE, a.getHealth());
    }

    @Test
    public void testActorDontHealOverLimit() {
        for (int i = 0; i < 100; i++) {
            a.heal();
        }
        assertEquals(Constants.ACTOR_BASE_HEALTH, a.getHealth());
    }
}
