package gameobjects.dungeon;

import gameobjects.Itemholder;
import gameobjects.actors.Actor;
import gameobjects.items.WoodenShield;
import java.util.ArrayList;
import java.util.List;

/**
 * The basic block from which the dungeon is build. Can contain actors and
 * items. Knows the blocks that are next to it.
 */
public final class Tunnel extends Itemholder {

    private final List<Tunnel> nextTo = new ArrayList<>();
    private final List<Actor> actors = new ArrayList<>();

    /**
     * Create a new tunnel with a unnique id.
     *
     * @param id Unique id.
     */
    public Tunnel(int id) {
        super(id, "Tunnel (" + id + ")");
        addItem(new WoodenShield(32));
    }

    /**
     * Adds a new block to the list of known neighbours.
     *
     * @param t The tunnel block to be added.
     */
    public void addBlock(Tunnel t) {
        nextTo.add(t);
    }

    /**
     * Get the list of neighbouring tunnel blocks.
     *
     * @return List of neighbouring tunnel blocks
     */
    public List<Tunnel> getNextTo() {
        return nextTo;
    }

    /**
     * Add an actor to the tunnel block.
     *
     * @param a Actor to be added.
     */
    public void addActor(Actor a) {
        actors.add(a);
    }

    /**
     * Removes an actor from the tunnel block.
     *
     * @param a Actor to be removed.
     */
    public void removeActor(Actor a) {
        actors.remove(a);
    }

    /**
     * Get a list of other actors in the tunnel. If no are found, an empty list
     * is returned.
     *
     * @param me The actor who is looking for other actors.
     * @return List of other actors in the tunnel.
     */
    public List<Actor> getOtherActors(Actor me) {
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
    public List<Actor> getActorSet() {
        return actors;
    }
}
