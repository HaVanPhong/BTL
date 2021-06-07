package com.hithaui.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "food")
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFood;
	
	private String name;
	
	private long oldPrice;
	
	private int phanTram;
	
	
	@Column(length = 1000)
	@Nationalized
	private String descriptions;
	
	@OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> comments;
	
	@ManyToOne
	@JoinColumn(name ="idRes")
	private Restaurant restaurant;

	public Food(Integer idFood, String name, long oldPrice, int phanTram, String descriptions, List<Comment> comments,
			Restaurant restaurant) {
		super();
		this.idFood = idFood;
		this.name = name;
		this.oldPrice = oldPrice;
		this.phanTram = phanTram;
		this.descriptions = descriptions;
		this.comments = comments;
		this.restaurant = restaurant;
	}

	public Food(String name, long oldPrice, int phanTram, String descriptions) {
		super();
		this.name = name;
		this.oldPrice = oldPrice;
		this.phanTram = phanTram;
		this.descriptions = descriptions;
	}

	public Food() {
		super();
	}

	public Integer getIdFood() {
		return idFood;
	}

	public void setIdFood(Integer idFood) {
		this.idFood = idFood;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	

}
