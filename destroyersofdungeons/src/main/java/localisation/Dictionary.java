package localisation;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Holds all the text of Destroyers of Dungeons. Cannot be created or inherited.
 * Is enum because there are no static (abstract final) classes in java.
 */
public enum Dictionary {

    ;
        private static final Map<String, String> strings = new HashMap<>();

    /**
     * Loads all the text from localisation.txt file.
     *
     * @param language Which language is loaded.
     */
    public static void loadText(String language) {
        try (InputStream localfile = Dictionary.class.getResourceAsStream("/localisation/localtext.txt"); Scanner scan = new Scanner(localfile)) {
            int n = getLineNumber(scan.nextLine(), language);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineSplit = line.split(";");
                strings.put(lineSplit[0], lineSplit[n]);
            }
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }

    private static int getLineNumber(String line, String language) {
        String[] langs = line.split(";");
        for (int i = 0; i < langs.length; i++) {
            if (langs[i].equals(language)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Get the localised value for a key.
     *
     * @param key Which string to return.
     * @return A localised value for a key. If the value is not found, the key is
     * returned.
     */
    public static String getValue(String key) {
        if (strings.containsKey(key)) {
            return strings.get(key);
        }
        return key;
    }
}
