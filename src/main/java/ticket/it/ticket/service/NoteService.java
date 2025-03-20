package ticket.it.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.it.ticket.model.Note;
import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.repository.NoteRepository;

@Service
public class NoteService {

  @Autowired
  private NoteRepository noteRepository;

  public List<Note> findNotesByTicket(Ticket ticket) {
    return noteRepository.findByTicket(ticket);
  }

  public Note save(Note save) {
    return noteRepository.save(save);
  }

  public Note update(Note update) {
    return noteRepository.save(update);
  }

  public Note getById(Integer id) {
    return noteRepository.findById(id).get();
  }

  public void deleteById(Integer note) {
    noteRepository.deleteById(note);
  }
}
