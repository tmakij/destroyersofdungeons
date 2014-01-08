package gameobjects.actors;

import constants.Constants;
import gameobjects.items.Item;
import gameobjects.items.Treasure;
import java.util.Random;
import logic.DestroyersOfDungeons;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class PlayerTest {

    private static final Random rand = new Random();

    @BeforeClass
    public static void onlyOnce() {
        Item.loadItemTypes();
    }
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
        assertTrue(p.isPlayerControlled());
    }

    @Test
    public void testDyeingAndRemovalFromGame() {
        killPlayer();
        assertFalse(game.getPlayers().contains(p));
    }

    @Test
    public void testLivingKeepPlayingGame() {
        dontDie();
        assertTrue(game.getPlayers().contains(p));
    }

    @Test
    public void testLivingStayInYourBlock() {
        dontDie();
        assertTrue(p.getMyBlock().getActors().contains(p));
    }

    @Test
    public void testDyeingAndRemovalFromMyBlock() {
        killPlayer();
        assertFalse(p.getMyBlock().getActors().contains(p));
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
        assertFalse(game.getPlayers().contains(def));
    }

    @Test
    public void testAttLosersDie() {
        setUpBattle();
        att.takeHit(Constants.ACTOR_BASE_HEALTH);
        att.die();
        assertFalse(game.getPlayers().contains(att));
    }

    @Test
    public void testAttDontDie() {
        setUpBattle();
        att.takeHit(Constants.ACTOR_BASE_HEALTH - 1);
        assertFalse(att.die());
    }

    @Test
    public void testHasTresureWhenHasIt() {
        p.addItem(new Treasure());
        assertTrue(p.hasTreasure());
    }

    @Test
    public void testHasntTresureWhenDoNotHaveIt() {
        assertFalse(p.hasTreasure());
    }

    @Test
    public void testHasntTresureWhenHaveWrongItem() {
        p.addItem(Item.getRandomItem(rand));
        assertFalse(p.hasTreasure());
    }

    @Test
    public void testIDAssignedOnGameCreate() {
        game = new DestroyersOfDungeons();
        game.addPlayer("TEST_PLAYER_NX");
        p = new Player(1, "TEST_PLAYER_NY", game);
        assertEquals(p, game.getCurrentPlayer());
    }
}
