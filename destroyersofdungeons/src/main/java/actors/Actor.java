package actors;

import GameObject.GameObject;
import dungeon.Tunnel;
import items.Item;
import java.util.ArrayList;

/**
 * Base class for all actors in the game. Each actor has unique id.
 */
public abstract class Actor extends GameObject {

    public static final int BASE_HEALTH = 100;
    public static final int BASE_ATTACK = 25;

    private Tunnel myBlock;
    protected int health = BASE_HEALTH;
    private final ArrayList<Item> items = new ArrayList<>();

    public Actor(int id, String name) {
        super(id, name);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public final Tunnel getMyBlock() {
        return myBlock;
    }

    /**
     * Adds an item to the actor.
     *
     * @param i The item to be added.
     */
    public final void addItem(Item i) {
        items.add(i);
    }

    /**
     * Tells whether the actor is contolled by a human or not.
     *
     * @return Is the actor human.
     */
    public abstract boolean isPlayerControlled();

    /**
     * Moves the Actor to another block.
     *
     * @param myBlock Where to move.
     */
    public final void setMyBlock(Tunnel myBlock) {
        this.myBlock = myBlock;
    }

    /**
     * Current actor attacks the target actor.
     *
     * @param to The actor into which the damage is inflicted.
     */
    public final void attack(Actor to) {
        int amount = BASE_ATTACK;
        for (Item i : items) {
            amount = i.onAttack(amount);
        }
        to.takeHit(amount);
    }

    /**
     * The actor takes the damage.
     *
     * @param amount How much damage is inflicted.
     */
    public final void takeHit(int amount) {
        for (Item i : items) {
            amount = i.onDamageReceived(amount);
        }
        health -= amount;
    }
}
