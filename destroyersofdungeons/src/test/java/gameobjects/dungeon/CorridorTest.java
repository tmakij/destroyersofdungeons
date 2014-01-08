package gameobjects.dungeon;

import gameobjects.items.Treasure;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class CorridorTest {

    private Corridor c;

    @Before
    public void setUp() {
        c = new Corridor(12, new Dungeon(), new Random(), false);
    }

    @Test
    public void testGetStartingBlock() {
        boolean gotEnd = false;
        Tunnel t;
        for (int i = 0; i < 100; i++) {
            t = c.getStartingBlock();
            if (t.getClass() == TunnelEnd.class) {
                gotEnd = true;
                break;
            }
        }
        assertFalse(gotEnd);
    }

    @Test
    public void testGetStartingBlockReturnDefault() {
        Tunnel t = c.startBlock;
        t.getNextTo().clear();
        t.addBlock(new TunnelEnd(Integer.MAX_VALUE));
        t.addItem(new Treasure());
        assertEquals(t, c.getStartingBlock());
    }
}
