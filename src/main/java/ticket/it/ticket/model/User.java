package ticket.it.ticket.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull(message = "name cannot be null")
  private String name;

  @NotNull(message = "surname cannot be null")
  private String Surname;

  @NotNull(message = "age cannot be null")
  private Integer age;

  @PastOrPresent
  @NotNull(message = "date of birth cannt be null")
  private LocalDate dateOfBirth;

  @NotNull(message = "email cannt be null")
  private String email;

  @NotNull(message = "asign the status")
  private boolean availabilityStatus = true;

  @NotBlank(message = "cannt be null")
  private String password;

  @NotBlank(message = "cannt be null")
  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @OneToMany(mappedBy = "user")
  private List<Ticket> tickets;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public void setAvailabilityStatus(boolean availabilityStatus) {
    this.availabilityStatus = availabilityStatus;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void updateAvailability() {
    this.availabilityStatus = (this.tickets == null || this.tickets.isEmpty());
  }

  public boolean isAvailable() {
    return availabilityStatus;
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return Surname;
  }

  public void setSurname(String surname) {
    Surname = surname;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getAvailabilityStatus() {
    return availabilityStatus;
  }

  public void setAvailabilityStatus(Boolean availabilityStatus) {
    this.availabilityStatus = availabilityStatus;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", Surname=" + Surname + ", age=" + age + ", dateOfBirth="
        + dateOfBirth + ", email=" + email + ", availabilityStatus=" + availabilityStatus + "]";
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
