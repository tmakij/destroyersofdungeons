package gameobjects.items.types;

/**
 * WoodenSword ItemType.
 */
final class WoodenSwordType extends ItemType {

    /**
     * ItemTypes are loaded using reflection, the constructor should never be
     * called.
     *
     * @param id The unique id.
     */
    public WoodenSwordType(int id) {
        super(id, "WOODEN_SWORD");
    }
}
