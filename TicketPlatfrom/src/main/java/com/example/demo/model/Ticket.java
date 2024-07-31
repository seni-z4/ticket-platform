package com.example.demo.model;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "Ticket")
public class Ticket {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "la data del presito nn puo essere null")
	private Date dueDate;
	
	@NotBlank(message = "the title cannt bt null")
	private String title;
	
	private String textArea;
	
	@ManyToOne
	@JoinColumn(name = "utenti_id")
	private Utenti utenti;
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
	@OneToOne
	@JoinColumn(name = "categori_id")
	private Categori categori;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getTextArea() {
		return textArea;
	}


	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}


	public Utenti getUtenti() {
		return utenti;
	}


	public void setUtenti(Utenti utenti) {
		this.utenti = utenti;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Categori getCategori() {
		return categori;
	}


	public void setCategori(Categori categori) {
		this.categori = categori;
	}
	
	
}
