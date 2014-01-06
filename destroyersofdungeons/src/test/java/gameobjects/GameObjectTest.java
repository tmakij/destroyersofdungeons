package gameobjects;

import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import gameobjects.dungeon.Tunnel;
import localisation.Dictionary;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public final class GameObjectTest {

    private GameObject go;
    private GameObject go2;

    @BeforeClass
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public static void setUpStatic() {
        try {
            Dictionary.loadText("english");
        } catch (Exception ex) {
        }
    }

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
        assertEquals(false, go.equals(go2));
    }

    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testEqualsNullFalse() {
        assertEquals(false, go.equals(null));
    }

    @Test
    public void testEqualsOtherClassFalse() {
        Actor a = new Player(34, "TEST_PLAYER_N02", null);
        assertEquals(false, go.equals(a));
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
