package gameobjects.actors.monsters;

import constants.Constants;

final class Ghost extends Monster {

    public Ghost(int id) {
        super(id, Ghost.class);
    }

    @Override
    protected int getHitDamage(int amount) {
        amount = super.getHitDamage(amount);
        amount *= Constants.GHOTS_DAMAGE_REDUCTION;
        return amount;
    }
}
