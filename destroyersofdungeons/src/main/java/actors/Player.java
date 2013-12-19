package actors;

import logic.DestroyersOfDungeons;

/**
 * Player for the game.
 */
public final class Player extends Actor {

    public Player(int id, String name) {
        super(id, name);
    }

    @Override
    public void die(DestroyersOfDungeons game) {
        if (!isAlive()) {
            super.die(game);
            game.removePlayer(this);
        }
    }

    @Override
    public boolean isPlayerControlled() {
        return true;
    }
}
