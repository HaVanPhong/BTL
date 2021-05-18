package com.hithaui.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRes;
	
	private String name;
	
	private String addresss;
	
	private String location;
	
	private int star;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<Comment> listCmt;

	public Restaurant(String name, String addresss, String location, int star) {
		super();
		this.name = name;
		this.addresss = addresss;
		this.location = location;
		this.star = star;
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

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public List<Comment> getListCmt() {
		return listCmt;
	}

	public void setListCmt(List<Comment> listCmt) {
		this.listCmt = listCmt;
	}
	
}
