package gameobjects.items;

import constants.Constants;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public final class ElixirOfLifeTest {

    private ElixirOfLife eol;

    @BeforeClass
    public static void setUpClass() {
        Item.loadItemTypes();
    }

    @Before
    public void setUp() {
        eol = new ElixirOfLife();
    }

    @Test
    public void testOnHeal() {
        final int amount = 100;
        assertEquals((int) (amount * Constants.ELIXIR_OF_LIFE_HEALTH_MODFIER), eol.onHeal(amount));
    }
}
