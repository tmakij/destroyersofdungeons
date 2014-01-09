package gameobjects.items.types;

/**
 * WoodenShield ItemType.
 */
final class WoodenShieldType extends ItemType {

    /**
     * ItemTypes are loaded using reflection, the constructor should never be
     * called.
     *
     * @param id The unique id.
     */
    public WoodenShieldType(int id) {
        super(id, "WOODEN_SHIELD");
    }
}
