package gameobjects.actors.races;

import localisation.Dictionary;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public final class RaceTest {

    private Race race;

    @BeforeClass
    @SuppressWarnings("UseSpecificCatch")
    public static void setUpOnce() {
        try {
            Dictionary.loadText("english");
        } catch (Exception ex) {
        }
    }

    @Test
    public void testToString() {
        race = new MinotaurRace();
        assertEquals("Minotaur", race.toString());
    }
}
