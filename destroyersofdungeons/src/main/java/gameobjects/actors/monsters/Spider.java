package gameobjects.actors.monsters;

import constants.Constants;
import logic.BattleAction;

/**
 * The Spider monster.
 */
final class Spider extends Monster {

    /**
     * The monsters are created using reflection. You should not create them
     * manually for other than test purposes.
     */
    public Spider(int id) {
        super(id);
    }

    @Override
    protected int getAttack(BattleAction act) {
        int amount = super.getAttack(act);
        if (act == BattleAction.DEFEND) {
            amount *= Constants.SPIDER_DEFENSE_STRENGHT;
        }
        return amount;
    }
}
