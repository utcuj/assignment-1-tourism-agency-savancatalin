
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import javax.swing.JButton;

import Model.Istoric;
import Model.User;
import Service.IstoricService;


import javax.swing.table.DefaultTableModel;

import Controller.ControllerUser;
import DataAccesOnly.UserDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;

public class guiu {

	private JFrame frmAdmin;
	private JPanel panel = new JPanel();
	private JTable table;
	private JTable table_1;
	private JLabel lblUseri;
	private JLabel lblNoutati;
	private JButton btnAfisareUseri;
	private JButton btnAfisareNoutati;
	private DefaultTableModel userGrafic;
	private DefaultTableModel changeGrafic;
	private JButton btnStergeNoutati;
	private JTextField data1;
	private JTextField data2;
	private JButton btnNouIntreDate;


	/**
	 * Launch the application.
	 */
	public void pornireAdmin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiu window = new guiu();
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
	public guiu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frmAdmin = new JFrame("Logare");
		frmAdmin.setTitle("Admin");
		frmAdmin.setBounds(100, 100, 795, 569);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdmin.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 777, 522);
		frmAdmin.getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
//		userGrafic = new DefaultTableModel();
//		userGrafic.addColumn("ID");
//		userGrafic.addColumn("Nume");
//		userGrafic.addColumn("Prenume");
//	    
//	    JScrollPane clientsp = new JScrollPane();
//	    clientsp.setBounds(0, 0, 300, 500);
//	    clientsp.getViewport().add(table);
//	    add(clientsp);
//	    
//	    table.setModel(userGrafic);
		table.setBounds(73, 165, 268, 279);
		panel.add(table);
		
		
		
		table_1 = new JTable();
//		changeGrafic = new DefaultTableModel();
//		changeGrafic.addColumn("ID");
//		changeGrafic.addColumn("iduser");
//		changeGrafic.addColumn("change");
//	    
//	    JScrollPane clientsp1 = new JScrollPane();
//	    clientsp1.setBounds(0, 0, 300, 500);
//	    clientsp1.getViewport().add(table_1);
//	    add(clientsp1);
//	    
//	    table_1.setModel(changeGrafic);
		table_1.setBounds(452, 165, 268, 279);
		panel.add(table_1);
		
		
		lblUseri = new JLabel("Useri");
		lblUseri.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblUseri.setBounds(73, 119, 147, 36);
		panel.add(lblUseri);
		
		lblNoutati = new JLabel("Noutati");
		lblNoutati.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNoutati.setBounds(452, 119, 147, 36);
		panel.add(lblNoutati);
		
		btnAfisareUseri = new JButton("Afisare Useri");
		btnAfisareUseri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					afiseazaUseriInTabel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAfisareUseri.setBounds(196, 127, 145, 25);
		panel.add(btnAfisareUseri);
		
		btnAfisareNoutati = new JButton("Afisare Noutati");
		btnAfisareNoutati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					afiseazaChangeInTabel();
				} catch (SQLException e1) {
					String message = "eroare afisare noutati";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});
		btnAfisareNoutati.setBounds(575, 127, 145, 25);
		panel.add(btnAfisareNoutati);
		
		btnStergeNoutati = new JButton("Sterge Noutati");
		btnStergeNoutati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerUser rezervari1 = new ControllerUser();
				rezervari1.deleteIstoricData();
				JOptionPane.showMessageDialog(null, "Noutatile au fost sterse", "Update", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnStergeNoutati.setBounds(575, 104, 145, 25);
		panel.add(btnStergeNoutati);
		
		data1 = new JTextField();
		data1.setBounds(452, 61, 116, 22);
		panel.add(data1);
		data1.setColumns(10);
		
		data2 = new JTextField();
		data2.setBounds(586, 61, 116, 22);
		panel.add(data2);
		data2.setColumns(10);
		
		btnNouIntreDate = new JButton("Nou intre Date");
		btnNouIntreDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnNouIntreDate.setBounds(452, 96, 122, 25);
		panel.add(btnNouIntreDate);
	}

	private void add(JScrollPane clientsp) {
		// TODO Auto-generated method stub
		
	}
	
	  public void afiseazaUseriInTabel() throws SQLException  {
		    userGrafic.setRowCount(0);

				for (Object user : UserDAO.toate()) {

				  String[] OB = {Integer.toString(((User) user).getIdUser()),(((User) user).getNume()),(((User) user).getPrenume())};
				  
				userGrafic.addRow(OB);
				}
		  }
	  

	public void afiseazaChangeInTabel() throws SQLException  {
		  changeGrafic.setRowCount(0);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				for (Istoric change : IstoricService.getIstoric()) {
					
				
			String[] OB = {Integer.toString(((Istoric) change).getIdUser()),					
					(((Istoric) change).getChange()),dateFormat.format(((Istoric) change).getTimp())};
				  
				  changeGrafic.addRow(OB);
				}
		  }
}
