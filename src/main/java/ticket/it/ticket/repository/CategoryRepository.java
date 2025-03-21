package ticket.it.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.it.ticket.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  // List<Category> findAllById(List<Integer> ids);

}
