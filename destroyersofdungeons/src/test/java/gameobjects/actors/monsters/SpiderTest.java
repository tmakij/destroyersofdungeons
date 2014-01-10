package gameobjects.actors.monsters;

import constants.Constants;
import logic.BattleAction;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import testSetUp.InitClass;

public final class SpiderTest extends InitClass {

    private Spider s;

    @Before
    public void setUp() {
        s = new Spider(0);
    }

    @Test
    public void testGetAttack() {
        long exp = (long) (Constants.ACTOR_BASE_ATTACK * BattleAction.DEFEND.actModifier() * Constants.SPIDER_DEFENSE_STRENGHT);
        assertEquals(exp, s.getAttack(BattleAction.DEFEND));
    }
}
