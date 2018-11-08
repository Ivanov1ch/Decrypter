/**
 * Encryptor Spin-off of the Lipogram Lab in section 8.8
 *
 * @author Justin Zhu
 * @version 1.0 11/2/18
 */

public class Encryptor {
    private String text;
    private final int SHIFT;
    private static final int NUM_LETTERS = 26;

    public Encryptor(String text) {
        this(text, (int) (Math.random() * NUM_LETTERS));
    }

    public Encryptor(String text, int shift) {
        this.text = text;
        SHIFT = shift;
    }

    public String getEncrypted() {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i))) {
                result += "" + text.charAt(i);
                continue;
            }
            char start = 'a';
            if (Character.isUpperCase(text.charAt(i))) {
                start = 'A';
            }
            //System.out.println(text.charAt(i) + " " + start);
            result += "" + (char) ((Character.toLowerCase(text.charAt(i)) - 'a' + SHIFT + NUM_LETTERS) % NUM_LETTERS + start);
        }
        return result;
    }
}
