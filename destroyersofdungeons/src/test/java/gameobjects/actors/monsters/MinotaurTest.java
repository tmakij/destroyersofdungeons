package gameobjects.actors.monsters;

import constants.Constants;
import logic.BattleAction;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public final class MinotaurTest {

    private Minotaur m;

    @Before
    public void setUp() {
        m = new Minotaur(0);
    }

    @Test
    public void testGetAttack() {
        long exp = (long) (Constants.ACTOR_BASE_ATTACK * BattleAction.ATTACK.actModifier() * Constants.MINOTAUR_ATTACK_STRENGHT);
        assertEquals(exp, m.getAttack(BattleAction.ATTACK));
    }
}
