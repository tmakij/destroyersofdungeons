package gameobjects.items;

import gameobjects.GameObject;
import localisation.Dictionary;

/**
 * Items can provide different types of bonuses. Their effects are checked
 * before actions.
 */
public abstract class Item extends GameObject {

    /**
     * Initialize a new item.
     *
     * @param id The unique id of the item.
     * @param key The key to the name of the item.
     */
    public Item(int id, String key) {
        super(id, Dictionary.getValue(key));
    }

    /**
     * If the item changes attack strength, it is modified by this method
     *
     * @param amount Original attack strengh.
     * @return New attack strength.
     */
    public int onAttack(int amount) {
        return amount;
    }

    /**
     * If the item changes the amount of damage received, it is modified by this
     * method
     *
     * @param amount Original damage.
     * @return Modified damage.
     */
    public int onDamageReceived(int amount) {
        return amount;
    }

    /**
     * Check if this item wins the game.
     *
     * @return Does this item win the game.
     */
    public boolean winsGame() {
        return false;
    }
}
