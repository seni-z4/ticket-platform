package ticket.it.ticket.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "tickets")
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull(message = "The title cannot be null")
  private String title;

  @NotNull(message = "Date cannot be null")
  @PastOrPresent(message = "The ticket cannot be created in the future")
  private LocalDate createdAt;

  @NotNull(message = "The ticket cannot be updated in the future")
  @PastOrPresent(message = "the ticket cann't we updated in the futher")
  private LocalDate updatedAt;

  @NotNull(message = "date cann't be null")
  @FutureOrPresent(message = "The ticket cannot have a due date in the past")
  private LocalDate dueDate;

  @NotNull(message = "Please add a description")
  private String description;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDate getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public String getTextArea() {
    return description;
  }

  public void setTextArea(String textArea) {
    this.description = textArea;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Ticket [id=" + id + ", title=" + title + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
        + ", dueDate=" + dueDate + ", textArea=" + description + "]";
  }

}
