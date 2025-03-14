package ticket.it.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @GetMapping
  public String index(Model model) {
    List<Ticket> tickets = ticketService.findAll();
    model.addAttribute("tickets", tickets);
    return "tickets/index";
  }
}
