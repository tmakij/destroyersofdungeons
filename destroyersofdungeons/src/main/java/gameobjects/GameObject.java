package gameobjects;

/**
 * Default class for gameobjects, holds ID, Name, hashcode, equals and toString
 * methods.
 */
public abstract class GameObject {

    private final int id;
    private final String name;

    public GameObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public final String toString() {
        return name;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameObject other = (GameObject) obj;
        return this.id == other.id;
    }
}
