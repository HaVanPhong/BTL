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

@Entity
@Table(name = "posts")
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPosts;
	
	private String content;
	
	@CreationTimestamp
	private Timestamp creaetAt;
	@UpdateTimestamp
	private Timestamp updateAt;
	
	@OneToMany( mappedBy ="posts", cascade = CascadeType.ALL)
	private List<Comment> listCmtComments ;

	public Posts(String content, Timestamp creaetAt, Timestamp updateAt) {
		super();
		this.content = content;
		this.creaetAt = creaetAt;
		this.updateAt = updateAt;
	}

	public Posts() {
		super();
	}

	public Integer getIdPosts() {
		return idPosts;
	}

	public void setIdPosts(Integer idPosts) {
		this.idPosts = idPosts;
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
