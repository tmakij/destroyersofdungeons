package gameobjects.actors.monsters;

import gameobjects.actors.races.Race;
import gameobjects.actors.Actor;
import java.lang.reflect.Constructor;
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

    private static final Map<Type, Race> races = new HashMap<>();
    private static final List<String> names = new ArrayList<>();

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

    @SuppressWarnings("UseSpecificCatch")
    public static Monster getRandomMonster(Random rand, int id) {
        Monster m;
        try {
            Class<? extends Monster> cls = (Class<? extends Monster>) Class.forName(names.get(rand.nextInt(names.size())));
            m = cls.getConstructor(Integer.TYPE).newInstance(id);
        } catch (Exception ex) {
            m = null;
        }
        return m;
    }

    /**
     * Loads the races. This method is only to be called once.
     */
    @SuppressWarnings("UseSpecificCatch")
    public static void loadRaces() {
        Reflections itemInstances = new Reflections("gameobjects.actors.monsters");
        Set<Class<? extends Monster>> racesInstancesClasses = itemInstances.getSubTypesOf(Monster.class);
        for (Class<? extends Monster> m : racesInstancesClasses) {
            try {
                String name = m.getName();
                name = name.replace("monster", "race");
                name += "Race";
                Class cl = Class.forName(name);
                Constructor ctor = cl.getConstructor();
                ctor.setAccessible(true);
                names.add(m.getName());
                races.put(m, (Race) ctor.newInstance());
            } catch (Exception ex) {
            }
        }
    }
}
