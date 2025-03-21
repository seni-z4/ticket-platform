package ticket.it.ticket.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ticket.it.ticket.model.User;
import ticket.it.ticket.repository.UserRepository;

public class DatabaseUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> userAmpt = userRepository.findByUsernameIgnoreCase(username);

    if (userAmpt.isEmpty()) {

      throw new UsernameNotFoundException(" not found");
    }

    return new DatabaseUserDetails(userAmpt.get());
  }

}
