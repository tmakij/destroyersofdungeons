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
     * Minimum amount of corridors in the dungeon.
     */
    public static final int DUNGEON_MIN_CORRIDORS = 6;
    /**
     * Maximum amount of corridors in the dungeon.
     */
    public static final int DUNGEON_MAX_CORRIDORS = 12;
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
    /**
     * The maximum length of player's name.
     */
    public static final int PLAYER_NAME_MAX_LENGHT = 32;
    /**
     * The minimum length of player's name.
     */
    public static final int PLAYER_NAME_MIN_LENGHT = 1;
    /**
     * Allowed characters in the player's name.
     */
    public static final String ALLOWED_CHARACTERS = "a-zA-Z0-9 _";
}
