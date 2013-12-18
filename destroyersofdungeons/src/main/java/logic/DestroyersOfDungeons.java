package logic;

import dungeon.Map;
import dungeon.Tunnel;
import actors.Player;
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
        current = current == players.size() - 1 ? 0 : current + 1;
        currentPlayer = players.get(current);
    }

    /**
     * Adds a new player to the game.
     *
     * @param name the name of the player.
     * @return Message about the operation.
     */
    public String addPlayer(String name) {
        String ret;
        Player p = new Player(playerIds++, name);
        ret = "Added player " + p;
        if (players.isEmpty()) {
            currentPlayer = p;
            ret += " (Starting player)";
        }
        players.add(p);
        p.setMyBlock(map.getFirstBlock());
        map.getFirstBlock().addActor(p);
        return ret;
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
