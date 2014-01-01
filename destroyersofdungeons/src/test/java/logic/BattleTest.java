package logic;

import GUI.testGUIPanel;
import constants.Constants;
import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import gameobjects.dungeon.Tunnel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class BattleTest {

    private Battle bt;
    private Actor att;
    private Actor def;
    private Actor current;
    private testGUIPanel gui;

    @Before
    public void setUp() {
        att = new Player(0, "TEST_PLAYER", null);
        def = new Player(1, "TEST_PLAYER_NO2", null);
        gui = new testGUIPanel();
        bt = new Battle(att, def, gui);
        current = bt.getCurrent();
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
    public void testTakeActionTurn() {
        bt.takeAction(BattleAction.DO_NOTHING);
        assertEquals(false, current.equals(bt.getCurrent()));
    }

    @Test
    public void testNextTurnTwice() {
        bt.takeAction(BattleAction.DO_NOTHING);
        bt.takeAction(BattleAction.DO_NOTHING);
        assertEquals(current, bt.getCurrent());
    }

    @Test
    public void testTakeAction() {
        bt.takeAction(BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH - Constants.ACTOR_BASE_ATTACK, bt.getCurrent().getHealth());
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
        def.takeHit(Constants.ACTOR_BASE_HEALTH - 1);
        assertEquals(true, bt.takeAction(BattleAction.ATTACK));
    }

    @Test
    public void testAttLosersDie() {
        testDying();
        att.takeHit(Constants.ACTOR_BASE_HEALTH - 1);
        bt.takeAction(BattleAction.DO_NOTHING);
        assertEquals(true, bt.takeAction(BattleAction.ATTACK));
    }

    private void emptyAction(BattleAction act, Actor a) {
        bt.takeAction(act);
        assertEquals(Constants.ACTOR_BASE_HEALTH, a.getHealth());
    }

    @Test
    public void testTakeActionNothingAttackerLoseNothing() {
        emptyAction(BattleAction.DO_NOTHING, att);
    }

    @Test
    public void testTakeActionNothingDefenderLoseNothing() {
        emptyAction(BattleAction.DO_NOTHING, def);
    }

    @Test
    public void testTakeActionDefendeAttackerLoseNothing() {
        emptyAction(BattleAction.DEFEND, att);
    }

    @Test
    public void testTakeActionDefendeDefenderLoseNothing() {
        emptyAction(BattleAction.DEFEND, def);
    }

    @Test
    public void testTakeActionCastSpellAttackerLoses() {
        bt.takeAction(BattleAction.CAST_SPELL);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.BATTLEACTION_CASTSPELL)),
                bt.getCurrent().getHealth());
    }

    @Test
    public void testTakeActionCastSpellDefenderDoesntLose() {
        bt.takeAction(BattleAction.CAST_SPELL);
        assertEquals(Constants.ACTOR_BASE_HEALTH, current.getHealth());
    }

    @Test
    public void testTakeActionDefendeAttackerLoseMoreOnAttack() {
        bt.takeAction(BattleAction.DEFEND);
        current = bt.getCurrent();
        bt.takeAction(BattleAction.ATTACK);
        assertEquals(Constants.ACTOR_BASE_HEALTH
                - ((int) (Constants.ACTOR_BASE_ATTACK * Constants.BATTLEACTION_DEFEND)), current.getHealth());
    }

    @Test
    public void testTakeActionFlee() {
        current.setMyBlock(new Tunnel(34432));
        assertEquals(true, bt.takeAction(BattleAction.FLEE));
    }

    @Test
    public void testTakeActionFleeRetreats() {
        Tunnel original = new Tunnel(34432);
        current.setMyBlock(original);
        current.setMyBlock(new Tunnel(3223));
        bt.takeAction(BattleAction.FLEE);
        assertEquals(original, current.getMyBlock());
    }

    @Test
    public void testTestGUINotUpdatedFirst() {
        assertEquals(false, gui.isUpdated());
    }

    @Test
    public void testGUIGetsUpdated() {
        bt.takeAction(BattleAction.DO_NOTHING);
        assertEquals(true, gui.isUpdated());
    }
}
