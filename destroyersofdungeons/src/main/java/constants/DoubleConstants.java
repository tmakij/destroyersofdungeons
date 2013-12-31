package constants;

/**
 * Contains all the double constants of the game.
 */
public enum DoubleConstants {

    /**
     * The percentage of damage that is inflicted on attack by a wooden sword.
     */
    WOODEN_SWORD_ATTACK(1.1D),
    /**
     * The percentage of damage that is passed through.
     */
    WOODEN_SHIELD_DEFENSE(0.9D),
    BATTLEACTION_ATTACK(1.0D),
    BATTLEACTION_DEFEND(1.2D),
    BATTLEACTION_CASTSPELL(0.8D);

    private final double value;

    private DoubleConstants(double value) {
        this.value = value;
    }

    /**
     * Get the constan value.
     *
     * @return The double value.
     */
    public double getValue() {
        return value;
    }
}
