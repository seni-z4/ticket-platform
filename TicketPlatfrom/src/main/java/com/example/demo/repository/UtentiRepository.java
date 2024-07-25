package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Utenti;

public interface UtentiRepository extends JpaRepository<Utenti, Integer> {

}
