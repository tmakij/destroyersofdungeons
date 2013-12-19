package logic;

import gameobjects.dungeon.Tunnel;
import gameobjects.actors.Player;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public final class DestroyersOfDungeonsTest {

    private DestroyersOfDungeons game;

    @Before
    public void setUp() {
        game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER");
    }

    private String addAnotherPlayer() {
        return game.addPlayer("TEST_PLAYER_NO2");
    }

    @Test
    public void testStartblockcount() {
        addAnotherPlayer();
        assertEquals(2, game.getCurrentPlayer().getMyBlock().getActorSet().size());
    }

    @Test
    public void testCurrentPlayer() {
        addAnotherPlayer();
        assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }

    @Test
    public void testNextPlayer() {
        addAnotherPlayer();
        game.nextPlayer();
        assertEquals(game.getPlayers().get(1), game.getCurrentPlayer());
    }

    @Test
    public void testNextIfOnlyOnePlayer() {
        Player p = game.getCurrentPlayer();
        game.nextPlayer();
        assertEquals(p, game.getCurrentPlayer());
    }

    @Test
    public void testNextIfTooManyPlayers() {
        addAnotherPlayer();
        addAnotherPlayer();
        try {
            game.nextPlayer();
            fail("You can add too many players.");
        } catch (UnsupportedOperationException ex) {
            assertEquals(ex.getClass(), UnsupportedOperationException.class);
        }
    }

    @Test
    public void test2NextPlayer() {
        addAnotherPlayer();
        game.nextPlayer();
        game.nextPlayer();
        assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }

    @Test
    public void testAddPlayer() {
        addAnotherPlayer();
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void testAddPlayerString() {
        game.getPlayers().clear();
        assertEquals("Added player TEST_PLAYER_NO2 (Starting player)", addAnotherPlayer());
    }

    @Test
    public void testAdd2PlayerString() {
        assertEquals("Added player TEST_PLAYER_NO2", addAnotherPlayer());
    }

    @Test
    public void testPlay() {
        addAnotherPlayer();
        String expected;
        Tunnel firstBlock = game.getMap().getFirstBlock();
        List<Tunnel> nextTo = firstBlock.getNextTo();
        assertEquals(nextTo, game.play());
    }

    @Test
    public void testMovePlayerTo() {
        Player p = game.getPlayers().get(0);
        Tunnel original = p.getMyBlock();
        Tunnel movingTo = original.getNextTo().get(0);
        game.movePlayerTo(0);
        Tunnel newBlock = p.getMyBlock();
        assertEquals(movingTo, newBlock);
    }

    @Test
    public void testMovePlayerToString() {
        Player p = game.getCurrentPlayer();
        Tunnel t = p.getMyBlock();
        Tunnel movingTo = t.getNextTo().get(0);
        assertEquals(movingTo, game.movePlayerTo(0));
    }

    @Test
    public void testMovePlayerToInvalidBlock() {
        Player p = game.getCurrentPlayer();
        Tunnel original = p.getMyBlock();
        game.movePlayerTo(23434);
        Tunnel newBlock = p.getMyBlock();
        assertEquals(original, newBlock);
    }

    @Test
    public void testMovePlayerToInvalidBlockString() {
        Player p = game.getCurrentPlayer();
        assertEquals(null, game.movePlayerTo(23434));
    }

    @Test
    public void testDeletePlayer() {
        addAnotherPlayer();
        Player p = game.getCurrentPlayer();
        game.nextPlayer();
        game.removePlayer(p);
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    public void testDeleteCurrentPlayer() {
        addAnotherPlayer();
        Player p = game.getCurrentPlayer();
        game.nextPlayer();
        game.removePlayer(game.getCurrentPlayer());
        assertEquals(p, game.getCurrentPlayer());
    }
}
