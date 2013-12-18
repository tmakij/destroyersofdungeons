package items;

/**
 *
 */
public final class WoodenShield extends Item {

    public static final double DEFENCE = 0.9D;

    public WoodenShield(int id) {
        super(id, "Wooden Shield");
    }

    @Override
    public int onDamageReceived(int amount) {
        return (int) (amount * DEFENCE);
    }
}
