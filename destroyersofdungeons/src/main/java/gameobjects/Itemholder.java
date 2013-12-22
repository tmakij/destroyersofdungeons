package gameobjects;

import gameobjects.items.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Baseclass for all itemowners, actors and tunnels.
 */
public abstract class Itemholder extends GameObject {

    private final List<Item> items = new ArrayList<>();

    public Itemholder(int id, String name) {
        super(id, name);
    }

    /**
     * Add a collection of items.
     *
     * @param items Collection of items.
     */
    public void addItems(Collection<Item> items) {
        for (Item i : items) {
            addItem(i);
        }
    }

    /**
     * Add an item to the tunnel.
     *
     * @param i Item to be added.
     */
    public void addItem(Item i) {
        items.add(i);
    }

    /**
     * Get the list of items in the tunnel.
     *
     * @return List of items in the tunnel.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Removes the item from the itemholder.
     *
     * @param i Item to remove.
     */
    public void removeItem(Item i) {
        items.remove(i);
    }

    /**
     * Removes all the items from the itemholder.
     */
    public void removeAllItems() {
        items.clear();
    }
}
