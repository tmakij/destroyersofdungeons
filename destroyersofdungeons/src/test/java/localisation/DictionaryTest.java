package localisation;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DictionaryTest {

    @Before
    public void setUp() {
        try {
            Dictionary.loadText("Erasing Memory");
        } catch (UnsupportedOperationException | IOException ex) {
        }
    }

    @Test
    public void testDictionaryLoadThrowsUnsopported() {
        try {
            Dictionary.loadText("pirate");
        } catch (UnsupportedOperationException | IOException ex) {
            assertEquals(UnsupportedOperationException.class, ex.getClass());
        }
    }

    @Test
    public void testGetValueKeyTheSameWhenKeyNotFound() {
        try {
            Dictionary.loadText("english");
        } catch (UnsupportedOperationException | IOException ex) {
        } finally {
            String key = Dictionary.getValue("YARR");
            assertEquals("YARR", key);
        }
    }

    @Test
    public void testGetValueWhenLanguageNotFound() {
        try {
            Dictionary.loadText("pirate");
        } catch (UnsupportedOperationException | IOException ex) {
        } finally {
            String key = Dictionary.getValue("YARR");
            assertEquals("Error on loading localisation for language pirate", key);
        }
    }

    @Test
    public void testGetValueWhenLanguageFound() {
        try {
            Dictionary.loadText("english");
        } catch (UnsupportedOperationException | IOException ex) {
        } finally {
            String key = Dictionary.getValue("TITLE");
            assertEquals("Destroyers of Dungeons", key);
        }
    }

    @Test
    public void testGetValueWhenLanguageFoundParam() {
        try {
            Dictionary.loadText("english");
        } catch (UnsupportedOperationException | IOException ex) {
        } finally {
            String key = Dictionary.getValue("PLAYER_N", "4345fmfsdkfjsdkf");
            assertEquals("Player 4345fmfsdkfjsdkf", key);
        }
    }

    @Test
    public void testGetValueWhenLanguageFoundParamNull() {
        try {
            Dictionary.loadText("english");
        } catch (UnsupportedOperationException | IOException ex) {
        } finally {
            String key = Dictionary.getValue("PLAYER_N", null);
            assertEquals("Player $0$", key);
        }
    }

    @Test
    public void testGetValueWhenLanguageFoundKeyNull() {
        try {
            Dictionary.loadText("english");
        } catch (UnsupportedOperationException | IOException ex) {
        } finally {
            String key = Dictionary.getValue(null);
            assertEquals(null, key);
        }
    }

    @Test
    public void testGetLanguageWhenLoaded() {
        assertEquals(true, Dictionary.getLanguages().length > 0);

    }
}
