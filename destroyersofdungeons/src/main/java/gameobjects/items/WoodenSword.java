package gameobjects.items;

import constants.Constants;

/**
 * Woodensword item, increases attack strength slightly. Overrides onattack.
 */
final class WoodenSword extends Item {

    /**
     * Items are created using reflection, the constructor should not be called
     * for other than testing purposes.
     */
    public WoodenSword() {
    }

    @Override
    public int onAttack(int amount) {
        return (int) (amount * Constants.WOODEN_SWORD_ATTACK);
    }
}
