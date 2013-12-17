package logic;

import dungeon.Tunnel;
import actors.Player;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DestroyersOfDungeonsTest {

    private final DestroyersOfDungeons game = new DestroyersOfDungeons();

    private void addPlayers() {
        game.addPlayer("Test1");
        game.addPlayer("Test2");
    }

    @Test
    public void testCurrentPlayer() {
        addPlayers();
        assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }

    @Test
    public void testNextPlayer() {
        addPlayers();
        game.nextPlayer();
        assertEquals(game.getPlayers().get(1), game.getCurrentPlayer());
    }

    @Test
    public void test2NextPlayer() {
        addPlayers();
        game.nextPlayer();
        game.nextPlayer();
        assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }

    @Test
    public void testAddPlayer() {
        addPlayers();
        assertEquals(true, 2 == game.getPlayers().size());
    }

    @Test
    public void testAddPlayerString() {
        assertEquals("Added player HELLO_PLAYER (Starting player)", game.addPlayer("HELLO_PLAYER"));
    }

    @Test
    public void testAdd2PlayersString() {
        game.addPlayer("HELLO_PLAYER");
        assertEquals("Added player DUMMY_PLAYER", game.addPlayer("DUMMY_PLAYER"));
    }

    @Test
    public void testPlay() {
        addPlayers();
        String expected;
        Tunnel firstBlock = game.getMap().getFirstBlock();
        ArrayList<Tunnel> nextTo = firstBlock.getNextTo();
        expected = "I can move to the following blocks:\n";
        for (int i = 0; i < nextTo.size(); i++) {
            expected += "[" + i + "]" + nextTo.get(i) + "\n";
        }
        assertEquals(expected, game.play());
    }

    @Test
    public void testMovePlayerTo() {
        game.addPlayer("TEST_PLAYER");
        Player p = game.getPlayers().get(0);
        Tunnel original = p.getMyBlock();
        Tunnel movingTo = original.getNextTo().get(0);
        game.movePlayerTo(0);
        Tunnel newBlock = p.getMyBlock();
        assertEquals(movingTo, newBlock);
    }

    @Test
    public void testMovePlayerToString() {
        game.addPlayer("TEST_PLAYER");
        Player p = game.getPlayers().get(0);
        Tunnel t = p.getMyBlock();
        Tunnel movingTo = t.getNextTo().get(0);
        assertEquals("You have moved to " + movingTo, game.movePlayerTo(0));
    }

    @Test
    public void testMovePlayerToInvalidBlock() {
        game.addPlayer("TEST_PLAYER");
        Player p = game.getPlayers().get(0);
        Tunnel original = p.getMyBlock();
        game.movePlayerTo(23434);
        Tunnel newBlock = p.getMyBlock();
        assertEquals(original, newBlock);
    }

    @Test
    public void testMovePlayerToInvalidBlockString() {
        game.addPlayer("TEST_PLAYER");
        Player p = game.getPlayers().get(0);
        assertEquals("", game.movePlayerTo(23434));
    }
}
