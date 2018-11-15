/**
 * File:        CharSet.java
 * Description: Enum for character sets, allowing us to easily tell if a key is valid.
 * Created:     11/08/18
 *
 * @author Justin Zhu
 * @version 1.0
 */


public enum CharSet {
    ALPHABETIC('a', 26),

    NUMERIC('0', 10),

    PUNCTUATION('.', 31), // All the symbols on a common QUERTY keyboard

    ALPHANUMERIC('a', 26);

    private final char baseChar;
    private final int numChars;

    CharSet(char baseChar, int numChars) {
        this.baseChar = baseChar;
        this.numChars = numChars;
    }

    char getBaseChar() {
        return baseChar;
    }

    int getNumChars() {
        return numChars;
    }

    boolean isInCharSet(char c) {
        if (this == CharSet.ALPHANUMERIC) {
            return CharSet.ALPHABETIC.isInCharSet(c) || CharSet.NUMERIC.isInCharSet(c);
        } else if (this == CharSet.ALPHABETIC) {
            c = Character.toLowerCase(c);
        } else if (this == CharSet.PUNCTUATION) {
            String punctuation = ".,/?<>;:\"'[]{}|\\+=_-()*&^%$#@!`~";
            return punctuation.indexOf(c) > -1;
        }

        return c - baseChar < numChars && c - baseChar >= 0;
    }

    boolean isAlphabetic() {
        return this == CharSet.ALPHABETIC || this == CharSet.ALPHANUMERIC;
    }
}
