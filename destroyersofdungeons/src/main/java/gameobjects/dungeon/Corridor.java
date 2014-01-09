package gameobjects.dungeon;

import constants.Constants;
import gameobjects.UniqueObject;
import gameobjects.actors.monsters.Monster;
import gameobjects.items.Item;
import gameobjects.items.Treasure;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Corridor is a line of tunnel blocks. In the end of it there are one or more
 * corridors or the treasure.
 */
final class Corridor extends UniqueObject {

    // For testing
    @SuppressWarnings("ProtectedMemberInFinalClass")
    /**
     * The first member of the corridor.
     */
    protected final Tunnel startBlock;
    /**
     * The last member of the corridor.
     */
    private final Tunnel endBlock;
    /**
     * Has this corridor the treasure. If yes, no player can start in this
     * corridor.
     */
    private final boolean hasTreasure;
    /**
     * The collection of corridors next to this. A corridor is next other one,
     * if their end- or startblocks are next to each other.
     */
    private final Collection<Corridor> nextTo = new HashSet<>();

    /**
     * Creates a new Corridor with a certain lenght.
     *
     * @param lenght The lenght of the Corridor.
     * @param d Dungeon where the corridor belongs to.
     * @param rand java.util.Random which is used to create randomness.
     */
    Corridor(int lenght, Dungeon d, Random rand, boolean treasure) {
        super(d.getGameID());
        hasTreasure = treasure;

        Tunnel previous = new TunnelEnd(d.getGameID());
        endBlock = new TunnelEnd(d.getGameID());
        startBlock = previous;

        Tunnel block = null;
        for (int i = 0; i < lenght; i++) {
            block = new Tunnel(d.getGameID());
            block.addBlock(previous);
            if (rand.nextDouble() <= Constants.ITEM_SPAWN_CHANCE) {
                block.addItem(Item.getRandomItem(rand));
            }
            if (rand.nextDouble() <= Constants.MONSTER_SPAWN_CHANCE) {
                Monster m = Monster.getRandomMonster(rand, d.getGameID());
                m.setMyBlock(block);
                m.pickUpItems();
            }
            previous = block;
        }
        endBlock.addBlock(block);
        if (treasure) {
            endBlock.addItem(new Treasure());
        }
    }

    /**
     * Makes this corridor neighbour to another one.
     *
     * @param c The corridor which to add to neighbours. Makes its endblock
     * neighbour to this one's startblock.
     */
    void makeNeighbour(Corridor c) {
        if (!nextTo.contains(c) && !c.equals(this)) {
            nextTo.add(c);
            c.nextTo.add(this);
            c.endBlock.addBlock(startBlock);
        }
    }

    /**
     * Get the total amount of neighbours.
     *
     * @return The total amount of neighbours.
     */
    int neighboursTotal() {
        return nextTo.size();
    }

    /**
     * Has this corridor a tresure.
     *
     * @return Has this corridor a tresure.
     */
    boolean hasTreasure() {
        return hasTreasure;
    }

    /**
     * Get a starting block. The block cannot be a startng or ending block, and
     * it cannot have items or other actors.
     *
     * @return
     */
    Tunnel getStartingBlock() {
        for (Tunnel t : startBlock.getNextTo()) {
            if (!t.isEndBlock()) {
                Tunnel intoCorridor = t;
                Random rand = new Random();
                while (!intoCorridor.isEmpty()) {
                    List<Tunnel> nextTunnels = intoCorridor.getNextTo();
                    intoCorridor = nextTunnels.get(rand.nextInt(nextTunnels.size() - 1) + 1);
                }
                return intoCorridor;
            }
        }
        return startBlock;
    }
}
