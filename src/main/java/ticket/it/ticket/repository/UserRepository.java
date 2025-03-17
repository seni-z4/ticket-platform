package ticket.it.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ticket.it.ticket.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  public User findById(User id);

  @Query("SELECT u FROM User u WHERE u.availabilityStatus = true")
  List<User> findAvailableUsers();
}