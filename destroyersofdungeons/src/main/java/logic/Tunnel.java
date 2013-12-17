package logic;

import actors.Monster;
import java.util.ArrayList;

/**
 * The basic block from which the dungeon is build
 */
public final class Tunnel {

    private final ArrayList<Tunnel> nextTo = new ArrayList<>();
    private Monster monster;
    private static int ids = 4322;
    private final int id;

    public Tunnel() {
        id = ids++;
    }

    public void addBlock(Tunnel t) {
        nextTo.add(t);
    }

    public ArrayList<Tunnel> getNextTo() {
        return nextTo;
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
