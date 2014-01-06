package gameobjects.actors.monsters;

import localisation.Dictionary;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public final class MonsterTest {

    private Monster mon;

    @BeforeClass
    @SuppressWarnings("UseSpecificCatch")
    public static void setUpOnce() {
        try {
            Dictionary.loadText("english");
        } catch (Exception ex) {
        }
    }

    @Before
    public void setUp() {
        mon = new Minotaur(0);
    }

    @Test
    public void testIsPlayerControlled() {
        assertEquals(false, mon.isPlayerControlled());
    }

    @Test
    public void testToString() {
        assertEquals("Minotaur", mon.toString());
    }
}
