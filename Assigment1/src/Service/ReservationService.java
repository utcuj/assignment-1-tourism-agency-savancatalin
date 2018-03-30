package Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataAccesOnly.ReservationDAO;
import Model.Reservation;
import Validators.DateValidator;
import Validators.Validator;

public class ReservationService {	
	
	// add reservation for a client
	public void addReservation(int id_client, String destination, String hotel, int nr_persons, String details, int tot_price, String final_payment_data) {
		Validator<String> validator;
		try {
			
			validator = new DateValidator();
			validator.validate(final_payment_data);
			Date f_payment_date = Date.valueOf(final_payment_data);
			
			Reservation reservation = new Reservation(destination, hotel, nr_persons, details, tot_price, f_payment_date , id_client);
			ReservationDAO.insert(reservation);
		} catch(Exception e){}
	}
	
	// view reservations for a client
	public Reservation getReservations(int id_rezervare) {
		return ReservationDAO.findById(id_rezervare);
	}
	public ArrayList<Reservation> getReservationsClient(int id_client) {
		return ReservationDAO.findByIdClient(id_client);
	}
	public static List<Reservation> getReservations() {
		try {
			return ReservationDAO.toate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// delete a reservation for a client
	public void deleteReservID(int id_reserv) {
		ReservationDAO.deleteReservationById(id_reserv);
	}
	
	// View all the clients who missed the final payment deadline 
	public void deleteReservationData() {
		ReservationDAO.deleteReservationByDate();
	}
	
	
	
	

}