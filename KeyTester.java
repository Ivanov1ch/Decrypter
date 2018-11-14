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

    private final double PERCENT_VALID_WORDS = 0.9; // How many of the words need to be valid in order to accept the key as valid (0.9 = 90%)
    private Decrypter decrypter;
    private CharSet keyCharSet; // The CharSet that will be applied to the key
    private String dictionaryPath = "C:\\Users\\daniv\\Dropbox\\Programming\\AP Java\\Projects\\Decrypter\\decryption\\dictionary.txt";
    private String validWords = "";

    public KeyTester() {
        this(null, CharSet.NUMERIC);
    }

    public KeyTester(Decrypter decrypter) {
        this(decrypter, CharSet.NUMERIC);
    }

    public KeyTester(Decrypter decrypter, CharSet keyCharSet) {
        this.decrypter = decrypter;
        this.keyCharSet = keyCharSet;

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
        switch (keyCharSet) {
            case NUMERIC:
                try {
                    Integer.parseInt(key);
                } catch (Exception e) {
                    // It is not fully numeric
                    return false;
                }

                if (decrypter instanceof Vigenere)
                    return false;

                // The key matches the format the cipher asks for - we can safely decrypt
                String decrypted = decrypter.decrypt(key);

                return isValidDecryption(decrypted);
            case ALPHABETIC:
                if (key.matches(".*\\d+.*")) // Returns true if the string contains a number
                    return false;

                if (decrypter instanceof Caesar)
                    return false;

                // The key matches the format the cipher asks for - we can safely decrypt
                String decryptedMessages = decrypter.decrypt(key);

                return isValidDecryption(decryptedMessages);
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
