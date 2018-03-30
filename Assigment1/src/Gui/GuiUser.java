package Gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import javax.swing.JButton;

import Controller.ControllerUser;

import Model.Client;
import Model.Reservation;

import Service.ClientService;
import Service.ReservationService;


import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiUser {

	private JFrame frmAdmin;
	private JPanel panel = new JPanel();
	private JTable tabelaClienti;
	private JTextField textNume;
	private JTextField textCard;
	private JTextField textCNP;
	private JTextField textAdresa;
	private JTextField textDestinatie;
	private JTextField textHotel;
	private JTextField textNrPers;
	private JTextField textDetali;
	private JTextField textPretTotal;
	private JTextField textPayDate;
	private DefaultTableModel clientGrafic;
	private JTextField textField;
	private ControllerUser rezervare;
	private ControllerUser client;
	private JTable table;
	private DefaultTableModel RezGrafic;

	/**
	 * Launch the application.
	 */
	public void pornireUser(int user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiUser window = new GuiUser(user);
					window.frmAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiUser(int id) {
		rezervare=new ControllerUser();
		client=new ControllerUser();
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		frmAdmin = new JFrame("Logare");
		frmAdmin.setTitle("User");
		frmAdmin.setBounds(100, 100, 1109, 670);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdmin.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 1079, 623);
		frmAdmin.getContentPane().add(panel);
		panel.setLayout(null);
		
		tabelaClienti = new JTable();
		
		
		clientGrafic = new DefaultTableModel();
	    clientGrafic.addColumn("Nume");
	    clientGrafic.addColumn("ID Client");
	    clientGrafic.addColumn("Adresa");
	    clientGrafic.addColumn("CNP");
	    clientGrafic.addColumn("IdCont");
	    
	    JScrollPane clientsp = new JScrollPane();
	    clientsp.setBounds(0, 0, 300, 500);
	    clientsp.getViewport().add(tabelaClienti);
	    add(clientsp);
	    
	    tabelaClienti.setModel(clientGrafic);
		tabelaClienti.setBounds(26, 314, 515, 236);
		panel.add(tabelaClienti);
		
		JLabel labelClienti = new JLabel("Clienti");
		labelClienti.setBounds(26, 259, 133, 27);
		panel.add(labelClienti);
		
		JButton btnAddclient = new JButton("AddClient");
		btnAddclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddclient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("userid "+id);
				
				client.insertClient(id, textNume.getText(), Integer.parseInt(textCard.getText()), textCNP.getText(), textAdresa.getText());
				JOptionPane.showMessageDialog(null, "A fost creat un nou client", "Update", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAddclient.setBounds(12, 25, 113, 25);
		panel.add(btnAddclient);
		
		textNume = new JTextField();
		textNume.setBounds(125, 41, 116, 22);
		panel.add(textNume);
		textNume.setColumns(10);
		
		textCard = new JTextField();
		textCard.setBounds(263, 41, 116, 22);
		panel.add(textCard);
		textCard.setColumns(10);
		
		textCNP = new JTextField();
		textCNP.setBounds(398, 41, 116, 22);
		panel.add(textCNP);
		textCNP.setColumns(10);
		
		textAdresa = new JTextField();
		textAdresa.setBounds(543, 41, 116, 22);
		panel.add(textAdresa);
		textAdresa.setColumns(10);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(143, 13, 56, 16);
		panel.add(lblNume);
		
		JLabel lblCard = new JLabel("Card");
		lblCard.setBounds(286, 13, 56, 16);
		panel.add(lblCard);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(414, 12, 56, 16);
		panel.add(lblCnp);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(553, 13, 56, 16);
		panel.add(lblAdresa);
		
		JButton btnAddrezervare = new JButton("AddRezervare");
		btnAddrezervare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.out.println("userid "+id);
				
				rezervare.addReservation(id,Integer.parseInt(textField.getText()), textDestinatie.getText(), textHotel.getText(),
						Integer.parseInt(textNrPers.getText()), textDetali.getText(), Integer.parseInt(textPretTotal.getText()),textPayDate.getText());
				JOptionPane.showMessageDialog(null, "A fost creata o noua rezervare", "Update", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAddrezervare.setBounds(12, 106, 113, 25);
		panel.add(btnAddrezervare);
		
		textDestinatie = new JTextField();
		textDestinatie.setBounds(125, 107, 116, 22);
		panel.add(textDestinatie);
		textDestinatie.setColumns(10);
		
		textHotel = new JTextField();
		textHotel.setColumns(10);
		textHotel.setBounds(263, 107, 116, 22);
		panel.add(textHotel);
		
		textNrPers = new JTextField();
		textNrPers.setColumns(10);
		textNrPers.setBounds(398, 107, 116, 22);
		panel.add(textNrPers);
		
		textDetali = new JTextField();
		textDetali.setColumns(10);
		textDetali.setBounds(543, 107, 116, 22);
		panel.add(textDetali);
		
		textPretTotal = new JTextField();
		textPretTotal.setColumns(10);
		textPretTotal.setBounds(125, 145, 116, 22);
		panel.add(textPretTotal);
		
		textPayDate = new JTextField();
		textPayDate.setColumns(10);
		textPayDate.setBounds(263, 145, 116, 22);
		panel.add(textPayDate);
		
		JLabel lblDestinatie = new JLabel("Destinatie");
		lblDestinatie.setBounds(143, 87, 56, 16);
		panel.add(lblDestinatie);
		
		JLabel lblHotel = new JLabel("Hotel");
		lblHotel.setBounds(287, 87, 56, 16);
		panel.add(lblHotel);
		
		JLabel lblNrPersoane = new JLabel("Nr Persoane");
		lblNrPersoane.setBounds(408, 87, 77, 16);
		panel.add(lblNrPersoane);
		
		JLabel lblDetali = new JLabel("Detali");
		lblDetali.setBounds(553, 87, 56, 16);
		panel.add(lblDetali);
		
		JLabel lblPretTotal = new JLabel("Pret Total");
		lblPretTotal.setBounds(143, 128, 56, 16);
		panel.add(lblPretTotal);
		
		JLabel lblPaymentDate = new JLabel("Payment Date");
		lblPaymentDate.setBounds(273, 128, 93, 16);
		panel.add(lblPaymentDate);
		

		JButton btnAfisareDetali = new JButton("Afisare Detali");
		btnAfisareDetali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAfisareDetali.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					afiseazaClientulInTabel();
					afiseazaRezervariInTabel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAfisareDetali.setBounds(481, 568, 128, 25);
		panel.add(btnAfisareDetali);
		
		JLabel lblIdClient = new JLabel("ID Client");
		lblIdClient.setBounds(26, 285, 56, 16);
		panel.add(lblIdClient);
		
		JLabel lblNumeprenume = new JLabel("NumePrenume");
		lblNumeprenume.setBounds(115, 285, 84, 16);
		panel.add(lblNumeprenume);
		
		JLabel lblIdCard = new JLabel("Id Card");
		lblIdCard.setBounds(248, 285, 56, 16);
		panel.add(lblIdCard);
		
		JLabel lblCnp_1 = new JLabel("CNP");
		lblCnp_1.setBounds(345, 285, 56, 16);
		panel.add(lblCnp_1);
		
		JLabel lblAdresa_1 = new JLabel("Adresa");
		lblAdresa_1.setBounds(447, 285, 56, 16);
		panel.add(lblAdresa_1);
		
		textField = new JTextField();
		textField.setBounds(398, 145, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblIdClient_1 = new JLabel("Id Client");
		lblIdClient_1.setBounds(414, 128, 56, 16);
		panel.add(lblIdClient_1);
		

		
		table = new JTable();
		RezGrafic = new DefaultTableModel();
		RezGrafic.addColumn("Nume");
		RezGrafic.addColumn("ID Client");
		RezGrafic.addColumn("Adresa");
		RezGrafic.addColumn("CNP");
		RezGrafic.addColumn("IdCont");
	    
	    JScrollPane rezsp = new JScrollPane();
	    rezsp.setBounds(0, 0, 300, 500);
	    rezsp.getViewport().add(table);
	    add(rezsp);
	    
	    table.setModel(RezGrafic);
		
		table.setBounds(553, 314, 514, 236);
		panel.add(table);
		
		JLabel lblRezervari = new JLabel("Rezervari");
		lblRezervari.setBounds(604, 264, 56, 16);
		panel.add(lblRezervari);
		
		JLabel lblDestinatie_1 = new JLabel("Destinatie");
		lblDestinatie_1.setBounds(553, 285, 56, 16);
		panel.add(lblDestinatie_1);
		
		JLabel lblNrpersoane = new JLabel("NrPersoane");
		lblNrpersoane.setBounds(653, 285, 77, 16);
		panel.add(lblNrpersoane);
		
		JLabel lblPret = new JLabel("Pret");
		lblPret.setBounds(768, 285, 56, 16);
		panel.add(lblPret);
		
		JLabel lblDataPlata = new JLabel("Data Plata");
		lblDataPlata.setBounds(850, 285, 66, 16);
		panel.add(lblDataPlata);
		
		JLabel lblIdClient_2 = new JLabel("Id Client");
		lblIdClient_2.setBounds(972, 285, 56, 16);
		panel.add(lblIdClient_2);
		
		JButton btnUpdateclient = new JButton("UpdateClient");
		btnUpdateclient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				client.updateClient(id, textNume.getText(), Integer.parseInt(textCard.getText()), textCNP.getText(), textAdresa.getText());
				JOptionPane.showMessageDialog(null, "Clientul cu cnp.ul"+textCNP.getText()+"a fost updatat", "Update", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnUpdateclient.setBounds(12, 51, 113, 25);
		panel.add(btnUpdateclient);
	}
	
	private void add(JScrollPane clientsp) {
		// TODO Auto-generated method stub
		
	}

	  public void afiseazaClientulInTabel() throws SQLException  {
		    clientGrafic.setRowCount(0);
				for (Object client : ClientService.getClienti()) {

				  String[] OB = {Integer.toString(((Client) client).getIdClient()),((Client) client).getNume(),Integer.toString(((Client) client).getIDcard()),
						  ((Client) client).getPersonalCode(),((Client)client).getAddress()};
				  
				clientGrafic.addRow(OB);
				}
		  }
	  
	  public void afiseazaRezervariInTabel() throws SQLException  {
		  RezGrafic.setRowCount(0);
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				for (Object rezervari : ReservationService.getReservations()) {

				  String[] OB = {((Reservation) rezervari).getDestination(),Integer.toString(((Reservation) rezervari).getNrPersoane()),
						  Integer.toString(((Reservation) rezervari).getTotalPrice()),
						  dateFormat.format(((Reservation) rezervari).getPaymentDate()),Integer.toString(((Reservation)rezervari).getIdClient())};
				  
				  RezGrafic.addRow(OB);
				}
		  }
}