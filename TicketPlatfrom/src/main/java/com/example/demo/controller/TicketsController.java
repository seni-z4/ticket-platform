package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Ticket;
import com.example.demo.model.Utenti;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UtentiRepository;
import com.example.demo.services.TicketService;
import com.example.demo.services.UtentiService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ticket")
public class TicketsController {

	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private UtentiRepository utentiRepository;
	
	@Autowired
	private UtentiService utentiService;
	
	@Autowired
	private TicketService ticketService;
	
	
//	@GetMapping
//	public String index(Model model, 
//			@RequestParam(name = "title", required = false) String title) {
//		
//		List<Ticket> tickets = new ArrayList<>();
//		
//		if(title == null || title.isBlank()) {
//			tickets = ticketRepo.findAll();
//		} else {
//			tickets = ticketRepo.findByTitleOrderByIdDesc(title);
//		}
//		
//		model.addAttribute("list", tickets);
//		
//		
//		
//		return "/Tickets/index";
		
		@GetMapping
	    public String index(Model model) {
	        List<Ticket> ticketList = ticketService.getAllTickets();
	        model.addAttribute("list", ticketList);
	        return "/Tickets/index";
		
	}
	
	
	
	
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("ticket", new Ticket());
		
		return "/Tickets/create";

		
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ticket") Ticket tickets, BindingResult bindingResult,
			Model model) {
//		if(bindingResult.hasErrors()) {
//			return "/Tickets/create";
//		}
		
		ticketRepo.save(tickets);
		
		return "redirect:/ticket";
	}
	

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<Utenti> utentiList = utentiService.getAllUsers();
        model.addAttribute("utentiList", utentiList);
        return "/Tickets/user"; // This should match the name of your Thymeleaf template (users.html)
    }
	
}
