package gameobjects.actors.monsters;

import constants.Constants;
import logic.BattleAction;

/**
 * The Vampire monster.
 */
final class Vampire extends Monster {

    /**
     * The monsters are created using reflection. You should not create them
     * manually for other than test purposes.
     */
    public Vampire(int id) {
        super(id, Vampire.class);
    }

    @Override
    protected int getAttack(BattleAction act) {
        int amount = super.getAttack(act);
        if (act.equals(BattleAction.ATTACK)) {
            heal((int) (amount * Constants.VAMPIRE_HEALT_ABSORB));
        }
        return amount;
    }
}
