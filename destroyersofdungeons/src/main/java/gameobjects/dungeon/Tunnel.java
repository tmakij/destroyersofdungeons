package gameobjects.dungeon;

import gameobjects.Itemholder;
import gameobjects.actors.Actor;
import java.util.ArrayList;
import java.util.List;
import localisation.Dictionary;

/**
 * The basic block from which the dungeon is build. Can contain actors and
 * items. Knows the blocks that are next to it. Is an ItemHolder.
 */
public class Tunnel extends Itemholder {

    /**
     * The neighbouring tunnels.
     */
    private final List<Tunnel> nextTo = new ArrayList<>();
    /**
     * The actors in the tunnel.
     */
    private final List<Actor> actors = new ArrayList<>();

    /**
     * Create a new tunnel with a unique id.
     *
     * @param id The unique id.
     */
    public Tunnel(int id) {
        super(id);
    }

    /**
     * Checks if the tunnel is a end or start block.
     *
     * @return Does the block start or end an tunnel.
     */
    protected boolean isEndBlock() {
        return false;
    }

    /**
     * Adds a new block to the list of known neighbours. Does nothing if the
     * block equals this or if the block is already in the list.
     *
     * @param t The tunnel block to be added.
     */
    public final void addBlock(Tunnel t) {
        if (t != null && !t.equals(this) && !nextTo.contains(t)) {
            nextTo.add(t);
            if (isEndBlock() && t.isEndBlock()) {
                for (int i = 0; i < t.nextTo.size(); i++) {
                    Tunnel t2 = t.nextTo.get(i);
                    if (t2.isEndBlock()) {
                        addBlock(t2);
                    }
                }
            }
            t.addBlock(this);
        }
    }

    /**
     * Get the list of neighbouring tunnel blocks.
     *
     * @return List of neighbouring tunnel blocks.
     */
    public final List<Tunnel> getNextTo() {
        return nextTo;
    }

    /**
     * Checks if there are items or actors in the tunnel.
     *
     * @return Returns true if the tunnel is empty.
     */
    public final boolean isEmpty() {
        return getItems().isEmpty() && actors.isEmpty();
    }

    /**
     * Add an actor to the tunnel block.
     *
     * @param a Actor to be added.
     */
    public final void addActor(Actor a) {
        if (a != null) {
            actors.add(a);
        }
    }

    /**
     * Removes an actor from the tunnel block.
     *
     * @param a Actor to be removed.
     */
    public final void removeActor(Actor a) {
        actors.remove(a);
    }

    /**
     * Get a list of other actors in the tunnel. If no are found, an empty list
     * is returned.
     *
     * @param me The actor who is looking for other actors.
     * @return List of other actors in the tunnel.
     */
    public final List<Actor> getOtherActors(Actor me) {
        List<Actor> others = new ArrayList<>();
        for (Actor enemy : actors) {
            if (!enemy.equals(me)) {
                others.add(enemy);
            }
        }
        return others;
    }

    /**
     * Get all the actors in the tunnel.
     *
     * @return List of all actors in the tunnel.
     */
    public final List<Actor> getActors() {
        return actors;
    }

    @Override
    public String toString() {
        return Dictionary.getValue("TUNNEL", id);
    }
}
