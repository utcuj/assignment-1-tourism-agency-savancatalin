package DataAccesOnly;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import ConectionBD.ConexiuneBD;
import Model.Istoric;

public class IstoricDAO {
	
		protected static final Logger LOGGER = Logger.getLogger(IstoricDAO.class.getName());
		private static final String insertStatementString = "INSERT INTO istoric (iduser,`change`)"
				+ " VALUES (?,?)";
		private final static String findStatementString = "SELECT * FROM istoric  where iduser = ?";
		public static Istoric findById(int id) {
			Istoric toReturn = null;
			Connection dbConnection = ConexiuneBD.getConnection();
			PreparedStatement findStatement = null;
			ResultSet rs = null;
			try {
				findStatement = dbConnection.prepareStatement(findStatementString);
				findStatement.setLong(1, id);
				rs = findStatement.executeQuery();
				if (rs.next())
				 {
				String Change=rs.getString("change");
				int idClient=rs.getInt("iduser");
				

				//public User(String Nume,String Prenume , String User ,String Pass,int id,String tipUser)
				toReturn = new Istoric(idClient,Change);
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
		
		public static List<Istoric> toate() throws SQLException {
			int i=0;
			Connection dbConnection = ConexiuneBD.getConnection();
		    String totiUseri = "Select * from istoric ";
		    Statement statement = dbConnection.createStatement();
		    ResultSet rs = statement.executeQuery(totiUseri);
		    
		    List<Istoric> useri = new ArrayList<Istoric>();
		    
		    while(rs.next()){
		    	
		    	Istoric user = new Istoric();
		    	 user.setIdIstoric(rs.getInt("idistoric"));
		    	 user.setIdUser(rs.getInt("iduser"));
		    	 user.setChange(rs.getString("change"));
		    	 user.setTimp(rs.getTimestamp("date"));
		    	 useri.add(user);
		    	 System.out.println("set " +user.getIdIstoric());
		    	 System.out.println("set " +user.getChange());
		    	 System.out.println("set " +user.getTimp());
		    	 System.out.println("DAO"+useri.get(i).getIdIstoric());
		    	 i++;
		    	
		    }
//		     System.out.println("DAO"+useri.get(1).getIdIstoric());
//	    	 System.out.println("DAO"+useri.get(1).getChange());
//	    	 System.out.println("DAO"+useri.get(1).getTimp());
		    useri.forEach(u -> System.out.println("id istoric " + u.getTimp()));
		    dbConnection.close();
		    return useri;
		}
		public static Istoric findByDate(Timestamp d1,Timestamp d2) {
			Istoric toReturn = null;
			Connection dbConnection = ConexiuneBD.getConnection();
			PreparedStatement findStatement = null;
			ResultSet rs = null;
			try {
				findStatement = dbConnection.prepareStatement("Select * from istoric where DATE('date') between ? and ? ");
				findStatement.setTimestamp(1, d1);
				findStatement.setTimestamp(1, d2);
				rs = findStatement.executeQuery();
				if (rs.next())
				 {
				String Change=rs.getString("change");
				int idClient=rs.getInt("iduser");
				toReturn = new Istoric(idClient,Change);
				 }
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"InsertDAO:findByDate " + e.getMessage());
			} finally {
				ConexiuneBD.close(rs);
				ConexiuneBD.close(findStatement);
				ConexiuneBD.close(dbConnection);
			}
			return toReturn;
			
		}
		public static int insert(Istoric istoric) {
			Connection dbConnection = ConexiuneBD.getConnection();
			PreparedStatement insertStatement = null;
			int insertedId = -1;
			try {
				insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setInt(1, istoric.getIdUser());
				insertStatement.setString(2, istoric.getChange());


				insertStatement.executeUpdate();

				ResultSet rs = insertStatement.getGeneratedKeys();
				if (rs.next()) {
					insertedId = rs.getInt(1);
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "IstoricDAO:insert " + e.getMessage());
			} finally {
				ConexiuneBD.close(insertStatement);
				ConexiuneBD.close(dbConnection);
			}
			return insertedId;
		}

		public void deleteIstoricByID(int id)
		{
		     String deleteuser="DELETE FROM istoric where idistoric ="+String.valueOf(id);
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
		public void deleteIstoricByUser(int idUser)
		{
		     String deleteuser="DELETE FROM istoric where iduser ="+String.valueOf(idUser);
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
		public static void deleteIstoricTot()
		{
		    String deleteistoric="DELETE FROM istoric";
			Connection dbConnection = ConexiuneBD.getConnection();
			try{
				Statement stat=dbConnection.createStatement();
				stat.executeUpdate(deleteistoric);
				
			}catch (SQLException e) {
				 e.printStackTrace();
				 JOptionPane.showMessageDialog(null, "Eroare stergere istoric", "Eroare", JOptionPane.ERROR_MESSAGE);
			} finally {
				ConexiuneBD.close(dbConnection);
			}
		}
		
//		public static void main(String[] args) throws SQLException {
//			IstoricDAO isto= new IstoricDAO();
//			
//			isto.toate();
//			
//		}
}