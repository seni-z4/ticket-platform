package ticket.it.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.it.ticket.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}