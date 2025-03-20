package ticket.it.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.it.ticket.model.Note;
import ticket.it.ticket.model.Ticket;

public interface NoteRepository extends JpaRepository<Note, Integer> {

  List<Note> findByTicket(Ticket ticket);

}
