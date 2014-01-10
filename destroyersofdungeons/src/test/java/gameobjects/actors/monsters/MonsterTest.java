package gameobjects.actors.monsters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import testSetUp.InitClass;

public final class MonsterTest extends InitClass {

    private Monster mon;

    @Before
    public void setUp() {
        mon = new Minotaur(0);
    }

    @Test
    public void testIsPlayerControlled() {
        assertFalse(mon.isPlayerControlled());
    }

    @Test
    public void testToString() {
        assertEquals("Minotaur", mon.toString());
    }
}
