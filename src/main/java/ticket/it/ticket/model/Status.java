package ticket.it.ticket.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "statuses")
public class Status {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String status;

  @OneToMany(mappedBy = "status")
  @JsonManagedReference
  private List<Ticket> ticket;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Ticket> getTicket() {
    return ticket;
  }

  public void setTicket(List<Ticket> ticket) {
    this.ticket = ticket;
  }

  @Override
  public String toString() {
    return "Status [id=" + id + ", status=" + status + ", ticket=" + ticket + "]";
  }

}
