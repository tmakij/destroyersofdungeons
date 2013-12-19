package gameobjects.actors.races;

import gameobjects.actors.races.MinotaurRace;
import gameobjects.actors.races.Race;
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
