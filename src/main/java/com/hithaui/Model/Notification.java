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
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "notifications")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer idNoti;
	
	@Nationalized
	private String title;
	
	@Nationalized
	private String content;
	
	private String linkPhoto;
	
	@CreationTimestamp
	private Timestamp createAt;
	@UpdateTimestamp
	private Timestamp updateAt;
	
	@ManyToOne
	@JoinColumn(name = "id")
	@JsonIgnore
	private Account account;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "idPhoto")
	private Photo photo;

	public Notification(String title, String content, String linkPhoto, Account account, Photo photo) {
		super();
		this.title = title;
		this.content = content;
		if (linkPhoto==null)
		this.linkPhoto = photo.getLinkImg();
		else 
			this.linkPhoto= linkPhoto;
		this.account = account;
		this.photo = photo;
	}

	public Notification() {
		super();
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinkPhoto() {
		return linkPhoto;
	}

	public void setLinkPhoto(String linkPhoto) {
		this.linkPhoto = linkPhoto;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
}
