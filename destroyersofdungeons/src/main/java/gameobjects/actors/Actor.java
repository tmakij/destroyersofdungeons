package gameobjects.actors;

import gameobjects.Itemholder;
import gameobjects.dungeon.Tunnel;
import gameobjects.items.Item;
import logic.BattleAction;

/**
 * Base class for all actors in the game. Each actor has unique id.
 */
public abstract class Actor extends Itemholder {

    public static final int BASE_HEALTH = 100;
    public static final int BASE_ATTACK = 25;

    private Tunnel myLastBlock;
    private Tunnel myBlock;
    private int health;

    public Actor(int id, String name) {
        super(id, name);
        health = BASE_HEALTH;
    }

    /**
     * Erases the actor from the game.
     *
     * @return Did the actor die
     */
    public boolean die() {
        if (!isAlive()) {
            myBlock.removeActor(this);
            myBlock.addItems(getItems());
            return true;
        }
        return false;
    }

    /**
     * Determines, wheter the actor has health > 0.
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
     * Get the lastblock of the actor.
     *
     * @return The lastblock of the actor.
     */
    public final Tunnel getMyLastBlock() {
        return myLastBlock;
    }

    /**
     * Returns the block on which the actor is currently on.
     *
     * @return The block on which the actor is currently on.
     */
    public final Tunnel getMyBlock() {
        return myBlock;
    }

    /**
     * Tells whether the actor is contolled by a human or not.
     *
     * @return Is the actor human.
     */
    public abstract boolean isPlayerControlled();

    /**
     * Moves the Actor to another block. Saves the previous block. Removes the
     * actor from the previous block and adds to the new one.
     *
     * @param newBlock Where to move.
     */
    public final void setMyBlock(Tunnel newBlock) {
        if (myBlock != null) {
            myBlock.removeActor(this);
        }
        myLastBlock = myBlock;
        myBlock = newBlock;
        newBlock.addActor(this);
    }

    /**
     * Current actor attacks the target actor.
     *
     * @param to The actor into which the damage is inflicted.
     * @param act Multiplies the default damage by the action.
     */
    public final void attack(Actor to, BattleAction act) {
        int amount = (int) (BASE_ATTACK * act.actModifier());
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
