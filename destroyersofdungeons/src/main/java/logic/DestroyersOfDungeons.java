package logic;

import gameobjects.dungeon.Map;
import gameobjects.dungeon.Tunnel;
import gameobjects.actors.Player;
import gameobjects.items.WoodenSword;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Main class for the game. Holds players and their deathtimes, the game map and
 * handles the turns.
 */
public final class DestroyersOfDungeons {

    private final List<Player> players = new ArrayList<>();
    private final java.util.Map<Player, Integer> deathTimes = new HashMap<>();
    private final Map map;
    private Player currentPlayer;
    private int current = 0;
    private int playerIds = 0;
    private int totalTurns = 0;

    /**
     * Creates a new instance of the game, and a new dungeon for it.
     */
    public DestroyersOfDungeons() {
        map = new Map(0, 10);
    }

    /**
     * Selects next player.
     */
    public void nextPlayer() {
        if (players.size() == 2) {
            current = current == players.size() - 1 ? 0 : current + 1;
        } else if (players.size() == 1) {
            current = 0;
            totalTurns++;
        } else {
            throw new UnsupportedOperationException("Unsupported amount of players");
        }
        currentPlayer = players.get(current);
    }

    /**
     * Adds a new player to the game. Currently supports only 2 players!
     *
     * @param name The name of the player.
     */
    public void addPlayer(String name) {
        Player p = new Player(playerIds++, name, this);
        if (players.isEmpty()) {
            currentPlayer = p;
            p.addItem(new WoodenSword(0));
        }
        players.add(p);
        p.setMyBlock(map.getAStartingBlock());
    }

    /**
     * Gets the tunnel blocks next to current player's block.
     *
     * @return Blocks next to player's current block.
     */
    public List<Tunnel> getMovingPossibilities() {
        Tunnel nextBlock = currentPlayer.getMyBlock();
        List<Tunnel> nextTo = nextBlock.getNextTo();
        return nextTo;
    }

    /**
     * Moves the player.
     *
     * @param to Which block to move into.
     * @return The block where player moved into.
     */
    public Tunnel movePlayerTo(int to) {
        Tunnel nextBlock = currentPlayer.getMyBlock();
        List<Tunnel> nextTo = nextBlock.getNextTo();
        if (to > nextTo.size() - 1) {
            return null;
        }
        Tunnel block = nextTo.get(to);
        currentPlayer.setMyBlock(block);
        return block;
    }

    /**
     * Delets player from the game, and marks his death time.
     *
     * @param p Player to be removed.
     */
    public void removePlayer(Player p) {
        players.remove(p);
        deathTimes.put(p, totalTurns);
        if (p.equals(currentPlayer)) {
            nextPlayer();
        }
    }

    /**
     * Get the winning player.
     *
     * @return The winner. Returns null if there is none.
     */
    public Player getWinner() {
        for (Player p : players) {
            if (p.hasTreasure()) {
                return p;
            }
        }
        return null;
    }

    /**
     * Get the player death times.
     *
     * @return The player death times.
     */
    public java.util.Map<Player, Integer> getDeathTimes() {
        return deathTimes;
    }

    /**
     * Checks if the last move moved player to a block with other actors or
     * items.
     *
     * @return Whether collisions were created.
     */
    public boolean lastMoveCreatedCollisions() {
        Tunnel t = currentPlayer.getMyBlock();
        return !t.getOtherActors(currentPlayer).isEmpty() || !t.getItems().isEmpty();
    }

    /**
     * Get the player currently in turn.
     *
     * @return The player currently in turn.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get all other players in the game, even the dead ones.
     *
     * @param me The player who to ignore.
     * @return The list of other players.
     */
    public List<Player> getAllOtherPlayers(Player me) {
        List<Player> others = new ArrayList<>();
        addOtherPlayers(others, players, me);
        addOtherPlayers(others, deathTimes.keySet(), me);
        return others;
    }

    /**
     * Get the list of living players in the game.
     *
     * @return List of living players in the game.
     */
    public List<Player> getPlayers() {
        return players;
    }

    private void addOtherPlayers(List<Player> list, Collection<Player> where, Player ignore) {
        for (Player p : where) {
            if (!p.equals(ignore)) {
                list.add(p);
            }
        }
    }
}
