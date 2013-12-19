package gameobjects.items;

/**
 * Wooden sword item, increases attack strength slightly.
 */
public final class WoodenSword extends Item {

    public static final double ATTACK = 1.1D;

    public WoodenSword(int id) {
        super(id, "WoodenSword");
    }

    @Override
    public int onAttack(int amount) {
        return (int) (amount * ATTACK);
    }
}
