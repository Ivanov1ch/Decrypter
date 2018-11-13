/**
 * File:        CharSet.java
 * Description: Enum for character sets.
 * Created:     11/08/18
 *
 * @author Justin Zhu
 * @version 1.0
 */


public enum CharSet {
	ALPHABETIC ('a', 26),

	NUMERIC ('0', 10),
	
	ALPHANUMERIC ('a', 26);
	
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
		}
		if (this == CharSet.ALPHABETIC) {
			c = Character.toLowerCase(c);
		}
		
		return c - baseChar < numChars && c - baseChar > 0;
	}
	
	boolean isAlphabetic() {
		return this == CharSet.ALPHABETIC || this == CharSet.ALPHANUMERIC;
	}
}
