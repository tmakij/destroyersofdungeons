package gameobjects.actors;

import gameobjects.items.Item;
import logic.DestroyersOfDungeons;

/**
 * Player for the game. Player's die method removes him from the game and player
 * isPlayerControlled.
 */
public final class Player extends Actor {

    /**
     * The current instance of the game where the players is.
     */
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

    /**
     * Determines whether the Player has a tresure type item.
     *
     * @return Has the player the Treasure.
     */
    public boolean hasTreasure() {
        for (Item i : getItems()) {
            if (i.winsGame()) {
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
