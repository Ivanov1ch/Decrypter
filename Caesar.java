/**
 * File:        Caesar.java
 * Description: Decrypts Caesar Ciphers with a shift.
 * Created:     11/08/18
 *
 * @author Justin Zhu
 * @version 1.0
 */

public class Caesar extends Decrypter {

    public Caesar(String cipherText) {
        super(cipherText);
    }

    public String decrypt(int shift) {
        return decrypt(shift, CharSet.ALPHANUMERIC);
    }

    public String decrypt(int shift, CharSet charset) {
        char base = charset.getBaseChar();
        int numChars = charset.getNumChars();

        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (charset.isInCharSet(c) && !CharSet.PUNCTUATION.isInCharSet(c))
                c = super.undoShift(c, shift, charset);

            plainText += "" + c;
        }

        return plainText;
    }

    @Override
    public String decrypt(String key) {
		return decrypt(key, CharSet.ALPHANUMERIC);
    }

    @Override
    public String decrypt(String key, CharSet charset) {
    	if (key.length() > 1)
    		return null;
    	char c = key.charAt(0);
        if (Character.isDigit(c)) {
            return decrypt(Integer.parseInt("" + key.charAt(0)), charset);
        } 
        else if (Character.isLetter(c)) {
			c = Character.toLowerCase(c);
			System.out.println(c);
            return decrypt((int) (c - 'a') + 1, charset);
        }
        return null;
    }

    public static void main(String[] args) {
        Caesar caesar = new Caesar("bcde 1234");
        System.out.println(caesar.decrypt("a"));

    }
}
