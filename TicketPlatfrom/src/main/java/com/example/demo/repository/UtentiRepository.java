package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Utenti;

public interface UtentiRepository extends JpaRepository<Utenti, Integer> {

	Optional<Utenti> findByUsername(String username);
}
