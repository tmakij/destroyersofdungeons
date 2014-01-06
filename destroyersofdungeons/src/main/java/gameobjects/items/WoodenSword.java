package gameobjects.items;

import constants.Constants;

/**
 * Woodensword item, increases attack strength slightly. Overrides onattack.
 */
public final class WoodenSword extends Item {

    /**
     * Creates a woodensword.
     */
    public WoodenSword() {
        super(WoodenSword.class);
    }

    @Override
    public int onAttack(int amount) {
        return (int) (amount * Constants.WOODEN_SWORD_ATTACK);
    }
}
