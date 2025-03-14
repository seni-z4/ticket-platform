package ticket.it.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.it.ticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}