package gameobjects.actors.monsters;

import constants.Constants;
import logic.BattleAction;

/**
 * Minotaur monster.
 */
final class Minotaur extends Monster {

    public Minotaur(int id) {
        super(id, Minotaur.class);
    }

    @Override
    protected int getAttack(BattleAction act) {
        int amount = super.getAttack(act);
        if (act.equals(BattleAction.ATTACK)) {
            amount *= Constants.MINOTAUR_ATTACK_STRENGHT;
        }
        return amount;
    }
}
