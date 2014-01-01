package gameobjects.actors;

import constants.Constants;
import gameobjects.Itemholder;
import gameobjects.dungeon.Tunnel;
import gameobjects.items.Item;
import java.util.ArrayDeque;
import java.util.Deque;
import logic.BattleAction;

/**
 * Base class for all actors in the game. Actors have health and they are
 * located in some tunnel block. They are also itemholders.
 */
public abstract class Actor extends Itemholder {

    private final Deque<Tunnel> myBlockHistory = new ArrayDeque<>();
    private int health;

    /**
     * Create a new Actor.
     *
     * @param id The unique id.
     * @param name The name of the object.
     */
    public Actor(int id, String name) {
        super(id, name);
        health = Constants.ACTOR_BASE_HEALTH;
    }

    /**
     * Adds all the items on the block of the actor to his inventory.
     */
    public final void pickUpItems() {
        addItems(getMyBlock().getItems());
        getMyBlock().removeAllItems();
    }

    /**
     * Erases the actor from the game, if the actor is dead.
     *
     * @return Did the actor die.
     */
    public boolean die() {
        if (!isAlive()) {
            getMyBlock().removeActor(this);
            getMyBlock().addItems(getItems());
            return true;
        }
        return false;
    }

    /**
     * Makes the actor retreat to the last tunnel block he remembers.
     */
    public final void retreat() {
        setMyBlock(myBlockHistory.peekLast());
        Tunnel first = getMyBlock();
        myBlockHistory.clear();
        myBlockHistory.addFirst(first);
    }

    /**
     * Determines, wheter the actor has is alive. Actor is alive when he has
     * positive health.
     *
     * @return Is the actor alive or not.
     */
    public final boolean isAlive() {
        return health > 0;
    }

    /**
     * Get the health of the actor.
     *
     * @return Health of the actor.
     */
    public final int getHealth() {
        return health;
    }

    /**
     * Returns the block on which the actor is currently on.
     *
     * @return The block on which the actor is currently on.
     */
    public final Tunnel getMyBlock() {
        return myBlockHistory.peekFirst();
    }

    /**
     * Tells whether the actor is contolled by a human or not.
     *
     * @return Is the actor human.
     */
    public abstract boolean isPlayerControlled();

    /**
     * Moves the Actor to another block. Saves the previous block. Removes the
     * actor from the previous block and adds to the new one. The amount of
     * blocks remembered is defined in constants.
     *
     * @param newBlock Where to move.
     */
    public final void setMyBlock(Tunnel newBlock) {
        if (getMyBlock() != null) {
            getMyBlock().removeActor(this);
        }
        myBlockHistory.addFirst(newBlock);
        newBlock.addActor(this);
        if (myBlockHistory.size() > Constants.TUNNEL_HISTORY) {
            myBlockHistory.removeLast();
        }
    }

    /**
     * Current actor attacks the target actor.
     *
     * @param to The actor into which the damage is inflicted.
     * @param act Multiplies the default damage by the action.
     */
    public final void attack(Actor to, BattleAction act) {
        int amount = (int) (Constants.ACTOR_BASE_ATTACK * act.actModifier());
        for (Item i : getItems()) {
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
        for (Item i : getItems()) {
            amount = i.onDamageReceived(amount);
        }
        health -= amount;
    }
}
