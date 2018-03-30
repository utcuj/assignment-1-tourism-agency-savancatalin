package Model;
import java.util.*;

public class Reservation {
	
	private String Destination;
	private String HotelName;
	private int NrPersoane;
	private String Details;
	private int TotalPrice;
	private Date PaymentDate;
	private int idRezervare;
	private int idClient;

	
	public Reservation(String dest,String hotel,int NrPers , String Details , int price ,Date payment ,int idr,int idc)
	{
		
		this.Destination=dest;
		this.HotelName=hotel;
		this.NrPersoane=NrPers;
		this.Details=Details;
		this.TotalPrice=price;
		this.PaymentDate=payment;
		this.idRezervare=idr;
		this.idClient=idc;

	}
	public Reservation(String dest,String hotel,int NrPers , String Details , int price ,Date payment ,int idc)
	{
		
		this.Destination=dest;
		this.HotelName=hotel;
		this.NrPersoane=NrPers;
		this.Details=Details;
		this.TotalPrice=price;
		this.PaymentDate=payment;
		this.idClient=idc;

	}
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public int getIdRezervare() {
		return idRezervare;
	}



	public void setIdRezervare(int idRezervare) {
		this.idRezervare = idRezervare;
	}



	public int getIdClient() {
		return idClient;
	}



	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	


	public String getDestination() {
		return Destination;
	}


	public void setDestination(String destination) {
		Destination = destination;
	}


	public String getHotelName() {
		return HotelName;
	}


	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}


	public int getNrPersoane() {
		return NrPersoane;
	}


	public void setNrPersoane(int nrPersoane) {
		NrPersoane = nrPersoane;
	}


	public String getDetails() {
		return Details;
	}


	public void setDetails(String details) {
		Details = details;
	}


	public int getTotalPrice() {
		return TotalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		TotalPrice = totalPrice;
	}


	public Date getPaymentDate() {
		return PaymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}
	
	public String PaymentCheck(Date paymentDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.DATE, -1);

		if (paymentDate.before(cal.getTime())) {
			return "Inca se mai poate plati pentru rezervare"; //  myDate must be yesterday or earlier
		} else {
			return "Data de plata a trecut , ne pare rau dar rezervarea dumneavoastra a fost anulata!";
		}
		
	}
	
}
