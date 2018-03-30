package Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JOptionPane;

import DataAccesOnly.ClientDAO;
import DataAccesOnly.IstoricDAO;
import Model.Client;
import Model.Istoric;


public class IstoricService {


	// insert new activity performed by an agent into history
	public int insertIstoric(int id_user, String change) {
		Istoric istoric = new Istoric(id_user, change);
		
		return IstoricDAO.insert(istoric);
	}
	public Istoric findByData(Timestamp d1,Timestamp d2) {
		Istoric istoric = IstoricDAO.findByDate( d1, d2);
		if (istoric == null) {
			String message = "The istoric between " + d1 + "and "+ d2 +" was not found!";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			//throw new NoSuchElementException("The client with name =" + name + " was not found!");
		}
		return istoric;
	}
	public static List<Istoric> getIstoric() {
		try {
			
			List<Istoric> user=IstoricDAO.toate();
			user.forEach(u -> {
				System.out.println("getIstoric " + u.getIdIstoric());
			});
//	    	System.out.println("getistoric service"+user.get(0).getIdIstoric());
//	    	System.out.println(user.get(0).getIdIstoric());
//	    	System.out.println(user.get(0).getChange());
//	    	System.out.println(user.get(0).getTimp());
			return IstoricDAO.toate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void deleteIstoricTot() {
		IstoricDAO.deleteIstoricTot();
		
	}


}