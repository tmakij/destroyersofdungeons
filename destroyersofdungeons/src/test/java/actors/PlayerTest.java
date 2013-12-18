package actors;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PlayerTest {

    private Player p;

    @Before
    public void setUp() {
        p = new Player(0, "TEST_PLAYER");
    }

    @Test
    public void testisPlayerControlled() {
        assertEquals(true, p.isPlayerControlled());
    }
}
