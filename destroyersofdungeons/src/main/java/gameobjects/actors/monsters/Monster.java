package gameobjects.actors.monsters;

import gameobjects.actors.races.MinotaurRace;
import gameobjects.actors.races.Race;
import gameobjects.actors.Actor;
import gameobjects.actors.races.SpiderRace;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * The Dungeon monster. Each monster belongs to a race. Monsters are Actors.
 */
public abstract class Monster extends Actor {

    private static final Map<Type, Race> races = new HashMap<>();

    /**
     * Creates a new Monster.
     *
     * @param id The unique id.
     * @param race The race of the monster.
     */
    protected Monster(int id, Type race) {
        super(id, races.get(race).toString());
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
        races.put(Spider.class, new SpiderRace());
    }
}
