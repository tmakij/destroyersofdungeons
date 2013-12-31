package gameobjects;

import gameobjects.actors.Player;
import gameobjects.items.Item;
import gameobjects.items.WoodenShield;
import gameobjects.items.WoodenSword;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class ItemholderTest {

    private Itemholder h;

    private Collection<Item> getItems() {
        Collection<Item> items = new ArrayList<>();
        items.add(new WoodenShield(0));
        items.add(new WoodenSword(0));
        return items;
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
        h.addItem(new WoodenShield(0));
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
        Item i = new WoodenSword(0);
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
