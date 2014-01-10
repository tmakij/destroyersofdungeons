package gameobjects.actors.monsters;

import constants.Constants;
import gameobjects.actors.Player;
import logic.BattleAction;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public final class VampireTest {

    private Vampire v;

    @Before
    public void setUp() {
        v = new Vampire(0);
    }

    @Test
    public void testGetAttack() {
        v.takeHit(50);
        Player p = new Player(0, "TEST_PLAYER", null);
        v.attack(p, BattleAction.ATTACK);
        long exp = (long) (50 + (Constants.ACTOR_BASE_ATTACK * BattleAction.ATTACK.actModifier() * Constants.VAMPIRE_HEALT_ABSORB));
        assertEquals(exp, v.getHealth());
    }
}
