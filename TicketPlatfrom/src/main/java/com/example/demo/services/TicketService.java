package com.example.demo.services;

import java.util.Collections;
import java.util.List;

//import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.model.TicketUtils;
import com.example.demo.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;




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
    
    public Page<Ticket> findTicketWithPagination(int offset , int pageSize){
     Page<Ticket> tickts =  ticketRepo.findAll(PageRequest.of(offset, pageSize));
     return tickts;
    }
    
    public Page<Ticket> getTickets(Pageable pageable) {
        return ticketRepo.findAll(pageable);
    }
    
    public Page<Ticket> filterTickets(String title, Pageable pageable) {
        return ticketRepo.findByTitleContainingIgnoreCase(title, pageable);
    }
    
}

