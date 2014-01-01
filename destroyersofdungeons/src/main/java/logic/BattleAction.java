package logic;

import constants.Constants;

/**
 * Defines possible actions that one can take in a battle.
 */
public enum BattleAction {

    ATTACK(Constants.BATTLEACTION_ATTACK),
    DEFEND(Constants.BATTLEACTION_DEFEND),
    CAST_SPELL(Constants.BATTLEACTION_CASTSPELL),
    DO_NOTHING(1.0D),
    FLEE(1.0D);

    private final double value;

    private BattleAction(double value) {
        this.value = value;
    }

    /**
     * Get the value associated with the BattleAct.
     *
     * @return The value.
     */
    public double actModifier() {
        return value;
    }
}
