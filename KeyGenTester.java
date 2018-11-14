/**
 * File:             KeyGenTester.java
 * Created:          11/13/2018
 * Description:      Description goes here.
 *
 * @author: danIv (Daniel Ivanovich)
 * @version: 1.0
 */


public class KeyGenTester {

    public static void main(String[] args) {
        KeyGen keyGen = new KeyGen(CharSet.ALPHABETIC, "hblio qhbrb", 2);
        keyGen.generateAll();

        System.out.println(keyGen.getKeyDecryptions());
    }

}
