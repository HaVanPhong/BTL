package com.hithaui.Model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCmt;
	
	private String content;
	
	private String linkImgCmt;
	
	
	@CreationTimestamp
	private Timestamp timeCmt;
	
	@UpdateTimestamp
	private Timestamp updateAt;
	
	@ManyToOne
	@JoinColumn(name = "idRes")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "idPosts")
	private Posts posts;
	
	@ManyToOne
	@JoinColumn(name = "idFood")
	private Food food;

	public Comment(String content) {
		super();
		this.content = content;
	}
	
	public Comment(String content, String link) {
		super();
		this.content = content;
		this.linkImgCmt= link;
	}
	
	public Comment(String content, String linkImgCmt, Timestamp timeCmt, Timestamp updateAt,
			Restaurant restaurant, Account account, Posts posts) {
		super();
		this.content = content;
		this.linkImgCmt = linkImgCmt;
		this.timeCmt = timeCmt;
		this.updateAt = updateAt;
 		this.restaurant = restaurant;
		this.account = account;
		this.posts = posts;
	}
	

	public Integer getIdCmt() {
		return idCmt;
	}

	public void setIdCmt(Integer idCmt) {
		this.idCmt = idCmt;
	}

	public Comment() {
		super();
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinkImgCmt() {
		return linkImgCmt;
	}

	public void setLinkImgCmt(String linkImgCmt) {
		this.linkImgCmt = linkImgCmt;
	}

	public Timestamp getTimeCmt() {
		return timeCmt;
	}

	public void setTimeCmt(Timestamp timeCmt) {
		this.timeCmt = timeCmt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Comment(Integer idCmt, String content, String linkImgCmt, Timestamp timeCmt, Timestamp updateAt,
			Restaurant restaurant, Account account, Posts posts, Food food) {
		super();
		this.idCmt = idCmt;
		this.content = content;
		this.linkImgCmt = linkImgCmt;
		this.timeCmt = timeCmt;
		this.updateAt = updateAt;
		this.restaurant = restaurant;
		this.account = account;
		this.posts = posts;
		this.food = food;
	}
	
	
	

}
