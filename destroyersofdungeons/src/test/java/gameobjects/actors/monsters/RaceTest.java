package gameobjects.actors.monsters;

import gameobjects.GameObjectType;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import testSetUp.InitClass;

public final class RaceTest extends InitClass {

    private GameObjectType race;

    @Test
    public void testToString() {
        race = new GameObjectType("MINOTAUR");
        assertEquals("Minotaur", race.toString());
    }
}
