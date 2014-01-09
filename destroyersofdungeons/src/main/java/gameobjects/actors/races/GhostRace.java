package gameobjects.actors.races;

/**
 * The race of Ghosts.
 */
final class GhostRace extends Race {

    /**
     * Races are loaded using reflection, the constructor should never be
     * called.
     */
    public GhostRace() {
        super("GHOST");
    }
}
