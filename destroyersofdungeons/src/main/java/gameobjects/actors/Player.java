package gameobjects.actors;

import gameobjects.items.Treasure;
import gameobjects.items.Item;
import logic.DestroyersOfDungeons;

/**
 * Player for the game. Player's die method removes him from the game and player
 * isPlayerControlled.
 */
public final class Player extends Actor {

    private final DestroyersOfDungeons game;

    /**
     * Creates a new instance of the player.
     *
     * @param id Unique id of the player.
     * @param name Name of the player.
     * @param game The game instance.
     */
    public Player(int id, String name, DestroyersOfDungeons game) {
        super(id, name);
        this.game = game;
    }

    @Override
    public boolean die() {
        if (super.die()) {
            game.removePlayer(this);
            return true;
        }
        return false;
    }

    public boolean hasTreasure() {
        for (Item i : getItems()) {
            if (i.getClass() == Treasure.class) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPlayerControlled() {
        return true;
    }
}
