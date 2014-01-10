package gameobjects.items;

import constants.Constants;

/**
 * Woodenshield item, decrease damage taken. Overrides onDamageReceived.
 */
final class WoodenShield extends Item {

    /**
     * Items are created using reflection, the constructor should not be called
     * for other than testing purposes.
     */
    public WoodenShield() {
    }

    @Override
    public int onDamageReceived(int amount) {
        return (int) (amount * Constants.WOODEN_SHIELD_DEFENSE);
    }
}
