package ticket.it.ticket.controller;

import java.time.LocalDate;
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
import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.model.User;
import ticket.it.ticket.repository.TicketRepository;
import ticket.it.ticket.repository.UserRepository;
import ticket.it.ticket.service.TicketService;
import ticket.it.ticket.service.UserService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

  // private final TicketApplication ticketApplication;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

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
    Ticket ticket = ticketService.getById(id);

    model.addAttribute("ticket", ticket);
    model.addAttribute("user", ticket.getUser());
    return "tickets/show";
  }

  @GetMapping("/search")
  public String filterByTitle(@RequestParam(name = "query") String query, Model model,
      RedirectAttributes redirectAttributes) {

    List<Ticket> tickets;

    if (query != null && !query.isEmpty()) {
      tickets = ticketService.serachTicketByTitle(query);
    } else {
      tickets = ticketService.findAll();
    }
    model.addAttribute("tickets", tickets);
    return "tickets/index";
  }

  @GetMapping("/create")
  public String Create(Model model) {
    model.addAttribute("users", userService.getAvailableUsers());
    model.addAttribute("ticket", new Ticket());
    model.addAttribute("create", true);

    return "tickets/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid Ticket ticketForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("create", true);
      model.addAttribute("users", userService.getAvailableUsers());

      return "tickets/create-or-edit";
    }

    User assignedUser = userRepository.findById(ticketForm.getUser().getId())
        .orElseThrow(() -> new RuntimeException("User not found"));

    assignedUser.updateAvailability();
    ticketForm.setUser(assignedUser);

    ticketService.save(ticketForm);
    userRepository.save(assignedUser);

    return "redirect:/ticket";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Ticket ticket = ticketService.getById(id);

    List<User> availabelUsers = userService.getAvailableUsers();

    if (ticket.getUser() != null && !availabelUsers.contains(ticket.getUser())) {
      availabelUsers.add(ticket.getUser());
    }

    model.addAttribute("ticket", ticket);
    model.addAttribute("users", availabelUsers);
    return "tickets/create-or-edit";

  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("users", userService.getAllUsers());
      return "tickets/create-or-edit";
    }

    Ticket existingTicket = ticketService.getById(ticket.getId());
    User previousUser = existingTicket.getUser();
    User newAssignedUser = userRepository.getReferenceById(ticket.getUser().getId());

    ticket.setUser(newAssignedUser);
    ticketService.save(ticket);

    if (previousUser != null && !previousUser.equals(newAssignedUser)) {
      previousUser.updateAvailability();
      userRepository.save(previousUser);
    }

    newAssignedUser.updateAvailability();
    userRepository.save(newAssignedUser);

    return "redirect:/ticket";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, Model model) {

    Ticket ticket = ticketService.getById(id);

    User assignedUser = ticket.getUser();
    System.out.println("Assigned User: " + (assignedUser != null ? assignedUser.getName() : "None"));

    ticketService.delete(ticket);

    if (assignedUser != null) {
      assignedUser.updateAvailability();
      userRepository.save(assignedUser);
    }

    return "redirect:/ticket";
  }

}
