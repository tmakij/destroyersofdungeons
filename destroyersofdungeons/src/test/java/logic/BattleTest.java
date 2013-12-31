package logic;

import GUI.testGUIPanel;
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
        att = new Player(0, "TEST_PLAYER", null);
        def = new Player(1, "TEST_PLAYER_NO2", null);
        bt = new Battle(att, def, new testGUIPanel());
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
    public void testTakeAction() {
        bt.takeAction(BattleAction.ATTACK);
        assertEquals(Actor.BASE_HEALTH - Actor.BASE_ATTACK, bt.getCurrent().getHealth());
    }

    private void testDying() {
        DestroyersOfDungeons game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER");
        game.addPlayer("TEST_PLAYER_NO2");
        def = game.getCurrentPlayer();
        game.nextPlayer();
        att = game.getCurrentPlayer();
        bt = new Battle(att, def, new testGUIPanel());
    }

    @Test
    public void testDefLosersDie() {
        testDying();
        def.takeHit(Actor.BASE_HEALTH - 1);
        assertEquals(true, bt.takeAction(BattleAction.ATTACK));
    }

    @Test
    public void testAttLosersDie() {
        testDying();
        att.takeHit(Actor.BASE_HEALTH - 1);
        bt.nextTurn();
        assertEquals(true, bt.takeAction(BattleAction.ATTACK));
    }
}
