package ticket.it.ticket.model.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.service.StatusService;
import ticket.it.ticket.service.TicketService;

@RestController
@CrossOrigin
@RequestMapping("/api/tickets")
public class TicketApiController {

  @Autowired
  private TicketService ticketService;

  @GetMapping
  public List<Ticket> index() {
    return ticketService.findAll();
  }

  @GetMapping("/category/{categoryId}")
  public List<Ticket> getTicketsByCategory(@PathVariable Integer categoryId) {
    return ticketService.findByCategoryId(categoryId);
  }

  @GetMapping("/status/{statusId}")
  public List<Ticket> getTicketsByStatus(@PathVariable Integer statusId) {
    return ticketService.findByStatus_Id(statusId);
  }

}
