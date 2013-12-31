package gameobjects.actors;

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

    @Test
    public void testGetAndGetMyBlock() {
        Tunnel t = new Tunnel(0);
        a.setMyBlock(t);
        assertEquals(t, a.getMyBlock());
    }

    @Test
    public void testAttack() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Actor.BASE_HEALTH - Actor.BASE_ATTACK, b.getHealth());
    }

    @Test
    public void testAttackWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        addItem();
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Actor.BASE_HEALTH - ((int) (Actor.BASE_ATTACK * WoodenSword.ATTACK)), b.getHealth());
    }

    @Test
    public void testTakeHitWithItem() {
        Actor b = new Player(123, "TEST_PLAYER_NO2", null);
        b.addItem(new WoodenShield(1));
        a.attack(b, BattleAction.ATTACK);
        assertEquals(Actor.BASE_HEALTH - ((int) (Actor.BASE_ATTACK * WoodenShield.DEFENCE)), b.getHealth());
    }

    @Test
    public void testLastBlockNull() {
        Tunnel t = new Tunnel(0);
        Tunnel t2 = new Tunnel(1);
        a.setMyBlock(t);
        assertEquals(null, a.getMyLastBlock());
    }

    @Test
    public void testLastBlockWorking() {
        Tunnel t = new Tunnel(0);
        Tunnel t2 = new Tunnel(1);
        a.setMyBlock(t);
        a.setMyBlock(t2);
        assertEquals(t, a.getMyLastBlock());
    }

    @Test
    public void testDyeing() {
        a.takeHit(Actor.BASE_HEALTH);
        assertEquals(false, a.isAlive());
    }

    @Test
    public void testDyeingAndDropping() {
        Tunnel t = new Tunnel(0);
        a.setMyBlock(t);
        addItem();
        a.takeHit(Actor.BASE_HEALTH);
        a.die();
        assertEquals(1, t.getItems().size());
    }

    @Test
    public void testDyeingAndRemoval() {
        Tunnel t = new Tunnel(0);
        a.setMyBlock(t);
        addItem();
        a.takeHit(Actor.BASE_HEALTH);
        a.die();
        assertEquals(false, t.getActorSet().contains(a));
    }

    @Test
    public void testLivingDontDie() {
        Tunnel t = new Tunnel(0);
        a.setMyBlock(t);
        addItem();
        a.takeHit(Actor.BASE_HEALTH - 1);
        a.die();
        assertEquals(true, t.getActorSet().contains(a));
    }
}
