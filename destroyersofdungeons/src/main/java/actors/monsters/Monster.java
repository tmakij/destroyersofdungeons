package actors.monsters;

import actors.races.MinotaurRace;
import actors.races.Race;
import actors.Actor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * The Dungeon monster. Each monster belongs to a race.
 */
public abstract class Monster extends Actor {

    protected static final Map<Type, Race> races = new HashMap<>();
    private final Race race;

    public Monster(int id, Race race) {
        super(id);
        this.race = race;
    }

    /**
     * Loads the races. This method is only to be called once.
     */
    public static void loadRaces() {
        races.put(Minotaur.class, new MinotaurRace());
    }

    @Override
    public final String toString() {
        return race.toString();
    }
}
