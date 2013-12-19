package logic;

/**
 * Defines possible actions that one can take in a battle.
 */
public enum BattleAction {

    ATTACK(1.0D), FLEE(1.0D), DO_NOTHING(1.0D), CAST_SPELL(0.8D), DEFEND(1.2D);

    private final double value;

    private BattleAction(double value) {
        this.value = value;
    }

    public double actModifier() {
        return value;
    }
}
