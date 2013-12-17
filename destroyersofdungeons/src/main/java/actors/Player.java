package actors;

import items.Item;
import java.util.ArrayList;

/**
 * Player for the game.
 */
public final class Player extends Actor {

    private final String name;
    private final ArrayList<Item> items = new ArrayList<>();

    public Player(String name) {
        super();
        this.name = name;
    }

    public void addItem(Item i) {
        items.add(i);
    }

    @Override
    public String toString() {
        return name;
    }
}
