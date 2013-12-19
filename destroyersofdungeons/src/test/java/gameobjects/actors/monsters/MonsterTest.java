package gameobjects.actors.monsters;

import gameobjects.actors.monsters.Monster;
import gameobjects.actors.monsters.Minotaur;
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
}