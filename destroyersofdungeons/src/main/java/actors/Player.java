package actors;

/**
 * Player for the game.
 */
public final class Player extends Actor {

    public Player(int id, String name) {
        super(id, name);
    }

    @Override
    public boolean isPlayerControlled() {
        return true;
    }
}
