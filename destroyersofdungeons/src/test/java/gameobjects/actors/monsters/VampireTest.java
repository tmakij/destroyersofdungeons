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
    public void testGetAttackHeals() {
        final int hit = 50;
        v.takeHit(hit);
        Player p = new Player(0, "TEST_PLAYER", null);
        v.attack(p, BattleAction.ATTACK);
        long exp = (long) (Constants.ACTOR_BASE_HEALTH - hit + (Constants.ACTOR_BASE_ATTACK * BattleAction.ATTACK.actModifier() * Constants.VAMPIRE_HEALT_ABSORB));
        assertEquals(exp, v.getHealth());
    }

    @Test
    public void testGetAttackReturnsDamage() {
        long exp = (long) (Constants.ACTOR_BASE_ATTACK * BattleAction.ATTACK.actModifier());
        assertEquals(exp, v.getAttack(BattleAction.ATTACK));
    }
}
