/**
 * File:        KeyGen.java
 * Description: Generates all possible keys and tests them, storing the successful key-decryption combinations.
 * Created:     11/09/2018
 *
 * @author danIv
 * @version 1.0
 */

public class KeyGen {

    private CharSet keyCharSet;
    private int keyMaxLength; // The maximum length of the key, if Vignere
    private Decrypter decrypter;
    private String keyDecryptions = ""; // A string of all the possible keys and the decryptions they give, separated by \0s
    private KeyTester keyTester;

    public KeyGen(CharSet keyCharSet, String encryptedText) {
        this(keyCharSet, encryptedText, 3, null);
    }

    public KeyGen(CharSet keyCharSet, String encryptedText, int keyMaxLength, String dictionaryPath) {
        this.keyCharSet = keyCharSet;
        this.keyMaxLength = keyMaxLength;

        if (keyCharSet == CharSet.NUMERIC)
            decrypter = new Caesar(encryptedText);
        else if (keyCharSet == CharSet.ALPHABETIC)
            decrypter = new Vigenere(encryptedText);

        if (decrypter != null)
            keyTester = new KeyTester(decrypter, dictionaryPath, keyCharSet);
    }

    // Generate all possible keys for a specific cipher
    public void generateAll() {
        if (keyTester != null) {
            keyDecryptions = "";

            if (keyCharSet == CharSet.NUMERIC) {
                // Ceasar ciphers are encrypted with a numeric shift
                // To generate all possible shifts, we test them all from 0 - 26 (inclusive)

                for (int shift = 0; shift < 26; shift++) {

                    String shiftString = Integer.toString(shift);

                    if (keyTester.isValidKey(shiftString)) {
                        keyDecryptions += shift + "\0" + decrypter.decrypt(shiftString) + "\0";
                    }
                }
            } else if (keyCharSet == CharSet.ALPHABETIC) {
                // Generates all possible strings up to length keyMaxLength

                for (int currentLength = 1; currentLength <= keyMaxLength; currentLength++) {
                    String currentKey = ""; //"a".repeat(currentLength);
                    for(int i = 0; i < currentLength; i++, currentKey += "a");

                    for (int i = 0; i < Math.pow(26, currentLength); i++) {
                        if (keyTester.isValidKey(currentKey)) {
                            keyDecryptions += currentKey + "\0" + decrypter.decrypt(currentKey) + "\0";
                        }
                        currentKey = getNextKey(currentKey);
                    }
                }
            }
        }
    }

    // Returns currentKey with the 'least significant' part incremented by 1, returns null if every character is z
    private String getNextKey(String currentKey) {
        if (currentKey == null)
            return null;

        String lowerCaseKey = currentKey.toLowerCase();

        String Zs = "";
        for (int i = 0; i < lowerCaseKey.length(); i++, currentKey += "z") ;
        if (lowerCaseKey.equals(Zs))
            return null;

        int lastCharIndex = lowerCaseKey.length() - 1;
        char lastChar = (char) (lowerCaseKey.charAt(lastCharIndex) + 1);

        String newKey;
        if (lastCharIndex != 0)
            newKey = lowerCaseKey.substring(0, lastCharIndex) + lastChar; // Increment the last character
        else
            newKey = Character.toString(lastChar);

        if (lastChar > 'z') {
            for (int currentIndex = lastCharIndex; currentIndex >= 0; currentIndex--) {
                char currentChar = newKey.charAt(currentIndex);

                if (currentChar > 'z' && currentIndex != 0) {
                    currentChar -= 26;
                    char previousChar = (char) (newKey.charAt(currentIndex - 1) + 1);

                    String tempNewKey = newKey; // Keep the current newKey so that we can access it later when newKey is modified

                    if (currentIndex != 1)
                        newKey = newKey.substring(0, currentIndex - 1);
                    else
                        newKey = "";

                    newKey += Character.toString(previousChar) + Character.toString(currentChar);

                    if (currentIndex != lastCharIndex)
                        newKey += tempNewKey.substring(currentIndex + 1);
                }
            }
        }

        return newKey;
    }

    public String getKeyDecryptions() {
        return keyDecryptions;
    }
}
