package localisation;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public final class DictionaryTest {

    @After
    public void tearDown() {
        Dictionary.loadText("english");
    }

    @Test
    public void testGetValueKeyTheSameWhenKeyNotFound() {
        Dictionary.loadText("english");
        String key = Dictionary.getValue("YARR");
        assertEquals("YARR", key);
    }

    @Test
    public void testGetValueWhenLanguageNotFound() {
        Dictionary.loadText("pirate");
        String key = Dictionary.getValue("YARR");
        assertEquals("YARR", key);
    }

    @Test
    public void testGetValueWhenLanguageFound() {
        Dictionary.loadText("english");
        String key = Dictionary.getValue("TITLE");
        assertEquals("Destroyers of Dungeons", key);
    }

    @Test
    public void testGetValueWhenLanguageFoundParam() {
        Dictionary.loadText("english");
        String key = Dictionary.getValue("PLAYER_N", "4345fmfsdkfjsdkf");
        assertEquals("Player 4345fmfsdkfjsdkf", key);
    }

    @Test
    public void testGetValueWhenLanguageFoundParamNull() {
        Dictionary.loadText("english");
        String key = Dictionary.getValue("PLAYER_N", null);
        assertEquals("Player $0$", key);
    }

    @Test
    public void testGetValueWhenLanguageFoundKeyNull() {
        Dictionary.loadText("english");
        String key = Dictionary.getValue(null);
        assertEquals(null, key);
    }

    @Test
    public void testGetLanguageWhenLoaded() {
        assertTrue(Dictionary.getLanguages().length > 0);
    }
}
