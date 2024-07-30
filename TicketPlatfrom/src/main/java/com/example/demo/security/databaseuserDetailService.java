package com.example.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Utenti;
import com.example.demo.repository.UtentiRepository;


public class databaseuserDetailService implements UserDetailsService {

	@Autowired
	private UtentiRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Utenti> utente = userrepo.findByUsername(username);

		if (utente.isPresent()) {

			UserDetails u = new DatabaseUserDetails(utente.get());
			return u;
		} else {
			throw new UsernameNotFoundException("utente non trovato");
		}
		 
        
	}

}
