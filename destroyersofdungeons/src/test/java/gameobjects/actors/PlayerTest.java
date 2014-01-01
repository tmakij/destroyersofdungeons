package gameobjects.actors;

import constants.Constants;
import logic.DestroyersOfDungeons;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PlayerTest {

    private Player p, att, def;
    private DestroyersOfDungeons game;

    private void killPlayer() {
        game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER");
        game.addPlayer("TEST_PLAYER_NO2");
        p = game.getCurrentPlayer();
        p.takeHit(Constants.ACTOR_BASE_HEALTH);
        p.die();
    }

    private void dontDie() {
        game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER");
        p = game.getCurrentPlayer();
        p.die();
    }

    @Before
    public void setUp() {
        p = new Player(0, "TEST_PLAYER", null);
    }

    @Test
    public void testisPlayerControlled() {
        assertEquals(true, p.isPlayerControlled());
    }

    @Test
    public void testDyeingAndRemovalFromGame() {
        killPlayer();
        assertEquals(false, game.getPlayers().contains(p));
    }

    @Test
    public void testLivingKeepPlayingGame() {
        dontDie();
        assertEquals(true, game.getPlayers().contains(p));
    }

    @Test
    public void testLivingStayInYourBlock() {
        dontDie();
        assertEquals(true, p.getMyBlock().getActorSet().contains(p));
    }

    @Test
    public void testDyeingAndRemovalFromMyBlock() {
        killPlayer();
        assertEquals(false, p.getMyBlock().getActorSet().contains(p));
    }

    private void setUpBattle() {
        game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER");
        game.addPlayer("TEST_PLAYER_NO2");
        att = game.getPlayers().get(0);
        def = game.getPlayers().get(1);
    }

    @Test
    public void testDefLosersDie() {
        setUpBattle();
        def.takeHit(Constants.ACTOR_BASE_HEALTH);
        def.die();
        assertEquals(false, game.getPlayers().contains(def));
    }

    @Test
    public void testAttLosersDie() {
        setUpBattle();
        att.takeHit(Constants.ACTOR_BASE_HEALTH);
        att.die();
        assertEquals(false, game.getPlayers().contains(att));
    }
}
