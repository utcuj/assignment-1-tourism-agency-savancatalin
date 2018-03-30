package Gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;


import Service.UserService;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GuiLogin {

	private JFrame frame;
	private JTextField Username;
	private JTextField Password;
	private JPanel panel = new JPanel();
	private JButton LoginButton = new JButton("Login");
	private UserService user;
	private GuiAdmin GA;
	private GuiUser GU;
	public int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiLogin window = new GuiLogin();
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
	public GuiLogin() {
		user = new UserService();
		GA= new GuiAdmin();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Logare");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Username = new JTextField();
		Username.setBounds(128, 54, 174, 22);
		Username.setColumns(10);
		
		Password = new JTextField();
		Password.setBounds(128, 94, 174, 22);
		Password.setColumns(10);
		LoginButton.setBounds(160, 129, 128, 52);
		LoginButton.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("merge , username "+Username.getText()+" Password "+Password.getText());
				if (user.signIn(Username.getText(), Password.getText())!=null)
				{
					if (user.signIn(Username.getText(), Password.getText()).equals("admin") )
					{
						GA.pornireAdmin();;
					}		
					if (user.signIn(Username.getText(), Password.getText()).equals("user") )
					{
						System.out.println("iduser "+user.getUserId(Username.getText(), Password.getText()));
						GU= new GuiUser(user.getUserId(Username.getText(), Password.getText()));
						GU.pornireUser(user.getUserId(Username.getText(), Password.getText()));;
					}
					
				}
				else
				{
					String message = "Nu Exista acest utilizator ";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frame.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 420, 269);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.add(LoginButton);
		panel.add(Password);
		panel.add(Username);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(37, 57, 79, 16);
		panel.add(lblUsername);
		
		JLabel lblPasswoed = new JLabel("Password");
		lblPasswoed.setBounds(37, 97, 79, 16);
		panel.add(lblPasswoed);
	}
}