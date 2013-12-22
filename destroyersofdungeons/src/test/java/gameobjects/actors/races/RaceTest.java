package gameobjects.actors.races;

import org.junit.Test;
import static org.junit.Assert.*;

public final class RaceTest {

    private Race race;

    @Test
    public void testToString() {
        race = new MinotaurRace();
        assertEquals("Minotaur", race.toString());
    }
}
