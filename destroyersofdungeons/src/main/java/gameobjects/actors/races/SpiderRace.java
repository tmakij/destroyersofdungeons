package gameobjects.actors.races;

/**
 * The race of Spiders.
 */
final class SpiderRace extends Race {

    /**
     * Races are loaded using reflection, the constructor should never be
     * called.
     */
    public SpiderRace() {
        super("SPIDER");
    }
}
