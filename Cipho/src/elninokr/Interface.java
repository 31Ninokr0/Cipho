package elninokr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interface {

	private JFrame frame;
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;
	
	private JPanel panel;
	private JTextField textField;
	private JTextField txtKey;
	private JLabel lblTextfiletxt;
	private JTextField txtLocationOfFile;
	private JLabel lblCipher;
	private JButton btnEncrypt;
	private JButton btnDecrypt;

	String[] ciphers = {"Alberti", "Atbash", "Caesar", "Keyboard", "Rail Fence", "Route", "Vigenere"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cipho");
		ImageIcon img = new ImageIcon("src/Images/icon.jpg");
		frame.setIconImage(img.getImage());
		frame.getContentPane().setBackground(Color.decode("#ada203"));
		frame.setSize(750,1200);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel1 = new JPanel();
		panel1.setBackground(Color.decode("#FFEF30"));
		
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Images/icon.jpg"));
		label1.setHorizontalAlignment(JLabel.CENTER);
		panel1.add(label1);
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setBackground(new Color(189, 183, 107));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		
		label2 = new JLabel("Text: ", JLabel.CENTER);
		label2.setFont(new Font("Serif", Font.PLAIN, 40));
		label2.setAlignmentX(-13);
		label2.setAlignmentY(0.0f);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setColumns(10);
		
		JLabel label3 = new JLabel("or");
		label3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		JButton btnTextfile = new JButton("Browse");
		btnTextfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				txtLocationOfFile.setText(f.getPath());
				
				
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(f.getPath()));
				} catch (FileNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				StringBuilder stringBuilder = new StringBuilder();
				String line = null;
				String ls = System.getProperty("line.separator");
				try {
					while ((line = reader.readLine()) != null) {
						stringBuilder.append(line);
						stringBuilder.append(ls);
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				// delete the last new line separator
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
				try {
					reader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				textField.setText(stringBuilder.toString());
			}
		});
		btnTextfile.setBackground(Color.LIGHT_GRAY);
		btnTextfile.setFont(new Font("Serif", Font.PLAIN, 27));
		
		final JTextArea txtrOutput = new JTextArea();
		txtrOutput.setEditable(true);
		txtrOutput.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblOutput = new JLabel("Output: ");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		final JComboBox comboBox = new JComboBox(ciphers);
		comboBox.setSelectedIndex(-1);
		comboBox.setFont(new Font("SimSun-ExtB", Font.PLAIN, 35));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == comboBox) {
					JComboBox cb = (JComboBox)e.getSource();
					String msg = (String)cb.getSelectedItem();
					switch (msg) {
						case "Alberti" : txtKey.setText("Letter from the English Alphaet");
							break;
						case "Atbash"  : txtKey.setText("No key Required.");
							break;
						case "Caesar"  : txtKey.setText("Enter Key as a Number");
							break;
						case "Keyboard" : txtKey.setText("No key Required.");
							break;
						case "Rail Fence" : txtKey.setText("Enter key, 1 <= key <= len+1");
							break;
						case "Route" : txtKey.setText("Enter key, 0 < key <= length");
							break;
						case "Vigenere" : txtKey.setText("Enter key as phrase");
							break;
						default: txtKey.setText("Select a Cipher");
					}
				}
			}
		});
		
		txtKey = new JTextField();
		txtKey.setFont(new Font("Tahoma", Font.PLAIN, 33));
		txtKey.setColumns(10);
		
		JLabel lblKey = new JLabel("Key: ");
		lblKey.setFont(new Font("Serif", Font.PLAIN, 30));
		
		lblTextfiletxt = new JLabel("Textfile (.txt): ");
		lblTextfiletxt.setFont(new Font("Tahoma", Font.PLAIN, 34));
		
		txtLocationOfFile = new JTextField();
		txtLocationOfFile.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtLocationOfFile.setText("Location of File");
		txtLocationOfFile.setColumns(10);
		
		lblCipher = new JLabel("Cipher: ");
		lblCipher.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedItem() == "Alberti") {
					txtrOutput.setText("Not yet Completed");
				}else if (comboBox.getSelectedItem() == "Atbash") {
					String temp = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
					Atbash.setUp(temp);
					txtrOutput.setText(Atbash.Cipher(textField.getText()));
				}else if (comboBox.getSelectedItem() == "Caesar") {
					txtrOutput.setText(Caesar.Encrypt(textField.getText(), Integer.parseInt(txtKey.getText())));
				}else if (comboBox.getSelectedItem() == "Keyboard") {
					String test ="";
					for (int i = 0; i < textField.getText().length(); i++) {
						test += Keyboard.Encrypt(textField.getText().charAt(i));
					}
					txtrOutput.setText(test);
				}else if (comboBox.getSelectedItem() == "Rail Fence") {
					txtrOutput.setText(RailFence.Encrypt(textField.getText(), Integer.parseInt(txtKey.getText())));
				}else if (comboBox.getSelectedItem() == "Route") {
					txtrOutput.setText(Route.Encrypt(textField.getText(), Integer.parseInt(txtKey.getText())));
				}else if (comboBox.getSelectedItem() == "Vigenere") {
					txtrOutput.setText(Vigenere.Encrypt(textField.getText(), txtKey.getText()));
				}
					
				
			}
		});
		
		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedItem() == "Alberti") {
					txtrOutput.setText("Not yet Completed");
				}else if (comboBox.getSelectedItem() == "Atbash") {
					String temp = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
					Atbash.setUp(temp);
					txtrOutput.setText(Atbash.Cipher(textField.getText()));
				}else if (comboBox.getSelectedItem() == "Caesar") {
					txtrOutput.setText(Caesar.Decrypt(textField.getText(), Integer.parseInt(txtKey.getText())));
				}else if (comboBox.getSelectedItem() == "Keyboard") {
					String test ="";
					for (int i = 0; i < textField.getText().length(); i++) {
						test += Keyboard.Decrypt(textField.getText().charAt(i));
					}
					txtrOutput.setText(test);
				}else if (comboBox.getSelectedItem() == "Rail Fence") {
					txtrOutput.setText(RailFence.Decrypt(textField.getText(), Integer.parseInt(txtKey.getText())));
				}else if (comboBox.getSelectedItem() == "Route") {
					txtrOutput.setText(Route.Decrypt(textField.getText(), Integer.parseInt(txtKey.getText())));
				}else if (comboBox.getSelectedItem() == "Vigenere") {
					txtrOutput.setText(Vigenere.Decrypt(textField.getText(), txtKey.getText()));
				}
					
				
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(361, Short.MAX_VALUE)
					.addComponent(label3)
					.addGap(348))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOutput)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtrOutput))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTextfiletxt)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtLocationOfFile, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnTextfile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCipher))
								.addComponent(lblKey))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtKey, GroupLayout.PREFERRED_SIZE, 546, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnDecrypt)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)))))
					.addGap(25))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(119)
					.addComponent(btnEncrypt)
					.addContainerGap(477, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(64)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label2)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label3)
							.addGap(14)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTextfiletxt)
								.addComponent(txtLocationOfFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnTextfile))
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCipher)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKey)
						.addComponent(txtKey, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEncrypt)
						.addComponent(btnDecrypt))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(86)
							.addComponent(lblOutput))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(58)
							.addComponent(txtrOutput, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
					.addGap(142))
		);
		panel.setLayout(gl_panel);
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
