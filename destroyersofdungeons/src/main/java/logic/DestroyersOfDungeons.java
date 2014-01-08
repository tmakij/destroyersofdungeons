package logic;

import constants.Constants;
import gameobjects.dungeon.Dungeon;
import gameobjects.dungeon.Tunnel;
import gameobjects.actors.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Main class for the game. Holds players and their deathtimes, the game map and
 * handles the turns.
 */
public final class DestroyersOfDungeons {

    private final Set<Player> playedTurn = new HashSet<>();
    private final List<Player> players = new ArrayList<>();
    private final Map<Player, Integer> deathTimes = new HashMap<>();
    private final Dungeon map;
    private Player currentPlayer;
    private int current = 0;
    private int playerIds = 0;
    private int totalTurns = 1;

    /**
     * Creates a new instance of the game, and a new dungeon for it.
     */
    public DestroyersOfDungeons() {
        map = new Dungeon();
    }

    /**
     * Selects next player, and increments the turncounter if necessary.
     */
    public void nextPlayer() {
        playedTurn.add(currentPlayer);
        if (current < players.size() && playedTurn.contains(players.get(current))) {
            current++;
        }
        if (current >= players.size()) {
            current = 0;
        }
        currentPlayer = players.get(current);
        turnsOver();
    }

    private void turnsOver() {
        if (playedTurn.size() >= players.size() && playedTurn.contains(currentPlayer)) {
            totalTurns++;
            playedTurn.clear();
            for (Player p : players) {
                p.heal();
            }
        }
    }

    /**
     * Adds a new player to the game.
     *
     * @param name The name of the player.
     * @return Was the name according to the regex pattern.
     */
    public boolean addPlayer(String name) {
        if (!name.matches("[" + Constants.ALLOWED_CHARACTERS + "]{" + Constants.PLAYER_NAME_MIN_LENGHT
                + "," + Constants.PLAYER_NAME_MAX_LENGHT + "}")) {
            players.clear();
            return false;
        }
        Player p = new Player(++playerIds, name, this);
        if (players.isEmpty()) {
            currentPlayer = p;
        }
        players.add(p);
        p.setMyBlock(map.getAStartingBlock());
        return true;
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
     */
    public void movePlayerTo(int to) {
        Tunnel nextBlock = currentPlayer.getMyBlock();
        List<Tunnel> nextTo = nextBlock.getNextTo();
        if (to < nextTo.size()) {
            Tunnel block = nextTo.get(to);
            currentPlayer.setMyBlock(block);
        }
    }

    /**
     * Delets player from the game, and marks his death time.
     *
     * @param p Player to be removed.
     */
    public void removePlayer(Player p) {
        players.remove(p);
        deathTimes.put(p, totalTurns);
        if (!players.isEmpty() && p.equals(currentPlayer)) {
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
    public Map<Player, Integer> getDeathTimes() {
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

    /**
     * Get the total amount of turns played. A turn is passed when everyone has
     * moved once.
     *
     * @return The total amount of turns played
     */
    public int getTotalTurns() {
        return totalTurns;
    }

    private void addOtherPlayers(List<Player> list, Collection<Player> where, Player ignore) {
        for (Player p : where) {
            if (!p.equals(ignore)) {
                list.add(p);
            }
        }
    }
}
