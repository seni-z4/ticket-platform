package ticket.it.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.it.ticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

  public List<Ticket> findByTitleContainingIgnoreCase(String title);

  public List<Ticket> findByUserId(Integer id);

  List<Ticket> findByCategories_Id(Integer categoryId);

  public List<Ticket> findByStatus_Id(Integer statusId);

}