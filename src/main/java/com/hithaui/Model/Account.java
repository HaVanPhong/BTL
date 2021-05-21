package com.hithaui.Model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	@JsonIgnore
	private String password;

	private String role;

	private String fullname;

	private String gender;

	private boolean status;
	@CreationTimestamp
	private Timestamp creaetAt;
	@UpdateTimestamp
	private Timestamp updateAt;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Photo> photos;

//	@JsonIgnore
//	private List<String> friends;

	public Account() {

	}

	public Account(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Account(Integer id, String username, String password, String role, String fullname, String gender,
			boolean status, Timestamp creaetAt, Timestamp updateAt, List<Photo> photos) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
		this.gender = gender;
		this.status = status;
		this.creaetAt = creaetAt;
		this.updateAt = updateAt;
		this.photos = photos;
	}

	public Account(String username, String password, String role, String fullname, String gender, boolean status,
			Timestamp creaetAt, Timestamp updateAt, List<Photo> photos, List<String> friends) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
		this.gender = gender;
		this.status = status;
		this.creaetAt = creaetAt;
		this.updateAt = updateAt;
		this.photos = photos;
//		this.friends = friends;
	}

	public Account(String username, String password, String role, String fullname, String gender, boolean status) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
		this.gender = gender;
		this.status = status;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Timestamp getCreaetAt() {
		return creaetAt;
	}

	public void setCreaetAt(Timestamp creaetAt) {
		this.creaetAt = creaetAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

//	public List<String> getFriends() {
//		return friends;
//	}
//
//	public void setFriends(List<String> friends) {
//		this.friends = friends;
//	}

}
