package com.hithaui.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hithaui.DTO.AccountDTO;
import com.hithaui.DTO.JwtResponse;
import com.hithaui.Exception.DuplicateException;
import com.hithaui.Service.MyUserDetailsService;
import com.hithaui.utils.JwtUtil;

@RestController
@RequestMapping("/auth/login")
public class LoginAuthController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping
	public String oke () {
		return "app da hoat dong";
	}
	
	
	@PostMapping
	public ResponseEntity<?> loginEntity (@RequestBody AccountDTO accountDTO) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(accountDTO.getUsername(), accountDTO.getPassword()));
		} catch (BadCredentialsException e) {
			throw new DuplicateException("incorrect username or password");
		}
		
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(accountDTO.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.status(200).body(new JwtResponse(jwt));		
	}
	
	
	
	
}
