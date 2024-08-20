package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;

@Service
public class TicketService {
	

    @Autowired
    private TicketRepository ticketRepo;

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public List<Ticket> filterTickets(String title) {
        if (title != null && !title.isEmpty()) {
            return ticketRepo.findByTitle(title);
        } else {
            return ticketRepo.findAll();
        }
    }
}

