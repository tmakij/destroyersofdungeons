package gameobjects.items;

import constants.Constants;

/**
 * Woodenshield item, decrease damage taken. Overrides onDamageReceived.
 */
final class WoodenShield extends Item {

    /**
     * Creates a woodenshield.
     */
    public WoodenShield() {
        super(WoodenShield.class);
    }

    @Override
    public int onDamageReceived(int amount) {
        return (int) (amount * Constants.WOODEN_SHIELD_DEFENSE);
    }
}
