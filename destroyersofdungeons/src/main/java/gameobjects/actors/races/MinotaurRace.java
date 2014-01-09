package gameobjects.actors.races;

/**
 * The race of Minotaurs.
 */
final class MinotaurRace extends Race {

    /**
     * Races are loaded using reflection, the constructor should never be
     * called.
     */
    public MinotaurRace() {
        super("MINOTAUR");
    }
}
