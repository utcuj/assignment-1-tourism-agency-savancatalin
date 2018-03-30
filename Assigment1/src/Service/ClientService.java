package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import DataAccesOnly.ClientDAO;
import Validators.CNPValidator;
import Validators.Validator;
import Model.Client;

public class ClientService {
	

	    // create the list with all the validators
		private List<Validator<Client>> validators;

		public ClientService() {
			validators = new ArrayList<Validator<Client>>();
			validators.add(new CNPValidator());
			
		}

		// find a client by id 
		public Client findClientByID(int id_client) {
			Client client = ClientDAO.findById(id_client);
			if (client == null) {
				String message = "The client with id =" + id_client + " was not found!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				//throw new NoSuchElementException("The client with name =" + name + " was not found!");
			}
			return client;
		}
		public Client findClientByName(String nume) {
			Client client = ClientDAO.findByNume(nume);
			if (client == null) {
				String message = "The client with name =" + nume + " was not found!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				//throw new NoSuchElementException("The client with name =" + name + " was not found!");
			}
			return client;
		}
		public static ArrayList<Client> getClienti() throws SQLException {
			return (ArrayList<Client>) ClientDAO.toate();
		}
		// add a new client to DB 
		public int insertClient(String name, int card_no, String cnp, String address) {
			Client client = new Client(name, card_no, cnp, address);
			try {	
					for (Validator<Client> v : validators) {
								v.validate(client);
					}
					
			} catch(Exception e) {
				String message = "Wrong information about client ! ";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			return ClientDAO.insert(client);
		}
		
		public int updateClient(String name, int card_no, String cnp, String address) {
			Client client = new Client(name, card_no, cnp, address);
			try {	
					for (Validator<Client> v : validators) {
								v.validate(client);
					}
			} catch(Exception e) {
				String message = "Wrong information about client ! ";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			return ClientDAO.update(client);
		}
		
		
		
		
		
}