package gameobjects.actors.monsters;

import constants.Constants;

/**
 * The Ghost monster.
 */
final class Ghost extends Monster {

    /**
     * The monsters are created using reflection. You should not create them
     * manually for other than test purposes.
     */
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
