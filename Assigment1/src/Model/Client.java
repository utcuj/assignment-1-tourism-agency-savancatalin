package Model;

public class Client {
	private String Nume;
	private int IDcard;
	private String personalCode;
	private String address;
	private int idClient;
	
	
	public int getIdClient() {
		return idClient;
	}


	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public Client(){
		
	}
	public Client( String Nume , int id , String code , String address) {
		this.Nume=Nume;
		this.IDcard=id;
		this.personalCode=code;
		this.address=address;

	}

	public Client( String Nume , int id , String code , String address,int idClient) {
		this.Nume=Nume;
		this.IDcard=id;
		this.personalCode=code;
		this.address=address;
		this.idClient=idClient;
	}


	public String getNume() {
		return Nume;
	}


	public void setNume(String nume) {
		Nume = nume;
	}


	public int getIDcard() {
		return IDcard;
	}


	public void setIDcard(int iDcard) {
		IDcard = iDcard;
	}


	public String getPersonalCode() {
		return personalCode;
	}


	public void setPersonalCode(String personalCode) {
		this.personalCode = personalCode;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	

}
