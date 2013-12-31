package gameobjects.actors;

import constants.DoubleConstants;
import constants.IntegerConstants;
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
        Monster.loadRaces();
    }

    private Item addItem() {
        Item i = new WoodenSword(0);
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
        assertEquals(IntegerConstants.ACTOR_BASE_HEALTH.getValue() - IntegerConstants.ACTOR_BASE_ATTACK.getValue(), b.getHealth());
    }

    @Test
    public void testAttackWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        addItem();
        a.attack(b, BattleAction.ATTACK);
        assertEquals(IntegerConstants.ACTOR_BASE_HEALTH.getValue()
                - ((int) (IntegerConstants.ACTOR_BASE_ATTACK.getValue() * DoubleConstants.WOODEN_SWORD_ATTACK.getValue())), b.getHealth());
    }

    @Test
    public void testTakeHitWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        b.addItem(new WoodenShield(1));
        a.attack(b, BattleAction.ATTACK);
        assertEquals(IntegerConstants.ACTOR_BASE_HEALTH.getValue()
                - ((int) (IntegerConstants.ACTOR_BASE_ATTACK.getValue() * DoubleConstants.WOODEN_SHIELD_DEFENSE.getValue())), b.getHealth());
    }

    @Test
    public void testAlive() {
        assertEquals(true, a.isAlive());
    }

    @Test
    public void testDyeingAndNotAlive() {
        a.takeHit(IntegerConstants.ACTOR_BASE_HEALTH.getValue());
        assertEquals(false, a.isAlive());
    }

    @Test
    public void testDyeingAndDropping() {
        setTunnel();
        addItem();
        a.takeHit(IntegerConstants.ACTOR_BASE_HEALTH.getValue());
        a.die();
        assertEquals(1, t.getItems().size());
    }

    @Test
    public void testDyeingAndRemovalLeaveFromBlock() {
        setTunnel();
        a.takeHit(IntegerConstants.ACTOR_BASE_HEALTH.getValue());
        a.die();
        assertEquals(false, t.getActorSet().contains(a));
    }

    @Test
    public void testLivingDontDieStayOnBlock() {
        setTunnel();
        a.takeHit(IntegerConstants.ACTOR_BASE_HEALTH.getValue() - 1);
        a.die();
        assertEquals(true, t.getActorSet().contains(a));
    }

    @Test
    public void testLivingIfTryingToDie() {
        assertEquals(false, a.die());
    }

    @Test
    public void testDyeingIfTryingToDie() {
        a.takeHit(IntegerConstants.ACTOR_BASE_HEALTH.getValue());
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
        Item i = new WoodenSword(1920);
        t.addItem(i);
        a.pickUpItems();
        assertEquals(true, a.getItems().contains(i));
    }

    private Tunnel TunnelHistoryWorks(int addedToMax, int returnN) {
        Tunnel first = null;
        for (int i = 0; i < IntegerConstants.TUNNEL_HISTORY.getValue() + addedToMax; i++) {
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
        Tunnel t = TunnelHistoryWorks(0, 0);
        assertEquals(t, a.getMyBlock());
    }

    @Test
    public void testTunnelHistoryAndRetreatWhenOverLimit() {
        Tunnel t = TunnelHistoryWorks(1, 0);
        assertEquals(false, a.getMyBlock().equals(t));
    }

    @Test
    public void testTunnelHistoryAndRetreatReturnCorrectTunnel() {
        Tunnel t = TunnelHistoryWorks(1, 1);
        assertEquals(t, a.getMyBlock());
    }
}
