package com.hithaui.Model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(unique = true)
	private String id;

	private String username;

	@JsonIgnore
	private String password;

	private String role;

	private String fullname;

	private String gender;

	private boolean status;
	
	private String linkAvt;
	
	private String email;
	
	@CreationTimestamp
	private Timestamp creaetAt;
	@UpdateTimestamp
	private Timestamp updateAt;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Posts> posts;

//	@JsonIgnore
//	private List<String> friends;
	
	

	public Account() {

	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

	public String getLinkAvt() {
		return linkAvt;
	}

	public void setLinkAvt(String linkAvt) {
		this.linkAvt = linkAvt;
	}

	public String getId() {
		return id;
	}

	public Account(String username, String password, String role, String fullname, String gender, String email) {
		super();
		this.id= "ELEO" + new Date().getTime();
		this.username = username;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
		this.gender = gender;
		this.email= email;
	}

	

	public void setId(String id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

//	public List<String> getFriends() {
//		return friends;
//	}
//
//	public void setFriends(List<String> friends) {
//		this.friends = friends;
//	}

}
