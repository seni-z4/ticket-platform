package ticket.it.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.it.ticket.model.Note;
import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.repository.TicketRepository;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  public List<Ticket> findAll() {
    return ticketRepository.findAll();
  }

  public Ticket getById(Integer id) {
    return ticketRepository.findById(id).get();
  }

  public List<Ticket> serachTicketByTitle(String title) {
    return ticketRepository.findByTitleContainingIgnoreCase(title);
  }

  public Ticket save(Ticket save) {
    return ticketRepository.save(save);
  }

  public void delete(Ticket ticket) {
    ticketRepository.delete(ticket);
  }

}
