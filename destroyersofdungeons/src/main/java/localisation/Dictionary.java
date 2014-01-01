package localisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Holds all the text of Destroyers of Dungeons. Cannot be created or inherited.
 * Is enum because there are no static (abstract final) classes in java.
 */
public enum Dictionary {

    ;
    private static Map<String, String> strings;
    private static String language;

    /**
     * Loads all the text from localisation.txt file.
     *
     * @param language Which language is loaded.
     * @throws java.io.IOException If the localisation is not found.
     * @throws UnsupportedOperationException If the language is not found.
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public static void loadText(String language) throws IOException, UnsupportedOperationException {
        InputStream localfile = Dictionary.class.getResourceAsStream("/localisation/localtext.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(localfile, "UTF-8"));
        Dictionary.language = language;
        int n = getLineNumber(reader.readLine());
        strings = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] lineSplit = line.split(";");
            if (lineSplit.length > n) {
                strings.put(lineSplit[0], lineSplit[n]);
            }
        }
    }

    private static int getLineNumber(String line) {
        String[] langs = line.split(";");
        for (int i = 0; i < langs.length; i++) {
            String lang = langs[i];
            if (lang.equals(language)) {
                language = lang;
                return i;
            }
        }
        strings = null;
        throw new UnsupportedOperationException("Invalid language, loading cannot proceed");
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
        if (strings == null) {
            return "Error on loading localisation for language " + language;
        }
        if (strings.containsKey(key)) {
            key = strings.get(key);
            for (int i = 0; i < params.length; i++) {
                key = key.replaceAll(("\\$" + i + "\\$"), params[i].toString());
            }
        }
        return key;
    }
}
