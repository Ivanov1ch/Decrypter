/**
 * File:        Caesar.java
 * Description: Decrypts Caesar Ciphers.
 * Created:     11/08/18
 *
 * @author Justin Zhu
 * @version 1.0
 */

public class Caesar extends Decrypter {

	public Caesar(String cipherText) {
        super(cipherText);
    }

	public String decrypt(int shift, CharSet charset) {
		char base = '\0';
		int numChars = 0;
		switch(charset) { // TODO Add more cases
			case ALPHABETIC:
				base = 'a';
				numChars = 26;
				break;
		}

		String plainText = "";
		for(int i = 0; i < cipherText.length(); i++) {
			char c = cipherText.charAt(i);
			boolean whitespace = false;
			switch (c) {
				case ' ':
				case '\n':
				case '\t':
					whitespace = true;
					break;
			}
			if(whitespace) {
				continue;
			}

			switch(charset) { // TODO Add more cases
				case ALPHABETIC:
					// preserve case
					if(Character.isUpperCase(c)) {
						base = 'A';
					}
					plainText += "" + (char) ((c - base - shift + numChars) % numChars + base);
					if(Character.isUpperCase(c)) {
						base = 'a';
					}
					break;
			}
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
		Caesar caesar = new Caesar("bcde");
		System.out.println(caesar.decrypt(1, CharSet.ALPHABETIC));
		
	}
}
