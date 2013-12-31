package gameobjects.items;

import constants.DoubleConstants;

/**
 * Woodenshield item, decrease damage taken. Overrides onDamageReceived.
 */
public final class WoodenShield extends Item {

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
        return (int) (amount * DoubleConstants.WOODEN_SHIELD_DEFENSE.getValue());
    }
}
