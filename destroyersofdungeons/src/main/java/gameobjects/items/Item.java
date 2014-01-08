package gameobjects.items;

import gameobjects.items.types.ItemType;
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
 * Items can provide different types of bonuses. Their effects are checked
 * before actions.
 */
public abstract class Item {

    private static final Map<Type, ItemType> itemTypesMap = new HashMap<>();
    private static final List<String> names = new ArrayList<>();

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

    private static String getReflectingName(String name) {
        String[] split = name.split("\\.");
        StringBuilder refname = new StringBuilder(name.length());
        for (int i = 0; i < split.length; i++) {
            if (i == split.length - 1) {
                refname.append("types.");
            }
            refname.append(split[i]);
            if (i != split.length - 1) {
                refname.append(".");
            }
        }
        refname.append("Type");
        return refname.toString();
    }

    public static void loadItemTypes() {
        Reflections itemInstances = new Reflections("gameobjects.items");
        Set<Class<? extends Item>> itemInstancesClasses = itemInstances.getSubTypesOf(Item.class);
        int ids = 0;
        for (Class<? extends Item> c : itemInstancesClasses) {
            try {
                String name = getReflectingName(c.getName());
                @SuppressWarnings("unchecked")
                Class<? extends ItemType> cl = (Class<? extends ItemType>) Class.forName(name);
                Constructor<? extends ItemType> ctor = cl.getConstructor(Integer.TYPE);
                ctor.setAccessible(true);
                names.add(name.replace("types.", "").replace("Type", ""));
                itemTypesMap.put(c, ctor.newInstance(ids));
                ids++;
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            }
        }
    }

    private final ItemType type;

    /**
     * Initialize a new item.
     *
     * @param type The type of the item.
     */
    protected Item(Type type) {
        this.type = itemTypesMap.get(type);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public final String toString() {
        return type.toString();
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
