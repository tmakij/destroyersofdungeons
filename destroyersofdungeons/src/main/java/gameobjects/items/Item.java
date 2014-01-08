package gameobjects.items;

import gameobjects.items.types.ItemType;
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
 * Items can provide different types of bonuses. Their effects are checked
 * before actions.
 */
public abstract class Item {

    private final ItemType type;
    private static final Map<Type, ItemType> itemTypesMap = new HashMap<>();
    private static final List<String> names = new ArrayList<>();

    /**
     * Initialize a new item.
     *
     * @param type The type of the item.
     */
    protected Item(Type type) {
        this.type = itemTypesMap.get(type);
    }

    public static Item getRandomItem(Random rand) {
        Item i;
        try {
            do {
                Class<? extends Item> cls = (Class<? extends Item>) Class.forName(names.get(rand.nextInt(names.size())));
                i = cls.getConstructor().newInstance();
            } while (i.winsGame());
        } catch (Exception ex) {
            i = null;
        }
        return i;
    }

    private static String getReflectingName(String name) {
        String[] split = name.split("\\.");
        name = "";
        for (int i = 0; i < split.length; i++) {
            if (i == split.length - 1) {
                name += "types.";
            }
            name += split[i];
            if (i != split.length - 1) {
                name += ".";
            }
        }
        name += "Type";
        return name;
    }

    @SuppressWarnings("UseSpecificCatch")
    public static void loadItemTypes() {
        Reflections itemInstances = new Reflections("gameobjects.items");
        Set<Class<? extends Item>> itemInstancesClasses = itemInstances.getSubTypesOf(Item.class);
        int ids = 0;
        for (Class<? extends Item> c : itemInstancesClasses) {
            try {
                String name = getReflectingName(c.getName());
                Class cl = Class.forName(name);
                Constructor ctor = cl.getConstructor(Integer.TYPE);
                names.add(name.replace("types.", "").replace("Type", ""));
                itemTypesMap.put(c, (ItemType) ctor.newInstance(++ids));
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public final int hashCode() {
        return type.hashCode();
    }

    @Override
    public final String toString() {
        return type.toString();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        final Item i = (Item) obj;
        return type.equals(i.type);
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
