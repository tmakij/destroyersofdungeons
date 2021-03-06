package gameobjects.dungeon;

import constants.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Dungeon holds all the corridors.
 */
public final class Dungeon {

    /**
     * The List of all the corridors in the dungeon.
     */
    private final List<Corridor> corridors = new ArrayList<>();
    /**
     * The Random which is used for the randomisation.
     */
    private final Random rand = new Random();
    /**
     * The game ids. No UniquueObject may have the same id.
     */
    private int gameIDs = 0;

    /**
     * Initialize a new Dungeon, that is randomly generated.
     */
    public Dungeon() {
        final int amounOfCorridors = rand.nextInt(Constants.DUNGEON_MAX_CORRIDORS) + Constants.DUNGEON_MIN_CORRIDORS;
        final int tresureCorridor = rand.nextInt(amounOfCorridors);
        for (int i = 0; i < amounOfCorridors; i++) {
            boolean tresure = i == tresureCorridor;
            corridors.add(new Corridor(rand.nextInt(Constants.CORRIDOR_MAX_LENGTH) + Constants.CORRIDOR_MIN_LENGTH, this, rand, tresure));
        }
        for (Corridor c : corridors) {
            int amountOfNeighbours = rand.nextInt(corridors.size() - 3) + 1;
            for (int i = 0; i < amountOfNeighbours; i++) {
                int count = 0;
                while (c.neighboursTotal() < amountOfNeighbours) {
                    int n = rand.nextInt(corridors.size() - 2) + 1;
                    Corridor friend = corridors.get(n);
                    if (friend.neighboursTotal() < 1 || count > 30) {
                        c.makeNeighbour(friend);
                    }
                    count++;
                }
            }
        }
    }

    /**
     * Get a good starting block for a player. It does not have other actors or
     * items in it. It also isn't in the corridor which holds the treasure.
     *
     * @return The good tunnel block.
     */
    public Tunnel getAStartingBlock() {
        Corridor c;
        do {
            c = corridors.get(rand.nextInt(corridors.size() - 1));
        } while (c.hasTreasure());
        return c.getStartingBlock();
    }

    /**
     * Get a unique game id. Increments the gameIDs.
     *
     * @return A unique game id.
     */
    public int getGameID() {
        int id = gameIDs;
        gameIDs++;
        return id;
    }
}
