package ticket.it.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.repository.TicketRepository;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  public List<Ticket> findAll() {
    return ticketRepository.findAll();
  }
}
