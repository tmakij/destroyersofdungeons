package gameobjects.actors.monsters;

import constants.Constants;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public final class GhostTest {

    private Ghost g;

    @Before
    public void setUp() {
        g = new Ghost(0);
    }

    @Test
    public void testGetHitDamage() {
        g.takeHit(10);
        assertEquals((long) (Constants.ACTOR_BASE_HEALTH - (10 * Constants.GHOTS_DAMAGE_REDUCTION)), g.getHealth());
    }
}
