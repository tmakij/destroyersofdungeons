package logic;

/**
 * Defines possible actions that one can take in a battle.
 */
public enum BattleAction {

    ATTACK, FLEE, DO_NOTHING, CAST_SPELL, DEFEND;

    public double actModifier() {
        switch (this) {
            case CAST_SPELL:
                return 0.8D;
            case DEFEND:
                return 1.2D;
            case ATTACK:
            case FLEE:
            case DO_NOTHING:
            default:
                return 1.0D;

        }
    }
}
