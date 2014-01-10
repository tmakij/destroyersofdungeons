package logic;

import constants.Constants;
import gameobjects.actors.Player;
import gameobjects.dungeon.Tunnel;
import gameobjects.items.Item;
import gameobjects.items.Treasure;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import testSetUp.InitClass;

public final class DestroyersOfDungeonsTest extends InitClass {

    private static final Random rand = new Random();

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
        assertTrue(game.getDeathTimes().containsKey(p));
    }

    @Test
    public void testStartBlockIsEmptyFromActorAndItems() {
        boolean isEmpty = true;
        final int rounds = 1000;

        for (int i = 0; i < rounds; i++) {
            game = new DestroyersOfDungeons();
            game.addPlayer("TEST_PLAYER");
            game.addPlayer("TEST_PLAYER_NO2");
            Tunnel myBlock = game.getCurrentPlayer().getMyBlock();
            if (myBlock.getActors().size() > 1 || !myBlock.getItems().isEmpty()) {
                isEmpty = false;
            }
        }
        assertTrue(isEmpty);
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
        game.getCurrentPlayer().getMyBlock().addActor(new Player(32, "TEST_PLAYER_NO2", null));
        assertTrue(game.lastMoveCreatedCollisions());
    }

    @Test
    public void testLastMoveCreatedCollisionsAfterMove() {
        Tunnel myNewBlock = new Tunnel(3244);
        Tunnel myNewBlock2 = new Tunnel(324456);
        myNewBlock.addBlock(myNewBlock2);
        game.getCurrentPlayer().setMyBlock(myNewBlock);
        addAnotherPlayer();
        game.nextPlayer();
        game.getCurrentPlayer().setMyBlock(myNewBlock);
        game.movePlayerTo(0);
        game.nextPlayer();
        game.movePlayerTo(0);
        assertTrue(game.lastMoveCreatedCollisions());
    }

    @Test
    public void testAloneDidNotCreateCollisions() {
        assertFalse(game.lastMoveCreatedCollisions());
    }

    @Test
    public void testLastMoveDidNotCreateCollisionsAfterMove() {
        addAnotherPlayer();
        List<Tunnel> nextTo = game.getCurrentPlayer().getMyBlock().getNextTo();
        nextTo.clear();
        nextTo.add(new Tunnel(0));
        game.movePlayerTo(0);
        assertFalse(game.lastMoveCreatedCollisions());
    }

    @Test
    public void testLastMoveCreatedCollisionsAfterMoveItem() {
        game.getCurrentPlayer().getMyBlock().addItem(Item.getRandomItem(rand));
        assertTrue(game.lastMoveCreatedCollisions());
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

    @Test
    public void testAddPlayerReturnTrue() {
        assertTrue(game.addPlayer("TEST_PLAYER_NO3"));
    }

    private String getNameWithLenght(int l) {
        String n = "";
        for (int i = 0; i < l; i++) {
            n += "a";
        }
        return n;
    }

    @Test
    public void testAddPlayerTooShort() {
        assertFalse(game.addPlayer(getNameWithLenght(Constants.PLAYER_NAME_MIN_LENGHT - 1)));
    }

    @Test
    public void testAddPlayerTooLong() {
        assertFalse(game.addPlayer(getNameWithLenght(Constants.PLAYER_NAME_MAX_LENGHT + 1)));
    }

    @Test
    public void testAddInvalidPlayerDeletesOthers() {
        game.addPlayer(getNameWithLenght(Constants.PLAYER_NAME_MAX_LENGHT + 1));
        assertTrue(game.getPlayers().isEmpty());
    }

    @Test
    public void testAddInvalidPlayerSomeInvalidChars() {
        for (int i = 0; i < 1024; i++) {
            String name = Character.toString((char) i);
            if (!name.matches("[" + Constants.ALLOWED_CHARACTERS + "]") && game.addPlayer(name)) {
                fail("Added regardless: " + name);
            }
        }
        assertTrue(true);
    }

    @Test
    public void testPlayerDeathCorrectTurnWhenSecondLast() {
        addAnotherPlayer();
        game.addPlayer("TEST_PLAYER_NO3");
        game.nextPlayer();
        game.nextPlayer();
        Player p = game.getCurrentPlayer();
        game.nextPlayer();
        game.nextPlayer();
        game.removePlayer(game.getCurrentPlayer());
        assertEquals(p, game.getCurrentPlayer());
    }

    @Test
    public void testPlayerDeathCorrectTurnWhenLast() {
        addAnotherPlayer();
        game.addPlayer("TEST_PLAYER_NO3");
        Player p = game.getCurrentPlayer();
        game.nextPlayer();
        game.nextPlayer();
        game.removePlayer(game.getCurrentPlayer());
        assertEquals(p, game.getCurrentPlayer());
    }
}
