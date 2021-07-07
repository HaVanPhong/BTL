package com.hithaui.DTO;

public class RestaurantDTO {
	
	private String name;
	
	private String address;
	
	private String location;

	public RestaurantDTO(String name, String address, String location) {
		super();
		this.name = name;
		this.address = address;
		this.location = location;
	}

	public RestaurantDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
