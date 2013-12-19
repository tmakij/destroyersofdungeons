package actors;

import dungeon.Tunnel;
import logic.DestroyersOfDungeons;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PlayerTest {

    private Player p;

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
        DestroyersOfDungeons game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER");
        game.addPlayer("TEST_PLAYER_NO2");
        p = game.getCurrentPlayer();
        p.takeHit(Actor.BASE_HEALTH + 1);
        p.die(game);
        assertEquals(false, game.getPlayers().contains(p));
    }
}
