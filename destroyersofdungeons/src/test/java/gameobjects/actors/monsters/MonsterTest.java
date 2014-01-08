package gameobjects.actors.monsters;

import localisation.Dictionary;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class MonsterTest {

    @BeforeClass
    @SuppressWarnings("UseSpecificCatch")
    public static void setUpOnce() {
        Dictionary.loadText("english");
    }

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
