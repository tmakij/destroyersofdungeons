package gameobjects;

import gameobjects.actors.Player;
import gameobjects.items.Item;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public final class ItemholderTest {

    private static final Random rand = new Random();

    private Itemholder h;

    private Collection<Item> getItems() {
        Collection<Item> items = new HashSet<>();
        items.add(Item.getRandomItem(rand));
        do {
            items.add(Item.getRandomItem(rand));
        } while (items.size() < 2);
        return items;
    }

    @Before
    public void setUp() {
        h = new Player(0, "TEST_PLAYER", null);
    }

    @Test
    public void testDefaultItems() {
        assertTrue(h.getItems().isEmpty());
    }

    @Test
    public void testAddItem() {
        h.addItem(Item.getRandomItem(rand));
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
        Item i = Item.getRandomItem(rand);
        h.addItem(i);
        h.removeItem(i);
        assertTrue(h.getItems().isEmpty());
    }

    @Test
    public void testRemoveAllItems() {
        h.addItems(getItems());
        h.removeAllItems();
        assertTrue(h.getItems().isEmpty());
    }
}
