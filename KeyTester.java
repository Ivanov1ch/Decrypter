/**
 * File:        KeyTester.java
 * Description: Tests keys until a almost all of the words in the decryptions they yield are valid in the American English dictionary.
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
    private String dictionaryPath;
    private String validWords = "";

    public KeyTester() {
        this(null, null, CharSet.NUMERIC);
    }

    public KeyTester(Decrypter decrypter) {
        this(decrypter, null, CharSet.NUMERIC);
    }

    public KeyTester(Decrypter decrypter, CharSet charSet) {
        this(decrypter, null, charSet);
    }

    public KeyTester(Decrypter decrypter, String dictionaryPath, CharSet keyCharSet) {
        this.decrypter = decrypter;
        this.dictionaryPath = dictionaryPath;
        this.keyCharSet = keyCharSet;

        File dict;

        try {
            dict = new File(dictionaryPath);

            Scanner scanner = new Scanner(dict);

            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                validWords += word.toLowerCase() + "\n";
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
        if (decryption == null)
            return false;

        int numValidWords = 0;
        int numSpaces = decryption.length() - decryption.replaceAll(" ", "").length();
        int numWords = numSpaces + 1;

        for (String word : decryption.split(" ")) {
            if (validWords.indexOf(removePunctuation(word.toLowerCase())) > -1)
                numValidWords++;
        }

        double percentValidWords = (double) (numValidWords) / numWords;

        if (numWords <= 4)
            return percentValidWords == 1;
        else if (numWords <= 8)
            return percentValidWords >= 0.75;
        else
            return percentValidWords >= PERCENT_VALID_WORDS;
    }

    private String removePunctuation (String word) {
        String editedWord = "";

        for(int i = 0; i < word.length(); i++) {
            char cuttentChar = word.charAt(i);

            if(!CharSet.PUNCTUATION.isInCharSet(cuttentChar)) {
                editedWord += cuttentChar;
            }
        }

        return editedWord;
    }
}
