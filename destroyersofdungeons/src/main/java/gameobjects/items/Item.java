package gameobjects.items;

import gameobjects.items.types.ItemType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;

/**
 * Items can provide different types of bonuses. Their effects are checked
 * before actions.
 */
public abstract class Item {

    private final ItemType type;
    private static final Map<Type, ItemType> itemTypesMap = new HashMap<>();

    /**
     * Initialize a new item.
     *
     * @param type The type of the item.
     */
    protected Item(Type type) {
        this.type = itemTypesMap.get(type);
        if (this.type == null) {
            System.out.println("ERROR " + type);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public static void loadItemTypes() {
        Reflections itemInstances = new Reflections("gameobjects.items");
        Set<Class<? extends Item>> itemInstancesClasses = itemInstances.getSubTypesOf(Item.class);
        int ids = 0;
        for (Class<? extends Item> c : itemInstancesClasses) {
            try {
                String name = c.getName();
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
                Class cl = Class.forName(name);
                Constructor ctor = cl.getConstructor(Integer.TYPE);
                itemTypesMap.put(c, (ItemType) ctor.newInstance(++ids));
            } catch (Exception ex) {
                System.out.println(ex);
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
}
