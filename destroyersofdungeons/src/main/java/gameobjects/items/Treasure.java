package gameobjects.items;

/**
 * The treasure. If a player finds it, he wins the game.
 */
public final class Treasure extends Item {

    /**
     * Creates a new treasure.
     */
    public Treasure() {
        super(-1, "TREASURE");
    }

    @Override
    public boolean winsGame() {
        return true;
    }
}
