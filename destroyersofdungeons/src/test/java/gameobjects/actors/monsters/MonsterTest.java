package gameobjects.actors.monsters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class MonsterTest {

    private Monster mon;

    @Before
    public void setUp() {
        mon = new Minotaur(0);
    }

    @Test
    public void testIsPlayerControlled() {
        assertEquals(false, mon.isPlayerControlled());
    }

    @Test
    public void testToStringGetsRaceName() {
        assertEquals("Minotaur", mon.toString());
    }
}
