package ticket.it.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.repository.TicketRepository;
import ticket.it.ticket.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

  // private final TicketApplication ticketApplication;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private TicketRepository ticketRepository;

  // TicketController(TicketApplication ticketApplication) {
  // this.ticketApplication = ticketApplication;
  // }

  @GetMapping
  public String index(Model model) {
    List<Ticket> tickets = ticketService.findAll();
    model.addAttribute("tickets", tickets);
    return "tickets/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    model.addAttribute("ticket", ticketService.getById(id));
    return "tickets/show";
  }

  @GetMapping("/search")
  public String filterByTitle(@RequestParam(name = "query") String query, Model model,
      RedirectAttributes redirectAttributes) {

    List<Ticket> tickets;

    if (query != null && !query.isEmpty()) {
      tickets = ticketService.serachBookByTitle(query);
    } else {
      tickets = ticketService.findAll();
    }
    model.addAttribute("tickets", tickets);
    return "tickets/index";
  }

}
