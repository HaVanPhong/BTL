package com.hithaui.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "restaurants")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRes;
	
	private String name;
	
	private String addresss;
	
	private String location;
	
	private double star;
	
	private int soLuot;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> listCmt;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Food> listFood;
	
	public Restaurant(String name, String addresss, String location) {
		super();
		this.name = name;
		this.addresss = addresss;
		this.location = location;
		this.star = 0;
		this.soLuot=0;
	}
	
	

	public Restaurant(Integer idRes, String name, String addresss, String location, List<Comment> listCmt) {
		super();
		this.idRes = idRes;
		this.name = name;
		this.addresss = addresss;
		this.location = location;
		this.star = 0;
		this.soLuot=0;
		this.listCmt = listCmt;
	}

	public Restaurant() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public List<Comment> getListCmt() {
		return listCmt;
	}

	public void setListCmt(List<Comment> listCmt) {
		this.listCmt = listCmt;
	}



	public Integer getIdRes() {
		return idRes;
	}



	public void setIdRes(Integer idRes) {
		this.idRes = idRes;
	}



	public List<Food> getListFood() {
		return listFood;
	}



	public void setListFood(List<Food> listFood) {
		this.listFood = listFood;
	}



	public Restaurant(Integer idRes, String name, String addresss, String location, int star, List<Comment> listCmt,
			List<Food> listFood) {
		super();
		this.idRes = idRes;
		this.name = name;
		this.addresss = addresss;
		this.location = location;
		this.star = 0;
		this.soLuot=0;
		this.listCmt = listCmt;
		this.listFood = listFood;
	}



	public int getSoLuot() {
		return soLuot;
	}



	public void setSoLuot(int soLuot) {
		this.soLuot = soLuot;
	}
	
	
	
	
	
}
