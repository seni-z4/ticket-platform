package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	public List<Ticket> findByTitle(String title);

	public List<Ticket> findByTitleOrderByIdDesc(String title);
}
