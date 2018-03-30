package Model;

import java.sql.Timestamp;

public class Istoric {

	public  int idIstoric;
	public  int idUser;
	public  String Change;
	public  Timestamp timp;
	
	
	public Istoric(int idIstoric, int idUser, String change, Timestamp timp) {
		this.idIstoric = idIstoric;
		this.idUser = idUser;
		this.Change = change;
		this.timp = timp;
	}
	public Istoric(int idUser, String change) {

		this.idUser = idUser;
		Change = change;

	}

	public Istoric() {
		// TODO Auto-generated constructor stub
	}
	public int getIdIstoric() {
		return idIstoric;
	}


	public void setIdIstoric(int idIstoric) {
		this.idIstoric = idIstoric;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getChange() {
		return Change;
	}


	public void setChange(String change) {
		Change = change;
	}


	public Timestamp getTimp() {
		return timp;
	}


	public void setTimp(Timestamp timp) {
		this.timp = timp;
	}
}
//SELECT UPDATE_TIME
//FROM   information_schema.tables
//WHERE  TABLE_SCHEMA = 'dbname'
//   AND TABLE_NAME = 'tabname'