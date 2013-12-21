package logic;

import gameobjects.dungeon.Map;
import gameobjects.dungeon.Tunnel;
import gameobjects.actors.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the game
 */
public final class DestroyersOfDungeons {

    private final List<Player> players = new ArrayList<>();
    private final Map map;
    private Player currentPlayer;
    private int current = 0;
    private int playerIds = 0;

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
        Player p = new Player(playerIds++, name);
        if (players.isEmpty()) {
            currentPlayer = p;
        }
        players.add(p);
        p.setMyBlock(map.getAStartingBlock());
        map.getAStartingBlock().addActor(p);
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
        currentPlayer.getMyBlock().removeActor(currentPlayer);
        currentPlayer.setMyBlock(block);
        currentPlayer.getMyBlock().addActor(currentPlayer);
        return block;
    }

    public void removePlayer(Player p) {
        players.remove(p);
        if (p.equals(currentPlayer)) {
            nextPlayer();
        }
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
