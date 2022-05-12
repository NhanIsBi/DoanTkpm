package DoAn.Model;

public class Account {
	private String username;
	private String pass;
	private String vitri;
	
	public Account() {
	}
	public Account(String username, String pass,String vitri) {
		this.username=username;
		this.pass=pass;
		this.vitri=vitri;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass=pass;
	}
	
	public String getVitri() {
		return vitri;
	}
	public void setVitri(String vitri) {
		this.vitri=vitri;
	}
}
