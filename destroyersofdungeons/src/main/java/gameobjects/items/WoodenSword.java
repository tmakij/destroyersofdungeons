package gameobjects.items;

import constants.Constants;

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
        return (int) (amount * Constants.WOODEN_SWORD_ATTACK);
    }
}
