package gameobjects.items;

/**
 * Woodenshield item, decrease damage taken. Overrides onDamageReceived.
 */
public final class WoodenShield extends Item {

    /**
     * The percentage of damage that is passed through.
     */
    public static final double DEFENCE = 0.9D;

    /**
     * Creates the woodenshield.
     *
     * @param id The unique
     */
    public WoodenShield(int id) {
        super(id, "WOODEN_SHIELD");
    }

    @Override
    public int onDamageReceived(int amount) {
        return (int) (amount * DEFENCE);
    }
}
