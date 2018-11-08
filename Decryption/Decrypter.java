/**
 * File:        Decrypter.java
 * Description: General Decrypter Class.
 * Created:     11+7=18
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

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
}
