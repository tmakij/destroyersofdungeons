package gameobjects.items;

import constants.Constants;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class ElixirOfLifeTest {

    @BeforeClass
    public static void setUpClass() {
        Item.loadItemTypes();
    }

    private ElixirOfLife eol;

    @Before
    public void setUp() {
        eol = new ElixirOfLife();
    }

    @Test
    public void testOnHeal() {
        final int amount = 100;
        assertEquals((long) (amount * Constants.ELIXIR_OF_LIFE_HEALTH_MODFIER), eol.onHeal(amount));
    }
}
