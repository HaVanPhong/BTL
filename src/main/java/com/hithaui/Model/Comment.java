package com.hithaui.Model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@OneToOne
	@JoinColumn(name = "idPhoto")
	private Photo photo;
	
	@ManyToOne
	@JoinColumn(name = "idRes")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "idPosts")
	private Posts posts;

	public Comment(String content, String linkImgCmt, Timestamp timeCmt, Timestamp updateAt) {
		super();
		this.content = content;
		this.linkImgCmt = linkImgCmt;
		this.timeCmt = timeCmt;
		this.updateAt = updateAt;
	}

	public Comment(String content, String linkImgCmt, Timestamp timeCmt, Timestamp updateAt, Photo photo,
			Restaurant restaurant, Account account, Posts posts) {
		super();
		this.content = content;
		this.linkImgCmt = linkImgCmt;
		this.timeCmt = timeCmt;
		this.updateAt = updateAt;
		this.photo = photo;
		this.restaurant = restaurant;
		this.account = account;
		this.posts = posts;
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

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
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
	
	

}
