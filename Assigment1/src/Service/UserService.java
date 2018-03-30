package Service;

import DataAccesOnly.UserDAO;
import Model.User;

public class UserService {
	public User user;
	
	// sign up
	public void signUp(String username, String password, String nume,  String prenume,String type) {
		user = new User(nume, prenume, username, password,type);
		
		UserDAO.insert(user);
	}
	
	// sign in, save the user's data for further use and return its type
	public String signIn(String username, String password) {
		user = UserDAO.findByUsername(username, password);
		
		if (user != null) {
			return user.getTipUser();
		}
		return null;
	}
	
	public int getUserId(String username, String password) {
		user = UserDAO.findByUsername(username, password);
		return user.getIdUser();
	}

}