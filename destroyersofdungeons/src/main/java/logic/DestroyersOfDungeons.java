package logic;

import gameobjects.dungeon.Map;
import gameobjects.dungeon.Tunnel;
import gameobjects.actors.Player;
import gameobjects.items.WoodenSword;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Main class for the game
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
     * Creates a new instance of the game.
     */
    public DestroyersOfDungeons() {
        map = new Map(0, 10);
        map.generate();
    }

    /**
     * Selects next player.
     *
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
     * Adds a new player to the game.
     *
     * @param name the name of the player.
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
     * Starts the turn.
     *
     * @return Blocks next to player's current block.
     */
    public List<Tunnel> play() {
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

    public void removePlayer(Player p) {
        players.remove(p);
        deathTimes.put(p, totalTurns);
        if (p.equals(currentPlayer)) {
            nextPlayer();
        }
    }

    public boolean hasPlayers() {
        return !players.isEmpty();
    }

    public Player getWinner() {
        for (Player p : players) {
            if (p.hasTreasure()) {
                return p;
            }
        }
        return null;
    }

    public java.util.Map<Player, Integer> getDeathTimes() {
        return deathTimes;
    }

    public boolean lastMoveCreatedCollisions() {
        Tunnel t = currentPlayer.getMyBlock();
        return !t.getOtherActors(currentPlayer).isEmpty() || !t.getItems().isEmpty();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map getMap() {
        return map;
    }
}
