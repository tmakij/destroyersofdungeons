package gameobjects.actors.monsters;

import gameobjects.actors.Actor;
import gameobjects.actors.races.Race;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.reflections.Reflections;

/**
 * The Dungeon monster. Each monster belongs to a race. Monsters are Actors.
 */
public abstract class Monster extends Actor {

    /**
     * The map of monsters and their races.
     */
    private static final Map<Type, Race> races = new HashMap<>();
    /**
     * The names of the monster classes. Used for reflection.
     */
    private static final List<String> names = new ArrayList<>();

    /**
     * Get a random monster from all the available monsters. Races must be
     * loaded before calling this methdod.
     *
     * @param rand The java.util.Random which is used to randomize the result.
     * @param id A unique id for the monster.
     * @return A random monster with the provided id.
     */
    public static Monster getRandomMonster(Random rand, int id) {
        Monster m;
        try {
            @SuppressWarnings("unchecked")
            Class<? extends Monster> cls = (Class<? extends Monster>) Class.forName(names.get(rand.nextInt(names.size())));
            m = cls.getConstructor(Integer.TYPE).newInstance(id);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            m = null;
        }
        return m;
    }

    /**
     * Loads the races usin reflection. This method is only to be called once.
     * Note: every monster must have a race defined in the races package. It
     * must be called "MonsterClassNameType". For example Minotaur must have a
     * race called MinotaurRace.
     */
    public static void loadRaces() {
        Reflections itemInstances = new Reflections("gameobjects.actors.monsters");
        Set<Class<? extends Monster>> racesInstancesClasses = itemInstances.getSubTypesOf(Monster.class);
        for (Class<? extends Monster> m : racesInstancesClasses) {
            try {
                String name = m.getName();
                name = name.replace("monster", "race");
                name += "Race";
                @SuppressWarnings("unchecked")
                Class<? extends Race> cl = (Class<? extends Race>) Class.forName(name);
                Constructor<? extends Race> ctor = cl.getConstructor();
                ctor.setAccessible(true);
                names.add(m.getName());
                races.put(m, ctor.newInstance());
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            }
        }
    }

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
}
