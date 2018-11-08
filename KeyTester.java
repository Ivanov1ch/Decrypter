/**
 * File:        KeyTester.java
 * Description: Tests keys until at least 90% of the words are valid in the English dictionary.
 * Created:     11/08/2018
 *
 * @author danIv
 * @version 1.0
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class KeyTester {
    public static enum KeySettings {ALPHABETIC, NUMERIC}

    ;

    private final double PERCENT_VALID_WORDS = 0.9; // How many of the words need to be valid in order to accept the key as valid (0.9 = 90%)
    private Decrypter decrypter;
    private KeySettings keySettings;
    private String dictionaryPath = "dictionary.txt";
    private String validWords = "";

    public KeyTester() {
        this(null, KeySettings.NUMERIC);
    }

    public KeyTester(Decrypter decrypter) {
        this(decrypter, KeySettings.NUMERIC);
    }

    public KeyTester(Decrypter decrypter, KeySettings keySettings) {
        this.decrypter = decrypter;
        this.keySettings = keySettings;

        File dict;

        try {
            dict = new File(dictionaryPath);

            Scanner scanner = new Scanner(dict);

            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                validWords += word;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error! Cannot find file " + dictionaryPath + "! Shutting down...");
            System.exit(10);
        }
    }

    public boolean isValidKey(String key) {
        switch (keySettings) {
            case NUMERIC:
                try {
                    Integer.parseInt(key);
                } catch (Exception e) {
                    // It is not fully numeric
                    return false;
                }

                if (decrypter instanceof Vignere)
                    return false;

                // The key matches the format the cipher asks for - we can safely decrypt
                String decrypted = decrypter.decrypt(key.trim());

                return isValidDecryption(decrypted);
            case ALPHABETIC:
                if (key.matches(".*\\d+.*")) // Returns true if the string contains a number
                    return false;

                if (decrypter instanceof Caesar)
                    return false;

                // The key matches the format the cipher asks for - we can safely decrypt
                String decrypted = decrypter.decrypt(key.trim());

                return isValidDecryption(decrypted);
            default:
                return false;
        }
    }

    private boolean isValidDecryption(String decryption) {
        int numValidWords = 0;
        int lastSpaceIndex = decryption.length();
        int numSpaces = decryption.length() - decryption.replaceAll(" ", "").length();
        int numWords = numSpaces + 1;

        do {
            lastSpaceIndex = decryption.lastIndexOf(" ", lastSpaceIndex);
            int nextSpaceIndex = decryption.lastIndexOf(" ", lastSpaceIndex);

            String word = decryption.substring(nextSpaceIndex + 1, lastSpaceIndex);

            if (validWords.indexOf(word) > -1)
                numValidWords++;
        } while (decryption.lastIndexOf(" ", lastSpaceIndex) > -1);

        return (double) (numValidWords) / numWords >= PERCENT_VALID_WORDS;
    }
}
