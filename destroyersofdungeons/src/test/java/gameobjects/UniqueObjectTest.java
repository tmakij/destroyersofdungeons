package gameobjects;

import gameobjects.actors.Player;
import gameobjects.dungeon.Tunnel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public final class UniqueObjectTest {

    private UniqueObject p;
    private UniqueObject t;

    @Before
    public void setUp() {
        p = new Player(0, "TEST_PLAYER", null);
        t = new Tunnel(1);
    }

    @Test
    public void testHashCodeIdZero() {
        assertEquals(413, p.hashCode());
    }

    @Test
    @SuppressWarnings("null")
    public void testHashCodeId66546() {
        t = new Tunnel(66546);
        assertEquals(66959, t.hashCode());
    }

    @Test
    public void testEqualsTrue() {
        t = new Tunnel(443);
        p = new Tunnel(443);
        assertEquals(t, p);
    }

    @Test
    public void testEqualsFalse() {
        p = new Tunnel(5688254);
        assertNotEquals(t, p);
    }

    @Test
    public void testEqualsNullFalse() {
        assertNotEquals(p, null);
    }

    @Test
    public void testEqualsOtherClassFalse() {
        assertNotEquals(p, t);
    }
}
