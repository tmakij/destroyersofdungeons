package gameobjects.actors.monsters;

import constants.Constants;
import logic.BattleAction;

final class Vampire extends Monster {
    
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
