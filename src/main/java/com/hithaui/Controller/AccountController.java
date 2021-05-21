package com.hithaui.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hithaui.DTO.AccountDTO;
import com.hithaui.Exception.NotFoundException;
import com.hithaui.Model.Account;
import com.hithaui.Repository.AccountRepositories;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	private AccountRepositories accountRepositories;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity<?> getAllAccountsEntity () throws Exception{
		List<Account> accounts = accountRepositories.findAll();
		if (accounts.size()==0) {
			throw new NotFoundException("Not found any account");
		}
		return ResponseEntity.status(200).body(accounts);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createAccount (@RequestBody AccountDTO accountDTO) throws Exception{
		String passwordString = passwordEncoder.encode(accountDTO.getPassword());
		Account account = new Account(accountDTO.getUsername(), passwordString, accountDTO.getRole());
		Account account2= accountRepositories.save(account);
		return ResponseEntity.status(200).body(account2);		
	}
	


}
