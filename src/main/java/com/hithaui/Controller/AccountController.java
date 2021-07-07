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
import org.springframework.web.bind.annotation.DeleteMapping;
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
		Account acc1= accountRepositories.findByEmail(accountDTO.getEmail());
		if (acc1!=null) {
			throw new DuplicateException("Tài khoản đã tồn tại. Hãy đảm bảo rằng ID, username và email của bạn chưa được sử dụng");
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
					+ "\nTài khoản: " + account.getUsername() + "\nMật Khẩu: " + accountDTO.getPassword());
			emailSender.send(message);
		}
		
		return ResponseEntity.status(200).body(account2);		
	}
	
	@PatchMapping("/updateId/{emai}/{idNew}")
	public ResponseEntity<?> updateID(@PathVariable(name = "emai") String emai, @PathVariable(name = "idNew") String idNew){
		Account account = accountRepositories.findByEmail(emai);
		if (account==null) {
			throw new NotFoundException("API cho biết: Không tìm thấy email để update idNew");			
		}
		account.setIdNew(idNew);
		return ResponseEntity.status(200).body(accountRepositories.save(account));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateAvata(@PathVariable(name = "id") String id, @RequestParam(name = "img") MultipartFile file) throws IOException{
		Account account = accountRepositories.findByIdNew(id);
		if (account==null) {
			throw new NotFoundException("Account Was Not Found");
		}
		
		Map<?, ?> map = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		String linkAvt= map.get("secure_url").toString();
		
		account.setLinkAvt(linkAvt);
		
		return ResponseEntity.status(200).body(accountRepositories.save(account));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletedAccount(@PathVariable(name = "id") String id){
		Optional<Account> acc= accountRepositories.findById(id);
		if (!acc.isPresent()) {
			throw new NotFoundException("Không tìm thấy id để xóa");
		}
		accountRepositories.deleteById(id);
		return ResponseEntity.status(200).build();		
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<?> deleteAllAccount (){
		accountRepositories.deleteAll();
		return ResponseEntity.status(200).body("done");
	}
	
	@DeleteMapping("/delete/{emai}")
	public ResponseEntity<?> deleteByEmail (@PathVariable(name = "emai") String emai){
		Account account = accountRepositories.findByEmail(emai);
		if (account ==null) {
			throw new NotFoundException("K tìm thấy email này");
		}
		accountRepositories.delete(account);
		return ResponseEntity.status(200).body("done");
	}
	@PatchMapping("/updateRole/{email}/{token}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "email") String email, @PathVariable(name = "token") String token){
		Account account = accountRepositories.findByEmail(email);
		if (account==null) {
			throw new NotFoundException("Not Fount This Email");
		}
		account.setRole(token);
		return ResponseEntity.status(200).body(accountRepositories.save(account));
	}
	
}
