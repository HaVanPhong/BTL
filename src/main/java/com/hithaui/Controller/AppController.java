package com.hithaui.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hithaui.Exception.NotFoundException;
import com.hithaui.Model.Account;
import com.hithaui.Repository.AccountRepositories;

@RestController
@RequestMapping("/api")
public class AppController {
	@Autowired
	private AccountRepositories AccountRepositories;
	
	
	@GetMapping("/admin")
	@PreAuthorize("@authorizeService.checkAdmin(authentication, 'ADMIN')")
	public ResponseEntity<?> getAllAccount() throws Exception{
//		List<Account> accounts=  AccountRepositories.findAll();
//		if (accounts.size()==0) {
//			throw new NotFoundException("NOT FOUND ANY ACCOUNTS");
//		}
//		return ResponseEntity.status(200).body(accounts);
		return new AccountController().getAllAccountsEntity();
		
	}
	
	@GetMapping("/member")
	@PreAuthorize("@authorizeService.checkMember(authentication, 'MEMBER')")
	public ResponseEntity<?> roleMember (){
		return ResponseEntity.status(200).body("member");
	}
	
	@GetMapping("/admin/{userID}")
	@PreAuthorize("@authorizeService.checkAuth(authentication, 'MEMBER', #userId)")
	public ResponseEntity<?> getDutyById (@PathVariable("userId") Integer userId){
		return ResponseEntity.status(200).body("abc");
	}	
	
	
	

}
