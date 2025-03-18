package ticket.it.ticket.controller;

import java.security.PrivateKey;
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
import ticket.it.ticket.model.Status;
import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.model.User;
import ticket.it.ticket.repository.StatusRepository;
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

  @Autowired
  private StatusRepository statusRepository;

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
    model.addAttribute("statuses", statusRepository.findAll());

    return "tickets/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid Ticket ticketForm, @RequestParam("status") Integer statusId, BindingResult bindingResult,
      Model model,
      RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("create", true);
      model.addAttribute("users", userService.getAvailableUsers());
      model.addAttribute("statuses", statusRepository.findAll());

      return "tickets/create-or-edit";
    }

    User assignedUser = userRepository.findById(ticketForm.getUser().getId())
        .orElseThrow(() -> new RuntimeException("User not found"));

    Status status = statusRepository.findById(statusId).get();

    ticketForm.setStatus(status);

    assignedUser.updateAvailability();
    ticketForm.setUser(assignedUser);

    ticketService.save(ticketForm);
    userRepository.save(assignedUser);

    redirectAttributes.addFlashAttribute("message",
        String.format("a new ticket %s has been created", ticketForm.getTitle()));

    redirectAttributes.addFlashAttribute("messageClass", "alert-success");

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
    model.addAttribute("statuses", statusRepository.findAll());

    return "tickets/create-or-edit";

  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("ticket") Ticket ticket, @RequestParam("status") Integer statusId,
      BindingResult bindingResult, Model model,
      RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("users", userService.getAllUsers());
      model.addAttribute("statuses", statusRepository.findAll());
      return "tickets/create-or-edit";
    }

    Ticket existingTicket = ticketService.getById(ticket.getId());
    User previousUser = existingTicket.getUser();
    User newAssignedUser = userRepository.getReferenceById(ticket.getUser().getId());

    ticket.setUser(newAssignedUser);

    if (previousUser != null && !previousUser.equals(newAssignedUser)) {
      previousUser.updateAvailability();
      userRepository.save(previousUser);
    }

    newAssignedUser.updateAvailability();
    userRepository.save(newAssignedUser);

    Status status = statusRepository.findById(statusId).get();
    ticket.setStatus(status);

    ticketService.save(ticket);

    redirectAttributes.addFlashAttribute("message",
        String.format("ticket  %s has been updated", ticket.getTitle()));

    redirectAttributes.addFlashAttribute("messageClass", "alert-primary");

    return "redirect:/ticket";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {

    Ticket ticket = ticketService.getById(id);

    User assignedUser = ticket.getUser();
    System.out.println("Assigned User: " + (assignedUser != null ? assignedUser.getName() : "None"));

    ticketService.delete(ticket);

    if (assignedUser != null) {
      assignedUser.updateAvailability();
      userRepository.save(assignedUser);
    }

    redirectAttributes.addFlashAttribute("message",
        String.format("ticket %s has been deleted", ticket.getTitle()));

    redirectAttributes.addFlashAttribute("messageClass", "alert-danger");

    return "redirect:/ticket";
  }

}
