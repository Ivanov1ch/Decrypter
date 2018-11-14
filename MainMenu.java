/**
 * File:        MainMenu.java
 * Description: Description.
 * Created:     11/14/2018
 *
 * @author Justin Zhu and daniv
 * @version 1.0
 */
 
import javax.swing.JOptionPane;
 
 public class MainMenu {
 	public static void main(String[] args) {
 		while (true) {
 				
 			int chosenDecrypter = getChosenDecrypter(); // -1 = quit, 1 = Caesar, 2 = Vigenere	
 			
 			switch (chosenDecrypter) {
 				case 1:
 					// init Caesar GUI
 					
 					break;
 				case 2:
 					// init Vigenere GUI
 					break;
 				default:
 					System.exit(0);
 			}	
 		}
 	}
 	
 	private static int getChosenDecrypter() {
 		return 1;
 	}
 }
