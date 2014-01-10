package gameobjects.actors.monsters;

import gameobjects.actors.Actor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import localisation.Dictionary;
import org.reflections.Reflections;

/**
 * The Dungeon monster. Each monster belongs to a race. Monsters are Actors.
 */
public abstract class Monster extends Actor {

    /**
     * The names of the monster classes. Used for reflection.
     */
    private static final List<String> names = new ArrayList<>();

    /**
     * Loads the monster class names.
     */
    static {
        Reflections itemInstances = new Reflections("gameobjects.actors.monsters");
        Set<Class<? extends Monster>> racesInstancesClasses = itemInstances.getSubTypesOf(Monster.class);
        for (Class<? extends Monster> m : racesInstancesClasses) {
            names.add(m.getName());
        }
    }

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
     * Creates a new Monster.
     *
     * @param id The unique id.
     */
    protected Monster(int id) {
        super(id);
    }

    @Override
    public final boolean isPlayerControlled() {
        return false;
    }

    @Override
    public final String toString() {
        return Dictionary.getValue(getClass().getSimpleName().toUpperCase(Locale.ENGLISH));
    }
}
