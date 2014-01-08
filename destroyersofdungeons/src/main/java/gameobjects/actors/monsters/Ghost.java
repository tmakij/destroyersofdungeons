package gameobjects.actors.monsters;

import constants.Constants;

final class Ghost extends Monster {

    public Ghost(int id) {
        super(id, Ghost.class);
    }

    @Override
    protected int getHitDamage(int amount) {
        int damage = super.getHitDamage(amount);
        damage *= Constants.GHOTS_DAMAGE_REDUCTION;
        return damage;
    }
}
