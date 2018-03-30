package DataAccesOnly;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ConectionBD.ConexiuneBD;
import Model.User;

public class UserDAO {
	
		protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
		private static final String insertStatementString = "INSERT INTO user (nume,prenume,user,password,type)"
				+ " VALUES (?,?,?,?,?)";
		private final static String findStatementString = "SELECT * FROM user where iduser = ?";
		private final static String findStatementStringUser = "SELECT * FROM user where username = ? and password =?";
		public static User findById(int id) {
			User toReturn = null;
			Connection dbConnection = ConexiuneBD.getConnection();
			PreparedStatement findStatement = null;
			ResultSet rs = null;
			try {
				findStatement = dbConnection.prepareStatement(findStatementString);
				findStatement.setLong(1, id);
				rs = findStatement.executeQuery();
				if (rs.next())
				 {
				String Nume=rs.getString("nume");
				String Prenume=rs.getString("prenume");
				String user=rs.getString("username");
				String Pass=rs.getString("password");
				String tipUser=rs.getString("type");
				

				//public User(String Nume,String Prenume , String User ,String Pass,int id,String tipUser)
				toReturn = new User(Nume,Prenume ,user ,Pass,id,tipUser);
				 }
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"UserDAO:findByIdUser " + e.getMessage());
			} finally {
				ConexiuneBD.close(rs);
				ConexiuneBD.close(findStatement);
				ConexiuneBD.close(dbConnection);
			}
			return toReturn;
			
		}
		public static User findByUsername(String username , String pass) {
			User toReturn = null;
			Connection dbConnection = ConexiuneBD.getConnection();
			PreparedStatement findStatement = null;
			ResultSet rs = null;
			try {
				findStatement = dbConnection.prepareStatement(findStatementStringUser);
				findStatement.setString(1, username);
				findStatement.setString(2, pass);
				rs = findStatement.executeQuery();
				 if (rs.next())
				 {
				String Nume=rs.getString("nume");
				String Prenume=rs.getString("prenume");
				String tipUser=rs.getString("type");
				int iduser=rs.getInt("iduser");
				 

				//public User(String Nume,String Prenume , String User ,String Pass,int id,String tipUser)
				toReturn = new User(pass,Nume,Prenume ,username ,iduser,tipUser);
				 }
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"UserDAO:findByUserPass " + e.getMessage());
			} finally {
				ConexiuneBD.close(rs);
				ConexiuneBD.close(findStatement);
				ConexiuneBD.close(dbConnection);
			}
			return toReturn;
			
		}
		public static List<User> toate() throws SQLException {
			Connection dbConnection = ConexiuneBD.getConnection();
		    String totiUseri = "Select * from user where type = 'user'";
		    Statement statement = dbConnection.createStatement();
		    ResultSet rs = statement.executeQuery(totiUseri);
		    User user;
		    List<User> useri = new ArrayList<User>();
		    while(rs.next()){
		    	 user=new User();
		    	 user.setIdUser(rs.getInt("iduser"));
		    	 user.setNume(rs.getString("nume"));
		    	 user.setPrenume(rs.getString("prenume"));
		    	 user.setUser(rs.getString("username"));
		    	 user.setPassword(rs.getString("password"));
		    	 useri.add(user);
		    }
		    dbConnection.close();
		    return useri;
		}
		public static int insert(User user) {
			Connection dbConnection = ConexiuneBD.getConnection();

			PreparedStatement insertStatement = null;
			int insertedId = -1;
			try {
				insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setString(1, user.getNume());
				insertStatement.setString(2, user.getPrenume());
				insertStatement.setString(3, user.getUser());
				insertStatement.setString(4, user.getPassword());
				insertStatement.setString(5, user.getTipUser());
				insertStatement.executeUpdate();

				ResultSet rs = insertStatement.getGeneratedKeys();
				if (rs.next()) {
					insertedId = rs.getInt(1);
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
			} finally {
				ConexiuneBD.close(insertStatement);
				ConexiuneBD.close(dbConnection);
			}
			return insertedId;
		}

		public void deleteUserByID(int id)
		{
		     String deleteuser="DELETE FROM user where iduser ="+String.valueOf(id);
			Connection dbConnection = ConexiuneBD.getConnection();
			try{
				Statement stat=dbConnection.createStatement();
				stat.executeUpdate(deleteuser);
				
			}catch (SQLException e) {
				 e.printStackTrace();
			} finally {
				ConexiuneBD.close(dbConnection);
			}
		}
		public void deleteUserByName(String nume)
		{
		     String deleteuser="DELETE FROM user where nume ="+String.valueOf(nume);
			Connection dbConnection = ConexiuneBD.getConnection();
			try{
				Statement stat=dbConnection.createStatement();
				stat.executeUpdate(deleteuser);
				
			}catch (SQLException e) {
				 e.printStackTrace();
			} finally {
				ConexiuneBD.close(dbConnection);
			}
		}
		public void deleteUserByPrenume(String Prenume)
		{
		     String deleteuser="DELETE FROM user where prenume ="+String.valueOf(Prenume);
			Connection dbConnection = ConexiuneBD.getConnection();
			try{
				Statement stat=dbConnection.createStatement();
				stat.executeUpdate(deleteuser);
				
			}catch (SQLException e) {
				 e.printStackTrace();
			} finally {
				ConexiuneBD.close(dbConnection);
			}
		}
		public void deleteUserByUserName(String UserName)
		{
		     String deleteuser="DELETE FROM user where usename ="+String.valueOf(UserName);
			Connection dbConnection = ConexiuneBD.getConnection();
			try{
				Statement stat=dbConnection.createStatement();
				stat.executeUpdate(deleteuser);
				
			}catch (SQLException e) {
				 e.printStackTrace();
			} finally {
				ConexiuneBD.close(dbConnection);
			}
		}
}
