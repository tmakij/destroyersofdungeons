package gameobjects.actors.races;

/**
 * Race contains the values, which monster of the same race have. Only one
 * instance of a race must be created.
 */
public abstract class Race {

    private final String name;

    /**
     * Creates a new Race.
     *
     * @param name The name of the Race.
     */
    protected Race(String name) {
        this.name = name;
    }

    @Override
    public final String toString() {
        return name;
    }
}
