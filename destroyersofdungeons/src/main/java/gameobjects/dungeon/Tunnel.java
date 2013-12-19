package gameobjects.dungeon;

import gameobjects.GameObject;
import gameobjects.actors.Actor;
import gameobjects.items.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The basic block from which the dungeon is build
 */
public final class Tunnel extends GameObject {

    private final List<Tunnel> nextTo = new ArrayList<>();
    private final List<Actor> actors = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();

    public Tunnel(int id) {
        super(id, "Tunnel (" + id + ")");
    }

    public void addItems(Collection<Item> items) {
        for (Item i : items) {
            addItem(i);
        }
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public List<Item> getItems() {
        return items;
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
