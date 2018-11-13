/**
 * File:        Vigenere.java
 * Description: Decrypts Vigenere Ciphers with a key.
 * Created:     11/09/18
 *
 * @author Justin Zhu
 * @version 1.0
 */

public class Vigenere extends Decrypter {

    public Vigenere(String cipherText) {
        super(cipherText);
    }
    
    public String decrypt(String key) {
    	return decrypt(key, CharSet.ALPHANUMERIC);
    }
    
    @Override
    public String decrypt(String key, CharSet charset) {
        char base = charset.getBaseChar(), keyBase = charset.getBaseChar();
        int numChars = charset.getNumChars();

        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (Character.isWhitespace(c))
                continue;

            int shift = key.charAt(i % key.length()); 
            if (Character.isLetter(shift)) {
                shift -= Character.isUpperCase((char) shift) ? 'A' : 'a';
            }

            plainText += "" + super.undoShift(c, shift, charset);
        }

        return plainText;
    }

    public static void main(String[] args) {
        Vigenere vigenere = new Vigenere("bbbb1234");
        System.out.println(vigenere.decrypt("bc", CharSet.ALPHABETIC));

    }
}
