package Model;

public class User {
	
	private String Nume;
	private	String Prenume;
	private String User;
	private String Password;
	private String tipUser;
	private int idUser;
	
	
	public User(String Nume,String Prenume , String User ,String Pass,int id,String tipUser) {
		this.Nume=Nume;
		this.Prenume = Prenume;
		this.User=User;
		this.Password=Pass;
		this.idUser=id;
		this.tipUser=tipUser;
		
	}
	public User(String Nume,String Prenume , String User ,String Pass,String tipUser) {
		this.Nume=Nume;
		this.Prenume = Prenume;
		this.User=User;
		this.Password=Pass;
		this.tipUser=tipUser;
		
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getTipUser() {
		return tipUser;
	}
	public void setTipUser(String tipUser) {
		this.tipUser = tipUser;
	}
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getNume() {
		return Nume;
	}

	public void setNume(String nume) {
		Nume = nume;
	}

	public String getPrenume() {
		return Prenume;
	}

	public void setPrenume(String prenume) {
		Prenume = prenume;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
