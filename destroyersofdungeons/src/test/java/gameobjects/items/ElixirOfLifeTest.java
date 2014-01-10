package gameobjects.items;

import constants.Constants;
import gameobjects.actors.Player;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public final class ElixirOfLifeTest extends ItemTestBase {

    @Before
    public void setUp() {
        i = new ElixirOfLife();
        a = new Player(0, "TEST_PLAYER", null);
    }

    @Test
    public void testOnHeal() {
        final int amount = 100;
        assertEquals((long) (amount * Constants.ELIXIR_OF_LIFE_HEALTH_MODFIER), i.onHeal(amount));
    }

    @Test
    public void testOnHealActor() {
        final int amount = 50;
        a.takeHit(amount);
        a.addItem(i);
        a.heal();
        assertEquals((long) (amount + Constants.ACTOR_HEAL_RATE * Constants.ELIXIR_OF_LIFE_HEALTH_MODFIER), a.getHealth());
    }
}
