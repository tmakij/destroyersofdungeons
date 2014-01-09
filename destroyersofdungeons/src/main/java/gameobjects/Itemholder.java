package gameobjects;

import gameobjects.items.Item;
import java.util.Collection;
import java.util.HashSet;

/**
 * Baseclass for all itemowners; actors and tunnels. Has the item collection.
 */
public abstract class Itemholder extends GameObject {

    /**
     * The items owned by the item holder.
     */
    private final Collection<Item> items = new HashSet<>();

    /**
     * Creates a new ItemHolder.
     *
     * @param id The unique id.
     * @param name The name of the object.
     */
    protected Itemholder(int id, String name) {
        super(id, name);
    }

    /**
     * Add a collection of items.
     *
     * @param items Collection of items.
     */
    public final void addItems(Collection<Item> items) {
        for (Item i : items) {
            addItem(i);
        }
    }

    /**
     * Add an item to the inventory of the itemholder.
     *
     * @param i Item to be added.
     */
    public final void addItem(Item i) {
        if (i != null) {
            items.add(i);
        }
    }

    /**
     * Get the collection of items in the inventory of the itemholder.
     *
     * @return Collection of items in the tunnel.
     */
    public final Collection<Item> getItems() {
        return items;
    }

    /**
     * Removes the item from the itemholder.
     *
     * @param i Item to remove.
     */
    public final void removeItem(Item i) {
        if (i != null) {
            items.remove(i);
        }
    }

    /**
     * Removes all the items from the itemholder.
     */
    public final void removeAllItems() {
        items.clear();
    }
}
