package com.example.demo.model;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "Utenti")
public class Utenti {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "email non puoi essere null")
	private String email;
	
	@NotBlank(message = "password non puoi essere null")
	private String password;
	
	@NotNull(message = "La data di prestito non può essere null")
	@Column(name = "date of birth ", nullable = false)
	private Date dateofbith;
	
	@OneToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateofbith() {
		return dateofbith;
	}

	public void setDateofbith(Date dateofbith) {
		this.dateofbith = dateofbith;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	
	

}
