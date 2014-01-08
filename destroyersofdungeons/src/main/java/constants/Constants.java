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
    public static final double WOODEN_SWORD_ATTACK = 1.25D;
    /**
     * The percentage of damage that is passed through a wooden shield.
     */
    public static final double WOODEN_SHIELD_DEFENSE = 0.75D;
    /**
     * The multiplier for the damage that is inflicted on attack action.
     */
    public static final double BATTLEACTION_ATTACK = 1.0D;
    /**
     * The multiplier for the damage that is inflicted on the attacker.
     */
    public static final double BATTLEACTION_DEFEND = 1.4D;
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
    /**
     * The minimum length of a corridor.
     */
    public static final int CORRIDOR_MIN_LENGTH = 6;
    /**
     * The maximum length of a corridor.
     */
    public static final int CORRIDOR_MAX_LENGTH = 12;
    /**
     * Minimum amount of players in the game.
     */
    public static final int MINIMUM_PLAYER_COUNT = 1;
    /**
     * Maximum amount of players in the game.
     */
    public static final int MAXIMUM_PLAYER_COUNT = 12;
    /**
     * The amount of health every player gains after everyone has played one
     * turn.
     */
    public static final int ACTOR_HEAL_RATE = 10;
    /**
     * The percentage that the Elixir Of Life increases the base healing rate.
     */
    public static final double ELIXIR_OF_LIFE_HEALTH_MODFIER = 1.5D;
    /**
     * The amount of health vampires get from the damage inflicted.
     */
    public static final double VAMPIRE_HEALT_ABSORB = 0.5D;
    /**
     * The percentage that the minotaur attack damage is increased.
     */
    public static final double MINOTAUR_ATTACK_STRENGHT = 1.5D;
    /**
     * The percentage that the spider defense damage is increased.
     */
    public static final double SPIDER_DEFENSE_STRENGHT = 1.5D;
    /**
     * The percentage of the damage that is left after a hit to a ghost.
     */
    public static final double GHOTS_DAMAGE_REDUCTION = 0.5D;
    /**
     * The chance that a tunnel has an item.
     */
    public static final double ITEM_SPAWN_CHANCE = 0.08D;
    /**
     * The chance that a tunnel has a monster.
     */
    public static final double MONSTER_SPAWN_CHANCE = 0.11D;
}
