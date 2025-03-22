package ticket.it.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.it.ticket.model.Status;
import ticket.it.ticket.model.Ticket;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}