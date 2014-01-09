package gameobjects.actors.races;

import localisation.Dictionary;

/**
 * Race contains the values, which monster of the same race have. Only one
 * instance of a race must be created.
 */
public abstract class Race {

    /**
     * The name of the race.
     */
    private final String name;

    /**
     * Creates a new Race.
     *
     * @param name The key for the name of the race for Dictionary.
     */
    protected Race(String name) {
        this.name = Dictionary.getValue(name);
    }

    @Override
    public final String toString() {
        return name;
    }
}
