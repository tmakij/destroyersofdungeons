package localisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds all the text of Destroyers of Dungeons. Cannot be created or inherited.
 * Is enum because there are no static (abstract final) classes in java.
 */
public enum Dictionary {

    ;
    /**
    * The map that holds the localisation keys and their values.
    */
    private static final Map<String, String> strings;
    /**
     * The loaded language.
     */
    private static String language;
    /**
     * The available languages.
     */
    private static final String languages[];

    static {
        strings = new HashMap<>();
        String langs[] = null;
        try (InputStream localfile = Dictionary.class.getResourceAsStream("/localisation/localtext.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(localfile, "UTF-8"))) {
            String line = reader.readLine();
            if (line != null) {
                String l[] = line.split(";");
                langs = new String[l.length - 1];
                for (int i = 1; i < l.length; i++) {
                    langs[i - 1] = l[i];
                }
            }
        } catch (IOException ex) {
        } finally {
            languages = langs;
        }
        loadText("english");
    }

    /**
     * Loads all the text from localisation.txt file.
     *
     * @param language Which language is loaded.
     */
    public static void loadText(String language) {
        strings.clear();
        try (InputStream localfile = Dictionary.class.getResourceAsStream("/localisation/localtext.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(localfile, "UTF-8"))) {
            Dictionary.language = language;
            int n = getLineNumber();
            if (n == 0) {
                return;
            }
            reader.readLine();
            //First line is the language line
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] lineSplit = line.split(";");
                if (lineSplit.length > n) {
                    strings.put(lineSplit[0], lineSplit[n]);
                }
            }
        } catch (IOException ex) {
        }
    }

    /**
     * Get the column of the language.
     *
     * @param line The language line.
     * @return The column of the language.
     */
    private static int getLineNumber() {
        int ret = 0;
        for (int i = 0; i < languages.length; i++) {
            if (languages[i].equals(language)) {
                ret = i + 1;
            }
        }
        return ret;
    }

    /**
     * Get the available languages.
     *
     * @return The available languages.
     */
    public static String[] getLanguages() {
        return languages.clone();
    }

    /**
     * Get the localised value for a key.
     *
     * @param key Which string to return.
     * @param params Objects that are put into the string. Will be put in the
     * string in the order as they are written to the localisation.
     * @return A localised value for a key. If the value is not found, the key
     * is returned.
     */
    public static String getValue(String key, Object... params) {
        if (strings.isEmpty()) {
            return "Error on loading localisation for language " + language;
        }
        String value = key;
        if (key != null && strings.containsKey(key)) {
            value = strings.get(key);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    value = value.replaceAll(("\\$" + i + "\\$"), params[i].toString());
                }
            }
        }
        return value;
    }
}
