package logic;

import actors.Player;
import java.util.ArrayList;

/**
 * Main class for the game
 */
public final class DestroyersOfDungeons {

    private final ArrayList<Player> players = new ArrayList<>();
    private final Map map;
    private Player currentPlayer;
    private int current = 0;

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
     * @return Message about the operation.
     */
    public final String nextPlayer() {
        current = current == players.size() - 1 ? 0 : current + 1;
        currentPlayer = players.get(current);
        return "Now is " + currentPlayer + "'s turn";
    }

    /**
     * Adds a new player to the game.
     *
     * @param name the name of the player.
     * @return Message about the operation.
     */
    public final String addPlayer(String name) {
        String ret;
        Player p = new Player(name);
        players.add(p);
        p.setMyBlock(map.getFirstBlock());
        ret = "Added player " + p;
        if (currentPlayer == null){
            currentPlayer = p;
            ret += " (Starting player)";
        }
        return ret;
    }

    /**
     * Starts the turn.
     *
     * @return Message about the operation.
     */
    public final String play() {
        String ret;
        Tunnel nextBlock = currentPlayer.getMyBlock();
        ArrayList<Tunnel> nextTo = nextBlock.getNextTo();
        ret = "I can move to the following blocks:\n";
        for (int i = 0; i < nextTo.size(); i++) {
            ret += "[" + i + "]" + nextTo.get(i) + "\n";
        }
        return ret;
    }

    /**
     * Moves the player.
     *
     * @param to Which block to move into.
     * @return Message about the operation.
     */
    public final String movePlayerTo(int to) {
        Tunnel nextBlock = currentPlayer.getMyBlock();
        ArrayList<Tunnel> nextTo = nextBlock.getNextTo();
        if (to > nextTo.size() - 1) {
            return "";
        }
        Tunnel block = nextTo.get(to);
        currentPlayer.setMyBlock(block);
        return "You have moved to " + currentPlayer.getMyBlock();
    }
}
