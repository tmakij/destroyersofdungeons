package localisation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Holds all the text of Destroyers of Dungeons. Cannot be created or inherited.
 * Is enum because there are no static (abstract final) classes in java.
 */
public enum dictionary {

    ;
        private static final Map<String, String> strings = new HashMap<>();

    /**
     * Loads all the text from localisation.txt file.
     */
    public static void loadText() {
        Scanner scan;
        try {
            File file = new File("localisation.txt");
            scan = new Scanner(file);
        } catch (FileNotFoundException fex) {
            return;
        }

        String lang = scan.nextLine();
        String[] langs = lang.split(";");
        int n = 0;
        for (int i = 0; i < langs.length; i++) {
            if (langs[i].equals("english")) {
                n = i;
                break;
            }
        }

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] lineSplit = line.split(";");
            strings.put(lineSplit[0], lineSplit[n]);
        }
    }

    /**
     * Get the localised value for a key.
     *
     * @param key Which string to return.
     * @return Localised value for a key. If value is not found, key is
     * returned.
     */
    public static String getValue(String key) {
        if (strings.containsKey(key)) {
            return strings.get(key);
        }
        return key;
    }
}
