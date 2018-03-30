package DataAccesOnly;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ConectionBD.ConexiuneBD;
import Model.Reservation;

public class ReservationDAO {
	
	protected static final Logger LOGGER = Logger.getLogger(ReservationDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO reservation (idclient,destinatie,hotel,nrpersoane,details,pret_total,payment_day)"
			+ " VALUES (?,?,?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM reservation where idreservation = ?";
	private final static String findStatementStringClient = "SELECT * FROM reservation where idclient = ?";
	public static Reservation findById(int id) {
		Reservation toReturn = null;
		Connection dbConnection = ConexiuneBD.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			if (rs.next())
			 {
			int idc=rs.getInt("idclient");
			String dest=rs.getString("destinatie");
			String hotel=rs.getString("hotel");
			int NrPers=rs.getInt("nrpersoane");
			String Details=rs.getString("details");
			int price=rs.getInt("pret_total");
			Date payment=rs.getDate("payment_day");
			
			

		
			toReturn = new Reservation(dest,hotel, NrPers , Details , price ,payment ,id,idc);
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
	@SuppressWarnings("null")
	public static ArrayList<Reservation> findByIdClient(int id) {
		ArrayList<Reservation> toReturn = null;
		Connection dbConnection = ConexiuneBD.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringClient);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			if (rs.next())
			 {
			int idc=rs.getInt("idclient");
			String dest=rs.getString("destinatie");
			String hotel=rs.getString("hotel");
			int NrPers=rs.getInt("nrpersoane");
			String Details=rs.getString("details");
			int price=rs.getInt("pret_total");
			Date payment=rs.getDate("payment_day");
			
			

		
			toReturn.add( new Reservation(dest,hotel, NrPers , Details , price ,payment ,id,idc));
			 }
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ReservationDAO:findByIdClient " + e.getMessage());
		} finally {
			ConexiuneBD.close(rs);
			ConexiuneBD.close(findStatement);
			ConexiuneBD.close(dbConnection);
		}
		return toReturn;
		
	}
	public static List<Reservation> toate() throws SQLException {
		Connection dbConnection = ConexiuneBD.getConnection();
	    String totiUseri = "Select * from reservation ";
	    Statement statement = dbConnection.createStatement();
	    ResultSet rs = statement.executeQuery(totiUseri);
	    Reservation rezervare;
	    List<Reservation> rezervari = new ArrayList<Reservation>();
	    while(rs.next()){
	    	rezervare=new Reservation();
	    	rezervare.setIdRezervare(rs.getInt("idreservation"));
	    	rezervare.setIdClient(rs.getInt("idclient"));
	    	rezervare.setDestination(rs.getString("destinatie"));
	    	rezervare.setHotelName(rs.getString("hotel"));
	    	rezervare.setNrPersoane(rs.getInt("nrpersoane"));
	    	rezervare.setDetails(rs.getString("details"));
	    	rezervare.setTotalPrice(rs.getInt("pret_total"));
	    	rezervare.setPaymentDate(rs.getDate("payment_day"));
  
	    	
	    	rezervari.add(rezervare);
	    }
	    dbConnection.close();
	    return rezervari;
	}
	public static int insert(Reservation rezervare) {
		Connection dbConnection = ConexiuneBD.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, rezervare.getIdClient());
			insertStatement.setString(2, rezervare.getDestination());
			insertStatement.setString(3, rezervare.getHotelName());
			insertStatement.setInt(4, rezervare.getNrPersoane());
			insertStatement.setString(5, rezervare.getDetails());
			insertStatement.setInt(6, rezervare.getTotalPrice());
			insertStatement.setDate(7, (java.sql.Date) rezervare.getPaymentDate());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservatioDAO:insert " + e.getMessage());
		} finally {
			ConexiuneBD.close(insertStatement);
			ConexiuneBD.close(dbConnection);
		}
		return insertedId;
	}

	public static void deleteReservationByIDClient(int id)
	{
	     String deleteuser="DELETE FROM reservation where idclient ="+String.valueOf(id);
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
	public static void deleteReservationById(int id)
	{
	     String deleteuser="DELETE FROM reservation where idreservation ="+String.valueOf(id);
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
	public static void deleteReservationByDate()
	{
	    String deleteuser="DELETE FROM user where payment_day < CURDATE() ";
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
	
	/*public static void updateRezervare(int id, int cant)
	{
		Connection dbConnection = ConexiuneBD.getConnection();
		PreparedStatement updateStatement = null;

	    String sql = "UPDATE stoc SET cantitate=" +cant+ "  where idprodus=" + id;
		try
		{
			updateStatement=dbConnection.prepareStatement(sql);
	        updateStatement.executeUpdate();
	     
		}catch(SQLException e)
		{
			LOGGER.log(Level.WARNING, "StocDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}*/
}
