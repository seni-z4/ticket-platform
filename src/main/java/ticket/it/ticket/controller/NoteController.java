package ticket.it.ticket.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import ticket.it.ticket.model.Note;
import ticket.it.ticket.model.Status;
import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.model.User;
import ticket.it.ticket.service.NoteService;
import ticket.it.ticket.service.TicketService;

@Controller
@RequestMapping("/notes")
public class NoteController {

  @Autowired
  private TicketService ticketService;

  @Autowired
  private NoteService noteService;

  @GetMapping("/create/{id}")
  public String create(@PathVariable Integer id, Model model) {
    Ticket ticket = ticketService.getById(id);

    Note note = new Note();
    note.setTicket(ticket);

    model.addAttribute("note", note);
    model.addAttribute("ticket", ticket);
    model.addAttribute("create", true);

    return "notes/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("note") Note note,
      @RequestParam(value = "ticketId", required = true) Integer ticketId,
      BindingResult bindingResult,
      Model model) {

    Ticket ticket = ticketService.getById(ticketId);
    note.setTicket(ticket);

    if (bindingResult.hasErrors()) {
      model.addAttribute("create", true);
      return "notes/create-or-edit";
    }

    noteService.save(note);

    return "redirect:/ticket/" + ticketId;
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Note note = noteService.getById(id);

    model.addAttribute("note", noteService.getById(id));
    model.addAttribute("ticket", note.getTicket());

    return "notes/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("notes") Note note,
      @RequestParam("ticketId") Integer ticketId,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("ticket", note.getTicket());
      return "notes/create-or-edit";
    }

    Note existingNote = noteService.getById(id);
    note.setCreateDate(existingNote.getCreateDate());

    Ticket ticket = ticketService.getById(ticketId);
    note.setTicket(ticket);

    noteService.update(note);
    return "redirect:/ticket/" + ticketId;
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {

    noteService.deleteById(id);

    redirectAttributes.addFlashAttribute("message", "Note deleted successfully!");
    redirectAttributes.addFlashAttribute("messageClass", "alert-danger");

    return "redirect:/ticket";
  }
}
