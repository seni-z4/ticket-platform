package ticket.it.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticket.it.ticket.model.User;
import ticket.it.ticket.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository.findById(id).get();
  }

  public List<User> getAvailableUsers() {
    return userRepository.findAvailableUsers();
  }
}
