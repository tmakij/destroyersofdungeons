package gameobjects.items.types;

/**
 * Treasure ItemType.
 */
final class TreasureType extends ItemType {

    /**
     * ItemTypes are loaded using reflection, the constructor should never be
     * called.
     *
     * @param id The unique id.
     */
    public TreasureType(int id) {
        super(id, "TREASURE");
    }
}
