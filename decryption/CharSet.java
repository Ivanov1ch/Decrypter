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

	NUMERIC ('0', 10);
	
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
}
