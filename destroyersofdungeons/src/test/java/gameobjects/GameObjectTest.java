package gameobjects;

import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import gameobjects.dungeon.Tunnel;
import localisation.Dictionary;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class GameObjectTest {

    @BeforeClass
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public static void setUpStatic() {
        Dictionary.loadText("english");
    }

    private GameObject go;
    private GameObject go2;

    @Before
    public void setUp() {
        go = new Tunnel(0);
        go2 = new Player(0, "TEST_PLAYER", null);
    }

    @Test
    public void testHashCodeIdZero() {
        assertEquals(413, go.hashCode());
    }

    @Test
    @SuppressWarnings("null")
    public void testHashCodeId66546() {
        go = new Tunnel(66546);
        assertEquals(66959, go.hashCode());
    }

    @Test
    public void testEqualsTrue() {
        go = new Tunnel(443);
        go2 = new Tunnel(443);
        assertEquals(go, go2);
    }

    @Test
    public void testEqualsFalse() {
        go2 = new Tunnel(5688254);
        assertNotEquals(go, go2);
    }

    @Test
    public void testEqualsNullFalse() {
        assertNotEquals(go, null);
    }

    @Test
    public void testEqualsOtherClassFalse() {
        Actor a = new Player(34, "TEST_PLAYER_N02", null);
        assertNotEquals(go, a);
    }

    @Test
    public void testToString() {
        assertEquals("Tunnel 0", go.toString());
    }

    @Test
    public void testToString2() {
        assertEquals("TEST_PLAYER", go2.toString());
    }
}
