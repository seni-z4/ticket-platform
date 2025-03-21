package ticket.it.ticket.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ticket.it.ticket.model.Role;
import ticket.it.ticket.model.User;

public class DatabaseUserDetails implements UserDetails {

  private final Integer id;
  private final String username;
  private final String password;
  private final Set<GrantedAuthority> authorities;

  public DatabaseUserDetails(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();

    this.authorities = new HashSet<GrantedAuthority>();
    for (Role role : user.getRoles()) {
      this.authorities.add(new SimpleGrantedAuthority(role.getName())); // ✅ FIXED
    }

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  public Integer getId() {
    return this.id;
  }

  @Override
  public boolean isAccountNonExpired() {

    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
