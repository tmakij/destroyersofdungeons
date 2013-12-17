package dungeon;

import actors.Actor;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The basic block from which the dungeon is build
 */
public final class Tunnel {

    private final ArrayList<Tunnel> nextTo = new ArrayList<>();
    private final HashSet<Actor> actors = new HashSet<>();
    private static int ids = 0;
    private final int id;

    public Tunnel() {
        this.id = ids++;
    }

    /**
     * Constructor for testing hashcode.
     * @param id custom tunnel id.
     */
    public Tunnel(int id) {
        this.id = id;
    }

    public void addBlock(Tunnel t) {
        nextTo.add(t);
    }

    public ArrayList<Tunnel> getNextTo() {
        return nextTo;
    }

    public void addActor(Actor a) {
        actors.add(a);
    }

    public void removeActor(Actor a) {
        actors.remove(a);
    }

    /**
     * Get a string of other persons on the same tunnelblock.
     *
     * @param me The actor on the block.
     * @return List of other persons on the same tunnelblock.
     */
    public String getActors(Actor me) {
        String ret;
        if (actors.size() == 1) {
            ret = "You see noone else on this tunnel block";
        } else {
            ret = actors.size() == 2 ? "You see another person on the block:\n" : "You see other persons on the block:\n";
            for (Actor a : actors) {
                if (!a.equals(me)) {
                    ret += a + "\n";
                }
            }
        }
        return ret;
    }

    public HashSet<Actor> getActorSet() {
        return actors;
    }

    @Override
    public String toString() {
        return "Tunnel (" + id + ")";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tunnel other = (Tunnel) obj;
        return this.id == other.id;
    }
}
