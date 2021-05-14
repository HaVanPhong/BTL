package com.hithaui.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String fullname;

}
