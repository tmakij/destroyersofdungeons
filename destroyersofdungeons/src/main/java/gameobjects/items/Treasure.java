package gameobjects.items;

/**
 * The treasure. If a player finds it, he wins the game. Overrides winsGame.
 */
public final class Treasure extends Item {

    /**
     * Creates a new treasure.
     */
    public Treasure() {
        super(Treasure.class);
    }

    @Override
    public boolean winsGame() {
        return true;
    }
}
