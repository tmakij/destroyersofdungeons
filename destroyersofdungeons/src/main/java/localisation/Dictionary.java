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
    private static Map<String, String> strings;

    /**
     * Loads all the text from localisation.txt file.
     *
     * @param language Which language is loaded.
     */
    public static void loadText(String language) {
        strings = new HashMap<>();
        try (InputStream localfile = Dictionary.class.getResourceAsStream("/localisation/localtext.txt"); Scanner scan = new Scanner(localfile)) {
            int n = getLineNumber(scan.nextLine(), language);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineSplit = line.split(";");
                if (lineSplit.length > n) {
                    strings.put(lineSplit[0], lineSplit[n]);
                }
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
     * @param params Objects that are put into the string. Will be put in the
     * string in the order as they are written to the localisation.
     * @return A localised value for a key. If the value is not found, the key
     * is returned.
     */
    public static String getValue(String key, Object... params) {
        if (strings.containsKey(key)) {
            key = strings.get(key);
            for (int i = 0; i < params.length; i++) {
                key = key.replaceAll(("\\$" + i + "\\$"), params[i].toString());
            }
        }
        return key;
    }
}
