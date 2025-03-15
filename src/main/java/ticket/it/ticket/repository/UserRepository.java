package ticket.it.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.it.ticket.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  // public List<User> findByTitleContainingIgnoreCase(String title);
}