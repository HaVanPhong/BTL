package com.hithaui.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hithaui.Model.Account;
import com.hithaui.Repository.AccountRepositories;


@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountRepositories accountRepositories;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepositories.findByUsername(username);
		Set<GrantedAuthority> authorities= new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(account.getRole()));
		
		return new User(account.getUsername(), account.getPassword(), authorities);
	}
	
}
