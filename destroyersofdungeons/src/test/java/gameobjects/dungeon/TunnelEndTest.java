package gameobjects.dungeon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public final class TunnelEndTest {

    private Tunnel t;

    @Before
    public void setUp() {
        t = new TunnelEnd(0);
    }

    @Test
    public void testIsEndBlock() {
        assertTrue(t.isEndBlock());
    }

    @Test
    public void testToString() {
        assertEquals("TunnelDoor 0", t.toString());
    }

    @Test
    public void addNeighbours() {
        Tunnel t2 = new TunnelEnd(1);
        Tunnel t3 = new TunnelEnd(2);
        t2.addBlock(t3);
        t.addBlock(t2);
        assertEquals(2, t.getNextTo().size());
    }
}
