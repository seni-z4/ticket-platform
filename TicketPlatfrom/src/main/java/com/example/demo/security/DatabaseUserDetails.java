package com.example.demo.security;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Role;
import com.example.demo.model.Utenti;

public class DatabaseUserDetails implements UserDetails {

	
	private final Integer id;
	private final String username;
	private final String email;
	private final String password;
	private final Date dateofbith;
	private final Set<GrantedAuthority> authorities;
	
	
	public DatabaseUserDetails(Utenti user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.dateofbith = user.getDateofbith();
		this.authorities = new HashSet<>();
		for(Role ruolo : user.getRoles()) {
			this.authorities.add(new SimpleGrantedAuthority(ruolo.getRole()));
		}
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}


}
