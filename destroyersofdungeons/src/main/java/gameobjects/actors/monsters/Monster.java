package gameobjects.actors.monsters;

import gameobjects.actors.races.MinotaurRace;
import gameobjects.actors.races.Race;
import gameobjects.actors.Actor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * The Dungeon monster. Each monster belongs to a race.
 */
public abstract class Monster extends Actor {

    private static final Map<Type, Race> races = new HashMap<>();
    private final Race race;

    public Monster(int id, Type race) {
        super(id, races.get(race).toString());
        this.race = races.get(race);
    }

    @Override
    public final boolean isPlayerControlled() {
        return false;
    }

    /**
     * Loads the races. This method is only to be called once.
     */
    public static void loadRaces() {
        races.put(Minotaur.class, new MinotaurRace());
    }
}
