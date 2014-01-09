package logic;

import constants.Constants;

/**
 * Defines possible actions that one can take in a battle.
 */
public enum BattleAction {

    /**
     * The Attack Action. Deals normal damage.
     */
    ATTACK(Constants.BATTLEACTION_ATTACK),
    /**
     * The Defend Action. Deals more damage on defend.
     */
    DEFEND(Constants.BATTLEACTION_DEFEND),
    /**
     * The CastSpell Action. Deals less damage that attack, but ignores defend
     * action.
     */
    CAST_SPELL(Constants.BATTLEACTION_CASTSPELL),
    /**
     * Do nothing. If you think the battle is too easy...
     */
    DO_NOTHING(1.0D),
    /**
     * Retreats from the battle.
     */
    FLEE(1.0D);

    /**
     * The value attached to the BattleAction.
     */
    private final double value;

    /**
     * Create the BattleAction.
     *
     * @param value To be attached to the BattleAction.
     */
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
