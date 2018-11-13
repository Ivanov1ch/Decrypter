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
            if (charset == CharSet.ALPHABETIC) {
                shift -= Character.isUpperCase((char) shift) ? 'A' : 'a';
            }

            plainText += "" + super.undoShift(c, shift, charset);
        }

        return plainText;
    }

    public static void main(String[] args) {
        Vigenere vigenere = new Vigenere("bbbb");
        System.out.println(vigenere.decrypt("bc", CharSet.ALPHABETIC));

    }
}
