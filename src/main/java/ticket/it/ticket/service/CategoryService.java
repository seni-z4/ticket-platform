package ticket.it.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.it.ticket.model.Category;
import ticket.it.ticket.model.Note;
import ticket.it.ticket.repository.CategoryRepository;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category getById(Integer id) {
    return categoryRepository.findById(id).get();
  }

  public List<Category> findAllById(List<Integer> category) {
    return categoryRepository.findAllById(category);
  }

}
