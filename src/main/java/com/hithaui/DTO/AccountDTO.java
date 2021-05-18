package com.hithaui.DTO;

public class AccountDTO {
	private String username;
	private String password;
	private String role;
	public AccountDTO(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role= role;
	}
	public AccountDTO() {
		super();
	}
	public String getRole() {
		return this.role;
	}
	public void setRole(String role) {
		this.role=role;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
