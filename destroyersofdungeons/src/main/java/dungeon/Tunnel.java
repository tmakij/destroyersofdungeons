package dungeon;

import actors.Actor;
import java.util.ArrayList;
import java.util.List;

/**
 * The basic block from which the dungeon is build
 */
public final class Tunnel {

    private final List<Tunnel> nextTo = new ArrayList<>();
    private final List<Actor> actors = new ArrayList<>();
    private final int id;

    /**
     * @param id Custom tunnel id, used for equals and hashcode.
     */
    public Tunnel(int id) {
        this.id = id++;
    }

    public void addBlock(Tunnel t) {
        nextTo.add(t);
    }

    public List<Tunnel> getNextTo() {
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
            ret = "You see nobody else on this tunnel block";
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

    public String getHostilityOptions(Actor a) {
        String actions = "";
        for (int i = 0; i < actors.size(); i++) {
            Actor enemy = actors.get(i);
            if (!enemy.equals(a)) {
                actions += "[" + i + "] " + enemy;
            }
        }
        return actions;
    }

    public List<Actor> getActorSet() {
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
