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
        KeyGen keyGen = new KeyGen(CharSet.ALPHABETIC, "tqv qdzct srxnn ofx slmyj oevr cyiakene urzh uopj", 3, "dictionary.txt");


        Decrypter decrypter = new Vigenere("ujh rwldm esqzo hry lxnrv pxhs 13 ndaa gpiv. {qkfp_ewg-123456}");
        System.out.println(decrypter.decrypt("abc"));
    }

}
