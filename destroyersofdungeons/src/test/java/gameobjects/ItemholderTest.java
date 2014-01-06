package gameobjects;

import gameobjects.actors.Player;
import gameobjects.items.Item;
import gameobjects.items.WoodenShield;
import gameobjects.items.WoodenSword;
import java.util.Collection;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public final class ItemholderTest {

    private Itemholder h;

    private Collection<Item> getItems() {
        Collection<Item> items = new HashSet<>();
        items.add(new WoodenShield());
        items.add(new WoodenSword());
        return items;
    }

    @BeforeClass
    public static void onlyOnce() {
        Item.loadItemTypes();
    }

    @Before
    public void setUp() {
        h = new Player(0, "TEST_PLAYER", null);
    }

    @Test
    public void testDefaultItems() {
        assertEquals(true, h.getItems().isEmpty());
    }

    @Test
    public void testAddItem() {
        h.addItem(new WoodenShield());
        assertEquals(1, h.getItems().size());
    }

    @Test
    public void testAddItems() {
        h.addItems(getItems());
        assertEquals(2, h.getItems().size());
    }

    @Test
    public void testGetItems() {
        Collection<Item> items = getItems();
        h.addItems(items);
        assertEquals(items, h.getItems());
    }

    @Test
    public void testRemoveItem() {
        Item i = new WoodenSword();
        h.addItem(i);
        h.removeItem(i);
        assertEquals(true, h.getItems().isEmpty());
    }

    @Test
    public void testRemoveAllItems() {
        h.addItems(getItems());
        h.removeAllItems();
        assertEquals(true, h.getItems().isEmpty());
    }
}
