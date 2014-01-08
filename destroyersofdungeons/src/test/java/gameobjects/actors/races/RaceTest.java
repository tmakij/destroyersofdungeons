package gameobjects.actors.races;

import localisation.Dictionary;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public final class RaceTest {

    @BeforeClass
    @SuppressWarnings("UseSpecificCatch")
    public static void setUpOnce() {
        Dictionary.loadText("english");
    }

    private Race race;

    @Test
    public void testToString() {
        race = new MinotaurRace();
        assertEquals("Minotaur", race.toString());
    }
}
