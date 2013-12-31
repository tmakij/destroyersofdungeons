package logic;

import constants.DoubleConstants;

/**
 * Defines possible actions that one can take in a battle.
 */
public enum BattleAction {

    ATTACK(DoubleConstants.BATTLEACTION_ATTACK.getValue()),
    DEFEND(DoubleConstants.BATTLEACTION_DEFEND.getValue()),
    CAST_SPELL(DoubleConstants.BATTLEACTION_CASTSPELL.getValue()),
    DO_NOTHING(1.0D),
    FLEE(1.0D);

    private final double value;

    private BattleAction(double value) {
        this.value = value;
    }

    public double actModifier() {
        return value;
    }
}
