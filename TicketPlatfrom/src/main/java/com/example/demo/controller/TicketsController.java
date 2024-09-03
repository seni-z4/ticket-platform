package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
//import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.APIResponse;
import com.example.demo.model.Ticket;
import com.example.demo.model.Utenti;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UtentiRepository;
import com.example.demo.services.TicketService;
import com.example.demo.services.UtentiService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



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
	
//	
//	@GetMapping
//	public String index(Model model, 
//			@RequestParam(name = "title", required = false) String title) {
//		
//		List<Ticket> tickets = new ArrayList<>();
//		
//		if(title == null || title.isBlank()) {
//			tickets = ticketRepo.findAll();
//		} 
//			else {
//			tickets = ticketRepo.findByTitleOrderByIdDesc(title);
//		}
//		
//		model.addAttribute("list", tickets);
//		
//		
//		
//		return "/Tickets/index";
//	}
//		
//		@GetMapping
//	    public String index(Model model) {
//		
//			
////			List<Ticket> listicket = ticketService.findbyTitle();
////			model.addAttribute("listicket" ,listicket);
//	       
//			
//	        List<Ticket> ticketList = ticketService.getAllTickets();
//	        model.addAttribute("list", ticketList);
//	        return "/Tickets/index";
//		
//	}
	
	@GetMapping
    public String index(
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        Model model) {

        // Create pageable instance
        Pageable pageable = PageRequest.of(page, size);
        
        // Get paginated tickets
        Page<Ticket> ticketPage;
        if (title != null && !title.isEmpty()) {
            ticketPage = ticketService.filterTickets(title, pageable);
        } else {
            ticketPage = ticketService.getTickets(pageable);
        }

        // Add pagination details to the model
        model.addAttribute("ticketPage", ticketPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ticketPage.getTotalPages());
        model.addAttribute("title", title); // for preserving the filter criteria

        return "/Tickets/index";
    }
	
////	 @GetMapping
////	    public String index(
////	            @RequestParam(value = "title", required = false) String title, Model model) {
////
////	        List<Ticket> ticketList = ticketService.filterTickets(title);
////	        model.addAttribute("list", ticketList);
////	        return "/Tickets/index";
////	    }
//	
	 @GetMapping("/pagination/{offset}/{pageSize}")
	    private APIResponse<Page<Ticket>> getTicketsWithSort(@PathVariable int offset, @PathVariable int pageSize) {
	        Page<Ticket> ticketsWithPagination = ticketService.findTicketWithPagination(offset, pageSize);
	        return new APIResponse<>(ticketsWithPagination.getSize(), ticketsWithPagination);
	    }
	
//	@GetMapping
//    public String index(Model model) {
//        List<Ticket> ticketList = ticketService.getAllTickets();
//        model.addAttribute("list", ticketList);
//        return "/Tickets/index";
//	
//}
//	
	
	
//	@RequestMapping("/")
//    public String viewHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
//		
////	    System.out.println("Received keyword: " + keyword); // Debug statement
////	    List<Ticket> listTickets = ticketService.listAll(keyword);
////	    model.addAttribute("listticket", listTickets);
////	    model.addAttribute("keyword", keyword);
//	    model.addAttribute("ticket", new Ticket()); // Add this for the form binding
//	    return "Tickets/index";
//	}
	
	
//    @GetMapping
//    public List<Ticket> findByTitle(@RequestParam String title) {  	
//        return ticketRepo.findByTitle(title);	
//    }

		
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("users", utentiRepository.findAll());
		
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
	
//	
//	@GetMapping("/show/{id}")
//	public String show(@PathVariable("id") Integer idticket, Model model) {
//		
//		model.addAttribute("Ticket" , ticketRepo.getReferenceById(idticket));
//		
//		return "/Tickets/show";
//	}
//	
	
	

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<Utenti> utentiList = utentiService.getAllUsers();
        model.addAttribute("utentiList", utentiList);
        return "/Tickets/user";
    }
    
	
}
