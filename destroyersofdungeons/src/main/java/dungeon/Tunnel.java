package dungeon;

import GameObject.GameObject;
import actors.Actor;
import java.util.ArrayList;
import java.util.List;

/**
 * The basic block from which the dungeon is build
 */
public final class Tunnel extends GameObject {

    private final List<Tunnel> nextTo = new ArrayList<>();
    private final List<Actor> actors = new ArrayList<>();

    /**
     * @param id Custom tunnel id, used for equals and hashcode.
     */
    public Tunnel(int id) {
        super(id, "Tunnel (" + id + ")");
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

    public List<Actor> getOtherActors(Actor me) {
        List<Actor> others = new ArrayList<>();
        for (Actor enemy : actors) {
            if (!enemy.equals(me)) {
                others.add(enemy);
            }
        }
        return others;
    }

    public List<Actor> getActorSet() {
        return actors;
    }
}
