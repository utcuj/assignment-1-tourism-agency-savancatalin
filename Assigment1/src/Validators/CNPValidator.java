package Validators;

import javax.swing.JOptionPane;

import Model.Client;

public class CNPValidator implements Validator<Client> {
		
		public void validate(Client client) {
				
				String message;
				
				if(!client.getPersonalCode().matches("^[0-9]{13}$")) { // match numbers	
					message = "Wrong CNP number format";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
	
			}
}



