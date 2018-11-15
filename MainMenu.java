/**
 * File:        MainMenu.java
 * Description: Displays the JOptionPane main menu, and controls the creation and display of DecrypterGUIs.
 * Created:     11/14/2018
 *
 * @author Justin Zhu and daniv
 * @version 1.0
 */

import javax.swing.*;

public class MainMenu {
    public static void main(String[] args) {
        while (true) {

            int chosenDecrypter = getChosenDecrypter(); // -1 = quit, 1 = Caesar, 2 = Vigenere

            switch (chosenDecrypter) {
                case 1:
                    openDecrypterGUI(1);
                    break;
                case 2:
                    openDecrypterGUI(2);
                    break;
                case 3:
                    showAboutPanel();
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    private static int getChosenDecrypter() {
        ImageIcon icon = null;

        try {
            icon = new ImageIcon("encryption-icon.png");
        } catch (Exception e) {
            System.out.println("Unable to find the icon! Showing the ugly GUI instead...");
        }

        Object[] options = {"Caesar Cipher", "Vigenere Cipher", "About", "Exit"};

        int choice = JOptionPane.showOptionDialog(null, "Welcome to the Decrypter!\nWhat would you like to do today?", "Welcome!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);

        if (choice != -1)
            return choice + 1; // If choice is -1 they exited, so we just return that. Otherwise, we shift the return value up by one to match the 1 = Caesar format

        return choice;
    }

    private static void showAboutPanel() {
        JOptionPane.showMessageDialog(null, "Decrypter was made by Justin Zhu and Daniel Ivanovich\nDecrypter was written in Java\nDecrypter is fully open-source - you can find it on GitHub under PlantsVsDan/Decrypter", "About Decrypter", JOptionPane.PLAIN_MESSAGE);
    }

    private static void openDecrypterGUI(int type) {
        if (type == 1 || type == 2) {
            DecrypterGUI window = new DecrypterGUI(type);
            window.setBounds(100, 100, 550, 480);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);

            while (window.isVisible()) {
            } // Wait, so a new main menu doesn't open
        }
    }
}