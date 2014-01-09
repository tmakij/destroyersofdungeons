package gameobjects.items.types;

import gameobjects.GameObject;
import localisation.Dictionary;

/**
 * Every item has a type. All items of the same type share their name, and
 * possibly images and such later.
 */
public abstract class ItemType extends GameObject {

    /**
     * Creates a new ItemType.
     *
     * @param id The unique id.
     * @param name The Dictionary key for the name.
     */
    protected ItemType(int id, String name) {
        super(id, Dictionary.getValue(name));
    }
}
