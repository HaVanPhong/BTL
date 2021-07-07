package com.hithaui.DTO;



public class FoodDTO {

private String name;
	
	private long oldPrice;
	
	private int phanTram;
	
	private String descriptions;

	public FoodDTO(String name, long oldPrice, int phanTram, String descriptions) {
		super();
		this.name = name;
		this.oldPrice = oldPrice;
		this.phanTram = phanTram;
		this.descriptions = descriptions;
	}

	public FoodDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(long oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getPhanTram() {
		return phanTram;
	}

	public void setPhanTram(int phanTram) {
		this.phanTram = phanTram;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	
}
