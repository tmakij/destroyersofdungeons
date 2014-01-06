package gameobjects.actors;

import constants.Constants;
import gameobjects.actors.monsters.Minotaur;
import gameobjects.actors.monsters.Monster;
import gameobjects.dungeon.Tunnel;
import gameobjects.items.Item;
import gameobjects.items.WoodenShield;
import gameobjects.items.WoodenSword;
import logic.BattleAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public final class ActorTest {

    private Actor a;
    private Tunnel t;

    @Before
    public void setUp() {
        a = new Minotaur(0);
    }

    @BeforeClass
    public static void onlyOnce() {
        Item.loadItemTypes();
        Monster.loadRaces();
    }

    private Item addItem() {
        Item i = new WoodenSword();
        a.addItem(i);
        return i;
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
    public void testAttackWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        addItem();
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SWORD_ATTACK)), b.getHealth());
    }

    @Test
    public void testTakeHitWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        b.addItem(new WoodenShield());
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.WOODEN_SHIELD_DEFENSE)), b.getHealth());
    }

    @Test
    public void testAlive() {
        assertEquals(true, a.isAlive());
    }

    @Test
    public void testDyeingAndNotAlive() {
        a.takeHit(Constants.ACTOR_BASE_HEALTH);
        assertEquals(false, a.isAlive());
    }

    @Test
    public void testDyeingAndDropping() {
        setTunnel();
        addItem();
        a.takeHit(Constants.ACTOR_BASE_HEALTH);
        a.die();
        assertEquals(1, t.getItems().size());
    }

    @Test
    public void testDyeingAndRemovalLeaveFromBlock() {
        setTunnel();
        a.takeHit(Constants.ACTOR_BASE_HEALTH);
        a.die();
        assertEquals(false, t.getActors().contains(a));
    }

    @Test
    public void testLivingDontDieStayOnBlock() {
        setTunnel();
        a.takeHit(Constants.ACTOR_BASE_HEALTH - 1);
        a.die();
        assertEquals(true, t.getActors().contains(a));
    }

    @Test
    public void testLivingIfTryingToDie() {
        assertEquals(false, a.die());
    }

    @Test
    public void testDyeingIfTryingToDie() {
        a.takeHit(Constants.ACTOR_BASE_HEALTH);
        setTunnel();
        assertEquals(true, a.die());
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
        Item i = new WoodenSword();
        t.addItem(i);
        a.pickUpItems();
        assertEquals(true, a.getItems().contains(i));
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
        assertEquals(false, a.getMyBlock().equals(t));
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
        assertEquals(true, t.getActors().isEmpty());
    }
}
