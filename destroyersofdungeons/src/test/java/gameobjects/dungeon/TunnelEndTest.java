package gameobjects.dungeon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public final class TunnelEndTest {

    private Tunnel t = new TunnelEnd(0);

    @Test
    public void testIsEndBlock() {
        assertTrue(t.isEndBlock());
    }

    @Test
    public void testToString() {
        assertEquals("TunnelDoor 0", t.toString());
    }
}
