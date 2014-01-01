package gameobjects.dungeon;

import gameobjects.actors.monsters.Minotaur;
import gameobjects.items.Treasure;

/**
 * Corridor is a line of tunnel blocks. In the end of it there are one or more
 * corridors or the treasure.
 */
public final class Corridor {

    private final int lenght;
    private final Tunnel startBlock;
    private final Tunnel endBlock;

    /**
     * Creates a new Corridor with a certain lenght.
     *
     * @param lenght The lenght of the Corridor.
     */
    public Corridor(int lenght) {
        this.lenght = lenght;

        // Test dungeon
        int ids = 0;
        Tunnel previous = new Tunnel(ids++);
        startBlock = previous;
        Tunnel block = null;
        for (int i = 0; i < lenght; i++) {
            block = new Tunnel(ids++);
            block.addBlock(previous);
            previous.addBlock(block);
            previous = block;
        }
        Minotaur m = new Minotaur(0);
        //m.setMyBlock(startBlock.getNextTo().get(0));
        startBlock.getNextTo().get(0).getNextTo().get(1).addItem(new Treasure());
        endBlock = block;
    }

    public Tunnel getStartBlock() {
        return startBlock;
    }
}
