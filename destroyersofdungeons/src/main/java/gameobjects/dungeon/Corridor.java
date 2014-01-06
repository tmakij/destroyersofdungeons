package gameobjects.dungeon;

import gameobjects.UniqueObject;
import gameobjects.actors.monsters.Minotaur;
import gameobjects.items.Treasure;
import gameobjects.items.WoodenShield;
import gameobjects.items.WoodenSword;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Corridor is a line of tunnel blocks. In the end of it there are one or more
 * corridors or the treasure.
 */
final class Corridor extends UniqueObject {

    protected final Tunnel startBlock;
    private final Tunnel endBlock;
    private final boolean hasTreasure;
    private final Set<Corridor> nextTo = new HashSet<>();

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

        Tunnel block;
        for (int i = 0; i < lenght; i++) {
            block = new Tunnel(d.getGameID());
            block.addBlock(previous);
            if (rand.nextDouble() <= 0.04D) {
                Minotaur m = new Minotaur(d.getGameID());
                m.setMyBlock(block);
            }
            if (rand.nextDouble() <= 0.03D) {
                block.addItem(new WoodenSword(d.getGameID()));
            }
            if (rand.nextDouble() <= 0.02D) {
                block.addItem(new WoodenShield(d.getGameID()));
            }
            previous = block;
            if (i == lenght - 1) {
                endBlock.addBlock(block);
            }
        }

        if (treasure) {
            endBlock.addItem(new Treasure());
        }
    }

    void makeNeighbour(Corridor c) {
        if (!nextTo.contains(c) && !c.equals(this)) {
            nextTo.add(c);
            c.nextTo.add(this);
            c.endBlock.addBlock(startBlock);
        }
    }

    int neighboursTotal() {
        return nextTo.size();
    }

    boolean hasTreasure() {
        return hasTreasure;
    }

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
