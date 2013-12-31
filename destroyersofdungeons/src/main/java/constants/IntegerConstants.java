package constants;

/**
 * Contains all the integer constants of the game.
 */
public enum IntegerConstants {

    TUNNEL_HISTORY(3), ACTOR_BASE_HEALTH(100), ACTOR_BASE_ATTACK(25);

    private final int value;

    private IntegerConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
