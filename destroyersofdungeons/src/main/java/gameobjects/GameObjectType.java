package gameobjects;

import localisation.Dictionary;

/**
 * GameObjectType contains the values, which objects of the same type have. Only
 * one instance of a type must be created.
 */
public final class GameObjectType {

    /**
     * The name of the type.
     */
    private final String name;

    /**
     * Creates a new GameObjectType.
     *
     * @param name The key for the name of the type for Dictionary.
     */
    public GameObjectType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return Dictionary.getValue(name);
    }
}
