package gameobjects.items;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import localisation.Dictionary;
import org.reflections.Reflections;

/**
 * Items can provide different types of bonuses. Their effects are checked
 * before actions.
 */
public abstract class Item {

    /**
     * The names of items. Used for reflection.
     */
    private static final List<String> names = new ArrayList<>();

    /**
     * Loads the class names.
     */
    static {
        Reflections itemInstances = new Reflections("gameobjects.items");
        Set<Class<? extends Item>> itemInstancesClasses = itemInstances.getSubTypesOf(Item.class);
        for (Class<? extends Item> c : itemInstancesClasses) {
            names.add(c.getName());
        }
    }

    /**
     * Get a random item from the available items. The item cannot return true
     * with winsGame().
     *
     * @param rand The random used for randomisation.
     * @return The random item.
     */
    public static Item getRandomItem(Random rand) {
        Item i;
        try {
            do {
                @SuppressWarnings("unchecked")
                Class<? extends Item> cls = (Class<? extends Item>) Class.forName(names.get(rand.nextInt(names.size())));
                i = cls.getConstructor().newInstance();
            } while (i.winsGame());
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            i = null;
        }
        return i;
    }

    /**
     * Create a new Item.
     */
    protected Item() {
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public final String toString() {
        return Dictionary.getValue(getClass().getSimpleName().toUpperCase(Locale.ENGLISH));
    }

    @Override
    public final boolean equals(Object obj) {
        return obj != null && obj.getClass() == getClass();
    }

    /**
     * If the item changes attack strength, it is modified by this method
     *
     * @param amount Original attack strengh.
     * @return New attack strength.
     */
    public int onAttack(int amount) {
        return amount;
    }

    /**
     * If the item changes the amount of damage received, it is modified by this
     * method
     *
     * @param amount Original damage.
     * @return Modified damage.
     */
    public int onDamageReceived(int amount) {
        return amount;
    }

    /**
     * Check if this item wins the game.
     *
     * @return Does this item win the game.
     */
    public boolean winsGame() {
        return false;
    }

    /**
     * If the item modifies the amount of health the owner receives on heal, it
     * is modified by this method.
     *
     * @param amount The original health to be gained.
     * @return The modified health to be gained.
     */
    public int onHeal(int amount) {
        return amount;
    }
}
