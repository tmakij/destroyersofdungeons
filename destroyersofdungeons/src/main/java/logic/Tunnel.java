package logic;

import actors.Monster;
import java.util.ArrayList;

/**
 * The basic block from which the dungeon is build
 */
public final class Tunnel {

    private final ArrayList<Tunnel> nextTo = new ArrayList<>();
    private Monster monster;
    private static int ids = 0;
    private final int id;

    public Tunnel() {
        id = ids++;
    }

    public final void addBlock(Tunnel t) {
        nextTo.add(t);
    }

    public final ArrayList<Tunnel> getNextTo() {
        return nextTo;
    }

    @Override
    public final String toString() {
        return "Tunnel (" + id + ")";
    }
}
