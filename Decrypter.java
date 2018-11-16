/**
 * File:        Decrypter.java
 * Description: A General decryption class. All other specific decrypters inherit from this base.
 * Created:     11/7/18
 *
 * @author Justin Zhu
 * @version 1.0
 */

public abstract class Decrypter {
    protected String cipherText;

    public Decrypter(String cipherText) {
        setCipherText(cipherText);
    }

    public abstract String decrypt(String key);

    public abstract String decrypt(String key, CharSet charset);

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    // Shifts characters backwards, instead of forwards like the Caesar and Vigenere ciphers do
    public static char undoShift(char c, int shift, CharSet charset) {
        if (Character.isLetter(c)) {
            charset = CharSet.ALPHABETIC;
        }
        if (Character.isDigit(c)) {
            charset = CharSet.NUMERIC;
        }
        char base = charset.getBaseChar();
        int numChars = charset.getNumChars();
        char result = '\0';
        switch (charset) {
            case ALPHABETIC:
                // preserve case
                if (Character.isUpperCase(c)) {
                    base = 'A';
                }
                result = (char) ((c + numChars * 20 - base - shift) % numChars + base);
                if (Character.isUpperCase(c)) {
                    base = 'a';
                }
                break;

            case NUMERIC:
                result = (char) ((c + numChars * 20 - base - shift) % numChars + base);
                break;
        }
        return result;
    }
}
