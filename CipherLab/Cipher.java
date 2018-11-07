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

public class Cipher extends JFrame
		implements ActionListener
{
	private JTextArea original, encrypted, words;
	private JButton go;

	// Constructor
	public Cipher()
	{
		super("Caesar Cipher");

		setJMenuBar(new CipherMenu(this));
		setupGui();

		original.setText("Type or paste your text here or load it from a file");
		refresh();
	}

	public String getText()
	{
		return original.getText();
	}

	public String getEncrypted() {
		return encrypted.getText();
	}

	public void setText(String text)
	{
		original.setText(text);
	}

	public void refresh()
	{
		String text = original.getText();
		Encryptor encryptor = new Encryptor(text);
		encrypted.setText(encryptor.getEncrypted());
		//words.setText(encryptor.allWordsWith('e'));
	}
 
	// Called when the Refresh burron is clicked
	public void actionPerformed(ActionEvent e)
	{
		refresh();
	}

	// ********************** GUI setup ********************************

	private void setupGui()
	{
		original = new JTextArea(10, 20);
		original.setLineWrap(true);
		original.setWrapStyleWord(true);
		JScrollPane originalPane = new JScrollPane(original,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		encrypted = new JTextArea(10, 20);
		encrypted.setEditable(false);
		encrypted.setBackground(Color.LIGHT_GRAY);
		encrypted.setLineWrap(true);
		encrypted.setWrapStyleWord(true);
		JScrollPane encryptedPane = new JScrollPane(encrypted,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		/*
		words = new JTextArea(10, 10);
		words.setEditable(false);
		words.setBackground(Color.LIGHT_GRAY);
		JScrollPane wordsPane = new JScrollPane(words,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		*/
		go = new JButton("Refresh");
		go.addActionListener(this);

		Box box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(10));
		box1.add(originalPane);
		box1.add(Box.createVerticalStrut(10));
		box1.add(encryptedPane);
		box1.add(Box.createVerticalStrut(10));
		box1.add(go);
		//Box box2 = Box.createVerticalBox();
		//box2.add(Box.createVerticalStrut(10));
		//box2.add(wordsPane);
		//box2.add(Box.createVerticalStrut(40));
		Box box3 = Box.createHorizontalBox();
		box3.add(box1);
		box3.add(Box.createHorizontalStrut(20));
		//box3.add(box2);

		Container c = getContentPane();
		c.setLayout (new FlowLayout());
		c.add(box3);
	}

	public static void main(String[] args)
	{
		Cipher window = new Cipher();
		window.setBounds(100, 100, 480, 480);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
