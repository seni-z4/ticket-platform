package ticket.it.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ticket.it.ticket.model.User;

import ticket.it.ticket.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public String index(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "users/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    model.addAttribute("users", userService.getById(id));
    return "users/show";
  }

  // @GetMapping("/search")
  // public String filterByTitle(@RequestParam(name = "query") String query, Model
  // model,
  // RedirectAttributes redirectAttributes) {

  // List<Ticket> tickets;

  // if (query != null && !query.isEmpty()) {
  // tickets = ticketService.serachBookByTitle(query);
  // } else {
  // tickets = ticketService.findAll();
  // }
  // model.addAttribute("tickets", tickets);
  // return "tickets/index";
  // }

}
