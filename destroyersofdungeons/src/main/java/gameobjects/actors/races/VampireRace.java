package gameobjects.actors.races;

/**
 * The race of Vampires.
 */
final class VampireRace extends Race {

    /**
     * Races are loaded using reflection, the constructor should never be
     * called.
     */
    public VampireRace() {
        super("VAMPIRE");
    }
}
