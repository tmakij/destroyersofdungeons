package gameobjects;

/**
 * Class for objects with id, holds hashcode and equals methods.
 */
public abstract class UniqueObject {

    private final int id;

    protected UniqueObject(int id) {
        this.id = id;
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
        final UniqueObject other = (UniqueObject) obj;
        return this.id == other.id;
    }
}
