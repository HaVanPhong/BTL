package com.hithaui.Model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "posts")
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPosts;

	private String content;

	private int likes;

	private String imgLink;

	@ManyToOne
	@JoinColumn(name = "id")
	private Account account;

	
	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> listCmtComments;
	
	@CreationTimestamp
	private Timestamp creaetAt;
	@UpdateTimestamp
	private Timestamp updateAt;


	public Posts(Account account, String content) {
		this.content = content;
		this.account = account;
	}

	public Posts(Account account, String content, String imgL) {
		super();
		this.content = content;
		this.imgLink = imgL;
		this.account = account;
	}

	public Posts(String content) {
		this.content = content;
	}

	
	public Posts(Integer idPosts, String content, int likes, String imgLink, Account account, Timestamp creaetAt,
			Timestamp updateAt, List<Comment> listCmtComments) {
		super();
		this.idPosts = idPosts;
		this.content = content;
		this.likes = likes;
		this.imgLink = imgLink;
		this.account = account;
		this.creaetAt = creaetAt;
		this.updateAt = updateAt;
		this.listCmtComments = listCmtComments;
	}

	public Posts(String content, String imgL) {
		super();
		this.content = content;
		this.imgLink = imgL;
	}

	public Posts() {
		super();
	}

	public int getLike() {
		return likes;
	}

	public void setLike(int likes) {
		this.likes = likes;
	}

	public Integer getIdPosts() {
		return idPosts;
	}

	public void setIdPosts(Integer idPosts) {
		this.idPosts = idPosts;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public List<Comment> getListCmtComments() {
		return listCmtComments;
	}

	public void setListCmtComments(List<Comment> listCmtComments) {
		this.listCmtComments = listCmtComments;
	}

}
