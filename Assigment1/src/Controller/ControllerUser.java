package Controller;

import Service.ClientService;
import Service.IstoricService;
import Service.ReservationService;

public class ControllerUser {
	
	private ClientService client;
	private IstoricService istoric;
	private ReservationService rezervare;
	
	public ControllerUser() {
		client=new ClientService();
		istoric= new IstoricService();
		rezervare=new ReservationService();
	}
	
	public void insertClient(int id_user ,String name, int card_no, String cnp, String address) {
		
		client.insertClient(name, card_no, cnp, address);
		istoric.insertIstoric(id_user, "adaugare Client");
		
		
	}
	public void updateClient(int id_user ,String name, int card_no, String cnp, String address) {
		
		client.updateClient(name, card_no, cnp, address);
		istoric.insertIstoric(id_user, "update Client "+name);
		
		
	}
	public void addReservation(int id_user ,int id_client, String destination, String hotel, int nr_persons, String details, int tot_price, String final_payment_data) {
		
		rezervare.addReservation(id_client, destination, hotel, nr_persons,details,tot_price,final_payment_data);
		istoric.insertIstoric(id_user, "adaugare rezervare");
		
		
	}
	public void deleteIstoricData(){
		
		istoric.deleteIstoricTot();
		
		
	}

}
