package com.hithaui.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hithaui.Controller2.PostsController;
import com.hithaui.DTO.AccountDTO;
import com.hithaui.Exception.DuplicateException;
import com.hithaui.Exception.NotFoundException;
import com.hithaui.Model.Account;
import com.hithaui.Repository.AccountRepositories;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	public PostsController postController;
	
	@Autowired
	private AccountRepositories accountRepositories;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private Cloudinary cloudinary;
	
	@GetMapping
	public ResponseEntity<?> getAllAccountsEntity () throws Exception{
		List<Account> accounts = accountRepositories.findAll();
		if (accounts.size()==0) {
			throw new NotFoundException("Not found any account");
		}
		return ResponseEntity.status(200).body(accounts);
	}
	
	@Transactional
	@PostMapping("/create")
	public ResponseEntity<?> createAccount (@RequestBody AccountDTO accountDTO) throws Exception{
		Account acc= accountRepositories.findByUsername(accountDTO.getUsername());
		if (acc!=null) {
			throw new DuplicateException("Tài Khoản đã tồn tại");
		}
		String passwordString = passwordEncoder.encode(accountDTO.getPassword());
		
		Account account = new Account(accountDTO.getUsername(), passwordString, accountDTO.getRole(), accountDTO.getFullname(), accountDTO.getGender(), accountDTO.getEmail());
		Account account2= accountRepositories.save(account);
		
		if (accountDTO.getEmail()!=null) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(accountDTO.getEmail());
			message.setSubject("THÔNG BÁO TẠO TÀI KHOẢN EATHIT");
			message.setText("Bạn vừa tạo thành công tài khoản trên hệ thống EATHIT"
					+ "\nVui lòng ghi nhớ tài khoản cá nhân và không cung cấp nội dung email này cho bất cứ ai"
					+ "\nID: "+ account.getId()
					+ "\nTài khoản: " + account.getUsername() + "\nMật Khẩu: " + account.getPassword());
			this.emailSender.send(message);
		}
		
		return ResponseEntity.status(200).body(account2);		
	}

	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateAvata(@PathVariable(name = "id") String id, @RequestParam(name = "img") MultipartFile file) throws IOException{
		Optional<Account> account = accountRepositories.findById(id);
		if (!account.isPresent()) {
			throw new NotFoundException("Account Was Not Found");
		}
		
		Map<?, ?> map = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		String linkAvt= map.get("secure_url").toString();
		
		account.get().setLinkAvt(linkAvt);
		
		return ResponseEntity.status(200).body(accountRepositories.save(account.get()));
	}
	
}
