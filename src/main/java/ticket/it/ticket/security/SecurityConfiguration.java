package ticket.it.ticket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  @Bean
  @SuppressWarnings("removal")
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/ticket/create").hasAuthority("ROLE_ADMIN")
        .requestMatchers("/ticket/edit/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
        .requestMatchers(HttpMethod.POST, "/ticket/**").hasAuthority("ROLE_ADMIN")
        .requestMatchers("/ticket", "/ticket/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
        .requestMatchers("/**").permitAll()
        .and()
        .formLogin()
        .and()
        .logout()
        .and()
        .exceptionHandling();

    return http.build();

  }

  @Bean
  DatabaseUserDetailService UserDetailService() {
    return new DatabaseUserDetailService();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  @SuppressWarnings("deprecation")
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();

    authprovider.setUserDetailsService(UserDetailService());
    authprovider.setPasswordEncoder(passwordEncoder());

    return authprovider;

  }
}
