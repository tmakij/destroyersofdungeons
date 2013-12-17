package dungeon;

import actors.Actor;
import actors.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public final class TunnelTest {

    private Tunnel tunnel;

    @Before
    public void setUp() {
        tunnel = new Tunnel(0);
    }

    private Actor addDummyPlayer() {
        Actor a = new Player(1, "TESTI_PELAAJA");
        tunnel.addActor(a);
        return a;
    }

    @Test
    public void testAddBlock() {
        final int amount = 29;
        for (int i = 0; i < amount; i++) {
            tunnel.addBlock(new Tunnel(i));
        }
        assertEquals(amount, tunnel.getNextTo().size());
    }

    @Test
    public void testGetNextToWhenEmpty() {
        assertEquals(0, tunnel.getNextTo().size());
    }

    @Test
    public void testNextBlocks() {
        Tunnel second = new Tunnel(1);
        Tunnel third = new Tunnel(2);
        tunnel.addBlock(second);
        second.addBlock(third);
        third.addBlock(tunnel);
        assertEquals(tunnel, tunnel.getNextTo().get(0).getNextTo().get(0).getNextTo().get(0));
    }

    @Test
    public void testAddActor() {
        Actor a = addDummyPlayer();
        assertEquals(true, tunnel.getActorSet().contains(a));
    }

    @Test
    public void testRemoveActor() {
        Actor a = addDummyPlayer();
        tunnel.removeActor(a);
        assertEquals(false, tunnel.getActorSet().contains(a));
    }

    @Test
    public void testgetActorSetWhenZero() {
        assertEquals(0, tunnel.getActorSet().size());
    }

    @Test
    public void testgetActorSetWhenAdded() {
        addDummyPlayer();
        assertEquals(1, tunnel.getActorSet().size());
    }

    @Test
    public void testToString() {
        assertEquals("Tunnel (0)", tunnel.toString());
    }

    @Test
    public void testHashCodeIdZero() {
        assertEquals(235, tunnel.hashCode());
    }

    @Test
    @SuppressWarnings("null")
    public void testHashCodeId66546() {
        tunnel = new Tunnel(66546);
        assertEquals(66781, tunnel.hashCode());
    }

    @Test
    public void testEqualsTrue() {
        tunnel = new Tunnel(443);
        Tunnel tunnelCopy = new Tunnel(443);
        assertEquals(tunnel, tunnelCopy);
    }

    @Test
    public void testEqualsFalse() {
        tunnel = new Tunnel(6665);
        Tunnel tunnelCopy = new Tunnel(5688254);
        assertEquals(false, tunnel.equals(tunnelCopy));
    }
}
