package actors;

import dungeon.Tunnel;

/**
 * Base class for all actors in the game. Each actor has unique id.
 */
public abstract class Actor {

    private Tunnel myBlock;
    private static int ids = 0;
    private final int id;
    private int health = 100;

    public Actor() {
        this.id = ++ids;
    }

    public final Tunnel getMyBlock() {
        return myBlock;
    }

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
    public void attack(Actor to) {
        to.takeHit(25);
    }

    /**
     * The actor takes the damage. Replace with item param?
     *
     * @param amount
     */
    public void takeHit(int amount) {
        health -= amount;
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actor other = (Actor) obj;
        return this.id == other.id;
    }
}
