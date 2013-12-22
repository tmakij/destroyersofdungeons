package gameobjects.dungeon;

import gameobjects.actors.Actor;
import gameobjects.actors.Player;
import java.util.ArrayList;
import java.util.List;
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
    public void testgetOtherActors() {
        Actor a = new Player(34, "TESTI_PELAAJA");
        Actor b = new Player(3334, "TESTI_PELAAJA_NO2");
        tunnel.addActor(a);
        tunnel.addActor(b);
        List<Actor> others = new ArrayList<>();
        others.add(b);
        assertEquals(others, tunnel.getOtherActors(a));
    }
}
