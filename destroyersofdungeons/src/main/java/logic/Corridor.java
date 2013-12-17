package logic;

/**
 * Corridor is a line of blocks. In the end of it there are one or more
 * corridors or the treasure.
 */
public final class Corridor {

    private final int lenght;
    private Tunnel startBlock;
    private Tunnel endBlock;

    public Corridor(int lenght) {
        this.lenght = lenght;
    }

    public Tunnel getStartBlock() {
        return startBlock;
    }

    public void generate() {
        // Test dungeon
        Tunnel previous = new Tunnel();
        startBlock = previous;
        Tunnel block = null;
        for (int i = 0; i < lenght; i++) {
            block = new Tunnel();
            block.addBlock(previous);
            previous.addBlock(block);
            previous = block;
        }
        endBlock = block;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
