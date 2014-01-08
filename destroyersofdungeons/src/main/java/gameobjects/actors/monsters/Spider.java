package gameobjects.actors.monsters;

import constants.Constants;
import logic.BattleAction;

final class Spider extends Monster {

    public Spider(int id) {
        super(id, Spider.class);
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
