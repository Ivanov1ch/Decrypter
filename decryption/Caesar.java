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
		for(int i = 0; i < cipherText.length(); i++) {
			char c = cipherText.charAt(i);
			if(Character.isWhitespace(c))
				continue;
			
			plainText += "" + super.undoShift(c, shift, charset);
		}
		
		return plainText;
	}

	@Override
	public String decrypt(String key, CharSet charset) {
		try {
			return decrypt(Integer.parseInt(key), charset);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		Caesar caesar = new Caesar("bcde1234");
		System.out.println(caesar.decrypt(1, CharSet.ALPHANUMERIC));
		
	}
}
