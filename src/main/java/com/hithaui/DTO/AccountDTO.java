package com.hithaui.DTO;

public class AccountDTO {
	private String username;
	private String password;
	private String role;
	private String fullname;
	private String gender;
	private String email;
	public AccountDTO(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role= role;
	}
	
	
	public AccountDTO(String username, String password, String role, String fullname, String gender, String email) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.fullname=fullname;
		this.gender= gender;
		this.email = email;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
