package logic;

import gameobjects.dungeon.Tunnel;
import gameobjects.actors.Player;
import gameobjects.items.Treasure;
import gameobjects.items.WoodenSword;
import java.util.ArrayList;
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

    private void addAnotherPlayer() {
        game.addPlayer("TEST_PLAYER_NO2");
    }

    @Test
    public void testDeathTimesHasPlayersSize() {
        addAnotherPlayer();
        game.removePlayer(game.getCurrentPlayer());
        assertEquals(1, game.getDeathTimes().size());
    }

    @Test
    public void testDeathTimesHasPlayersContains() {
        addAnotherPlayer();
        Player p = game.getCurrentPlayer();
        game.removePlayer(p);
        assertEquals(true, game.getDeathTimes().containsKey(p));
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
    public void testgetMovingPossibilities() {
        Tunnel firstBlock = game.getCurrentPlayer().getMyBlock();
        List<Tunnel> nextTo = firstBlock.getNextTo();
        assertEquals(nextTo, game.getMovingPossibilities());
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
    public void testMovePlayerToInvalidBlock() {
        Player p = game.getCurrentPlayer();
        Tunnel original = p.getMyBlock();
        game.movePlayerTo(23434);
        Tunnel newBlock = p.getMyBlock();
        assertEquals(original, newBlock);
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

    @Test
    public void testLastMoveCreatedCollisionsDefault() {
        addAnotherPlayer();
        assertEquals(true, game.lastMoveCreatedCollisions());
    }

    @Test
    public void testLastMoveCreatedCollisionsAfterMove() {
        addAnotherPlayer();
        game.movePlayerTo(0);
        game.nextPlayer();
        game.movePlayerTo(0);
        assertEquals(true, game.lastMoveCreatedCollisions());
    }

    @Test
    public void testAloneDidNotCreateCollisions() {
        assertEquals(false, game.lastMoveCreatedCollisions());
    }

    @Test
    public void testLastMoveDidNotCreateCollisionsAfterMove() {
        addAnotherPlayer();
        List<Tunnel> nextTo = game.getCurrentPlayer().getMyBlock().getNextTo();
        nextTo.clear();
        nextTo.add(new Tunnel(0));
        game.movePlayerTo(0);
        assertEquals(false, game.lastMoveCreatedCollisions());
    }

    @Test
    public void testLastMoveCreatedCollisionsAfterMoveItem() {
        game.getCurrentPlayer().getMyBlock().addItem(new WoodenSword(0));
        assertEquals(true, game.lastMoveCreatedCollisions());
    }

    @Test
    public void testNullWinnerWhenTreasureNotFound() {
        assertEquals(null, game.getWinner());
    }

    @Test
    public void testWinnerWhenTreasureFound() {
        addAnotherPlayer();
        Player p = game.getCurrentPlayer();
        p.addItem(new Treasure());
        game.nextPlayer();
        assertEquals(p, game.getWinner());
    }

    private List<Player> createOthers() {
        addAnotherPlayer();
        Player p = game.getCurrentPlayer();
        game.nextPlayer();
        List<Player> others = new ArrayList<>();
        others.add(p);
        return others;
    }

    @Test
    public void testGetOtherPlayersWork() {
        List<Player> others = createOthers();
        assertEquals(others, game.getAllOtherPlayers(game.getCurrentPlayer()));
    }

    @Test
    public void testGetOtherPlayersWorkWithDeath() {
        List<Player> others = createOthers();
        Player p3 = new Player(0, "TEST_PLAYER_NO3", game);
        others.add(p3);
        game.getDeathTimes().put(p3, Integer.SIZE);
        assertEquals(others, game.getAllOtherPlayers(game.getCurrentPlayer()));
    }

    @Test
    public void testTotalTurnDefault() {
        assertEquals(1, game.getTotalTurns());
    }

    @Test
    public void testWhen7TurnsPass() {
        addAnotherPlayer();
        int playerCount = game.getPlayers().size();
        for (int i = 0; i < 7 * playerCount; i++) {
            game.nextPlayer();
        }
        assertEquals(8, game.getTotalTurns());
    }
}
