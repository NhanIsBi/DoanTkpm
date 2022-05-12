package builder;

import DoAn.Model.Account;

public class AccountBuilder {
	private String username;
	private String pass;
	private String vitri;
	
	public AccountBuilder setusername(String username) {
		this.username = username;
		return this;
	}
	public AccountBuilder setpass(String pass) {
		this.pass = pass;
		return this;
	}
	public AccountBuilder setvitri(String vitri) {
		this.vitri = vitri;
		return this;
	}
	public Account build() {
		return new Account(username,pass,vitri);
	}
}
