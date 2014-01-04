package gameobjects;

/**
 * Default class for gameobjects, Name and toString methods.
 */
public abstract class GameObject extends UniqueObject {

    private final String name;

    /**
     * Creates a new GameObject.
     *
     * @param id The unique id.
     * @param name The name of the object.
     */
    protected GameObject(int id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public final String toString() {
        return name;
    }
}
