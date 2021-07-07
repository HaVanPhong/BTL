package com.hithaui.DTO;

public class JwtResponse {
	private String jwt;
	
 
	public JwtResponse(String jwt) {
		super();
		this.jwt = jwt;
 	}

	public JwtResponse() {
		super();
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	 

}
