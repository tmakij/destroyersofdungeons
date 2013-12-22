package gameobjects.items;

/**
 * Woodensword item, increases attack strength slightly. Overrides on attack.
 */
public final class WoodenSword extends Item {

    /**
     * The percentage of damage that is inflicted on attack.
     */
    public static final double ATTACK = 1.1D;

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
        return (int) (amount * ATTACK);
    }
}
