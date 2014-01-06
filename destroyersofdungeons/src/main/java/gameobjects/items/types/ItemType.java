package gameobjects.items.types;

import gameobjects.GameObject;
import localisation.Dictionary;

public abstract class ItemType extends GameObject {

    public ItemType(int id, String name) {
        super(id, Dictionary.getValue(name));
    }
}
