package gameobjects.actors;

import logic.DestroyersOfDungeons;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PlayerTest {

    private Player p;

    private DestroyersOfDungeons killPlayer() {
        DestroyersOfDungeons game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER");
        game.addPlayer("TEST_PLAYER_NO2");
        p = game.getCurrentPlayer();
        p.takeHit(Actor.BASE_HEALTH + 1);
        p.die(game);
        return game;
    }

    private DestroyersOfDungeons donDie() {
        DestroyersOfDungeons game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER");
        p = game.getCurrentPlayer();
        p.die(game);
        return game;
    }

    @Before
    public void setUp() {
        p = new Player(0, "TEST_PLAYER");
    }

    @Test
    public void testisPlayerControlled() {
        assertEquals(true, p.isPlayerControlled());
    }

    @Test
    public void testDyeingAndRemovalFromGame() {
        DestroyersOfDungeons game = killPlayer();
        assertEquals(false, game.getPlayers().contains(p));
    }

    @Test
    public void testLivingKeepPlayingGame() {
        DestroyersOfDungeons game = donDie();
        assertEquals(true, game.getPlayers().contains(p));
    }

    @Test
    public void testLivingStayInYourBlock() {
        donDie();
        assertEquals(true, p.getMyBlock().getActorSet().contains(p));
    }

    @Test
    public void testDyeingAndRemovalFromMyBlock() {
        killPlayer();
        assertEquals(false, p.getMyBlock().getActorSet().contains(p));
    }
}
