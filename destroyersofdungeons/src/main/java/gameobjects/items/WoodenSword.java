package gameobjects.items;

import constants.DoubleConstants;

/**
 * Woodensword item, increases attack strength slightly. Overrides onattack.
 */
public final class WoodenSword extends Item {

    /**
     * Creates the woodensword.
     *
     * @param id The unique
     */
    public WoodenSword(int id) {
        super(id, "WOODEN_SWORD");
    }

    @Override
    public int onAttack(int amount) {
        return (int) (amount * DoubleConstants.WOODEN_SWORD_ATTACK.getValue());
    }
}
