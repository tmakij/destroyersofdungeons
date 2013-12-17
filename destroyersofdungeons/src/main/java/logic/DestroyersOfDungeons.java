package logic;

import actors.Player;
import java.util.ArrayList;

/**
 * Main class for the game
 */
public final class DestroyersOfDungeons {

    private final ArrayList<Player> players = new ArrayList<>();
    private final Map map;

    /**
     * Creates a new instance of the game.
     */
    public DestroyersOfDungeons() {
        map = new Map(0, 10);
        map.generate();
    }

    /**
     * Adds a new player to the game.
     *
     * @param name the name of the player.
     * @return Message about the operation.
     */
    public final String addPlayer(String name) {
        Player p = new Player(name);
        players.add(p);
        p.setMyBlock(map.getFirstBlock());
        return "Added player " + p;
    }

    /**
     * Starts the game.
     *
     * @param player which player 0-1
     * @return Operation string
     */
    public final String play(int player) {
        String ret;
        Player p = players.get(player);
        Tunnel nextBlock = p.getMyBlock();
        ArrayList<Tunnel> nextTo = nextBlock.getNextTo();
        ret = "I can move to the following blocks:\n";
        for (int i = 0; i < nextTo.size(); i++) {
            ret += "[" + i + "]" + nextTo.get(i) + "\n";
        }
        return ret;
    }

    public final String movePlayerTo(int player, int to) {
        Player p = players.get(player);
        Tunnel nextBlock = p.getMyBlock();
        ArrayList<Tunnel> nextTo = nextBlock.getNextTo();
        if (to > nextTo.size() - 1) {
            return "";
        }
        Tunnel block = nextTo.get(to);
        p.setMyBlock(block);
        return "You have moved to " + p.getMyBlock();
    }
}
