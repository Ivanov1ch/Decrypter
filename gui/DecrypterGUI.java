import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.JLabel;

public class DecrypterGUI extends JFrame
        implements ActionListener {
    private JTextArea ciphertext, plaintext, keyInput, keyGuess;
    private JButton go;

    // Constructor
    public DecrypterGUI() {
        super("Decrypter");

        //setJMenuBar(new DecrypterMenu(this));
        setupGui();

        ciphertext.setText("Type or paste your text here or load it from a file");
        keyInput.setText("Enter your key here, or no key for brute force\0");
        refresh();
    }

    public String getText() {
        return ciphertext.getText();
    }

    public String getEncrypted() {
        return plaintext.getText();
    }

    public void setText(String text) {
        ciphertext.setText(text);
    }

    public void refresh() {
        String text = ciphertext.getText();
        Encryptor encryptor = new Encryptor(text);
        plaintext.setText(encryptor.getEncrypted());
    }

    // Called when the Refresh burron is clicked
    public void actionPerformed(ActionEvent e) {
        refresh();
    }

    // ********************** GUI setup ********************************

    private void setupGui() {
        ciphertext = new JTextArea(10, 20);
        ciphertext.setLineWrap(true);
        ciphertext.setWrapStyleWord(true);
        JScrollPane ciphertextPane = new JScrollPane(ciphertext,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        plaintext = new JTextArea(10, 20);
        plaintext.setEditable(false);
        plaintext.setBackground(Color.LIGHT_GRAY);
        plaintext.setLineWrap(true);
        plaintext.setWrapStyleWord(true);
        JScrollPane plaintextPane = new JScrollPane(plaintext,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		keyInput = new JTextArea(10, 15);
		keyInput.setLineWrap(true);
		keyInput.setWrapStyleWord(true);
		JScrollPane keyInputPane = new JScrollPane(keyInput,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
		keyGuess = new JTextArea(10, 15);
		keyGuess.setEditable(false);
		keyGuess.setBackground(Color.LIGHT_GRAY);
		keyGuess.setLineWrap(true);
		keyGuess.setWrapStyleWord(true);
		JScrollPane keyGuessPane = new JScrollPane(keyGuess,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
        go = new JButton("Decrypt");
        go.addActionListener(this);
        
		JLabel cipherTextLabel = new JLabel("Ciphertext:");
		JLabel plainTextLabel = new JLabel("Plaintext:");
        Box box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalStrut(10));
        box1.add(cipherTextLabel);
        box1.add(ciphertextPane);
        box1.add(Box.createVerticalStrut(10));
        box1.add(plainTextLabel);
        box1.add(plaintextPane);
        //box1.add(Box.createVerticalStrut(10));
        
        JLabel keyInputLabel = new JLabel("Key Input:");
        Box box2 = Box.createVerticalBox();
        box2.add(Box.createVerticalStrut(10));
        box2.add(keyInputLabel);
        box2.add(keyInputPane);
        box2.add(Box.createVerticalStrut(10));
        
        Box box3 = Box.createHorizontalBox();
        box3.add(box1);
        box3.add(Box.createHorizontalStrut(10));
        
        JLabel keyGuessLabel = new JLabel("Key Guess:");
        Box box4 = Box.createVerticalBox();
        box4.add(keyGuessLabel);
        box4.add(keyGuessPane);
        box2.add(box4);
        box3.add(box2);

		Box boxGo = Box.createHorizontalBox();
		boxGo.add(Box.createHorizontalStrut(20));
		boxGo.add(go);
		boxGo.add(Box.createHorizontalStrut(20));
        
        Box box5 = Box.createVerticalBox();
        box5.add(box3);
        box5.add(Box.createVerticalStrut(10));
        box5.add(boxGo);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(box5);
    }

    public static void main(String[] args) {
        DecrypterGUI window = new DecrypterGUI();
        window.setBounds(100, 100, 480, 480);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
