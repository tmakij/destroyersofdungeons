package gameobjects.items;

import gameobjects.GameObject;

/**
 * Items can provide different types of bonuses. Their effects are checked
 * before actions.
 */
public abstract class Item extends GameObject {

    public Item(int id, String name) {
        super(id, name);
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
}
