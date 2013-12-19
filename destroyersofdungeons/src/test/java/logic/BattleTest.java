package logic;

import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class BattleTest {

    private Battle bt;
    private Actor att;
    private Actor def;

    @Before
    public void setUp() {
        att = new Player(0, "TEST_PLAYER");
        def = new Player(1, "TEST_PLAYER_NO2");
        bt = new Battle(att, def);
    }

    @Test
    public void testAttacker() {
        assertEquals(att, bt.getAttacker());
    }

    @Test
    public void testDefender() {
        assertEquals(def, bt.getDefender());
    }

    @Test
    public void testNextDefault() {
        assertEquals(false, bt.getCurrent().equals(bt.getNextActor()));
    }

    @Test
    public void testNextTurn() {
        Actor a = bt.getCurrent();
        bt.nextTurn();
        assertEquals(a, bt.getNextActor());
    }

    @Test
    public void testNextTurnTwice() {
        Actor a = bt.getCurrent();
        bt.nextTurn();
        bt.nextTurn();
        assertEquals(false, a.equals(bt.getNextActor()));
    }

    @Test
    public void testDefaultResume() {
        assertEquals(true, bt.resume());
    }

    @Test
    public void testTakeAction() {
        bt.takeAction(0);
        assertEquals(Actor.BASE_HEALTH - Actor.BASE_ATTACK, bt.getNextActor().getHealth());
    }

    @Test
    public void testTakeActionResultDeath() {
        bt.getNextActor().takeHit(Actor.BASE_HEALTH - 1);
        bt.takeAction(0);
        assertEquals(false, bt.resume());
    }
}
