package constants;

/**
 * Contains all the constants of the game.
 */
public enum Constants {

    ;
    /**
     * The amount of tunnels that is kept on the memory. The last one is the block where the actor retreats.
     */
    public static final int TUNNEL_HISTORY = 3;
    /**
     * Max health of an actor. Also starting health.
     */
    public static final int ACTOR_BASE_HEALTH = 100;
    /**
     * Base attack of an actor, before any damage modifiers are applied.
     */
    public static final int ACTOR_BASE_ATTACK = 25;
    /**
     * The percentage of damage that is inflicted on attack by a wooden sword.
     */
    public static final double WOODEN_SWORD_ATTACK = 1.1D;
    /**
     * The percentage of damage that is passed through a wooden shield.
     */
    public static final double WOODEN_SHIELD_DEFENSE = 0.9D;
    /**
     * The multiplier for the damage that is inflicted on attack action.
     */
    public static final double BATTLEACTION_ATTACK = 1.0D;
    /**
     * The multiplier for the damage that is inflicted on the attacker.
     */
    public static final double BATTLEACTION_DEFEND = 1.2D;
    /**
     * The multiplier for the damage that is inflicted when a spell is casted.
     */
    public static final double BATTLEACTION_CASTSPELL = 0.8D;

}
