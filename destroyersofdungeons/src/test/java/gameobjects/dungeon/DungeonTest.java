package gameobjects.dungeon;

import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public final class DungeonTest {

    private Dungeon d;

    @Before
    public void setUp() {
        d = new Dungeon();
    }

    @Test
    public void testGetGameID() {
        int id = d.getGameID();
        int id2 = d.getGameID();
        assertNotEquals(id, id2);
    }
}
