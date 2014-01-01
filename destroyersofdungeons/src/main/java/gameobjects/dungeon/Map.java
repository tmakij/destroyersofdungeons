package gameobjects.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The map holds all the corridors.
 */
public final class Map {

    private final int minSize;
    private final int maxSize;
    private final List<Corridor> corridors = new ArrayList<>();

    /**
     * Initialize a new Dungeon, that is randomly generated.
     *
     * @param minSize Minimium amount of corridors in the map.
     * @param maxSize Maximium amount of corridors in the map.
     */
    public Map(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        Corridor c = new Corridor(10);
        corridors.add(c);
    }

    /**
     * Get a good starting block for a player. It does not have other actors or
     * items in it. It also isn't in the corridor which holds the treasure.
     *
     * @return The good tunnel block.
     */
    public Tunnel getAStartingBlock() {
        return corridors.get(0).getStartBlock();
    }
}
