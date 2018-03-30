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
import Model.Client;

public class ClientDAO{
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (nume,idcard,cnp,adresa)"
			+ " VALUES (?,?,?,?)";	
	private final static String findStatementString = "SELECT * FROM client where idClient = ?";
	private final static String findStatementStringID = "SELECT * FROM client where nume = ?";
	public static Client findById(int id) {
		Client toReturn = null;
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
			int IDcard=Integer.parseInt(rs.getString("idcard"));
			String personalCode=rs.getString("cnp");
			String address=rs.getString("adresa");
			//public Client( String Nume , int id , int code , String address,int idClient)
			toReturn = new Client(Nume, IDcard, personalCode,address,id);
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
	public static Client findByNume(String nume) {
		Client toReturn = null;
		Connection dbConnection = ConexiuneBD.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringID);
			findStatement.setString(1, nume);
			rs = findStatement.executeQuery();
			if (rs.next())
			 {
			int id=rs.getInt("idclient");
			int IDcard=Integer.parseInt(rs.getString("idcard"));
			String personalCode=rs.getString("cnp");
			String address=rs.getString("adresa");
			//public Client( String Nume , int id , int code , String address,int idClient)
			toReturn = new Client(nume, IDcard, personalCode,address,id);
			 }
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findByNume " + e.getMessage());
		} finally {
			ConexiuneBD.close(rs);
			ConexiuneBD.close(findStatement);
			ConexiuneBD.close(dbConnection);
		}
		return toReturn;
		
	}
	public static List<Client> toate() throws SQLException {
		Connection dbConnection = ConexiuneBD.getConnection();
	    String tot = "Select * from client";
	    Statement statement = dbConnection.createStatement();
	    ResultSet rs = statement.executeQuery(tot);
	    Client client;
	    List<Client> clienti = new ArrayList<Client>();
	    while(rs.next()){
	         client=new Client();
	         client.setIdClient(rs.getInt("idclient"));
	         client.setNume(rs.getString("nume"));
	         client.setIDcard(rs.getInt("idcard"));
	         client.setPersonalCode(rs.getString("cnp"));
	         client.setAddress(rs.getString("adresa"));
	         clienti.add(client);
	    }
	    dbConnection.close();
	    return clienti;
	}
	public static int insert(Client user) {
		Connection dbConnection = ConexiuneBD.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			//idClient,nume,idcard,cnp,adresa
			insertStatement.setString(1, user.getNume());
			insertStatement.setString(2, String.valueOf(user.getIDcard()));
			insertStatement.setString(3, user.getPersonalCode());
			insertStatement.setString(4, user.getAddress());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConexiuneBD.close(insertStatement);
			ConexiuneBD.close(dbConnection);
		}
		return insertedId;
	}
	public static int update(Client user) {
		Connection dbConnection = ConexiuneBD.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement("UPDATE client SET idcard=?, nume=?, adresa=? WHERE cnp=?",Statement.RETURN_GENERATED_KEYS);
			//idClient,nume,idcard,cnp,adresa
			insertStatement.setString(1, String.valueOf(user.getIDcard()));
			insertStatement.setString(4, user.getPersonalCode());
			insertStatement.setString(3, user.getAddress());
			insertStatement.setString(2, user.getNume());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "update client " + e.getMessage());
		} finally {
			ConexiuneBD.close(insertStatement);
			ConexiuneBD.close(dbConnection);
		}
		return insertedId;
	}
	public void deleteUserByID(int id)
	{
	     String deleteuser="DELETE FROM client where idclient ="+String.valueOf(id);
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
	     String deleteuser="DELETE FROM client where nume ="+String.valueOf(nume);
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
