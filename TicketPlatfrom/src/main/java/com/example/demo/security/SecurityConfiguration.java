package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests()
	        .requestMatchers("/ticket/create").hasAuthority("ADMIN")
	        .requestMatchers("/ticket/menu").hasAnyAuthority("USER" , "ADMIN")
	        .requestMatchers("/**").permitAll()  // This should be the last matcher
	        .and().formLogin()
	        .and().logout()
	        .and().exceptionHandling();

	    return http.build();
	}

	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.authorizeHttpRequests()
//		.requestMatchers("/ticket/create").hasAuthority("ADMIN")
////		.requestMatchers("/ticket/menu").hasAnyAuthority("USER" , "ADMIN")
////		.requestMatchers("/ticket/menu").hasAnyAuthority("USER")
////		.requestMatchers("/ticket/menu").hasAnyAuthority("ADMIN")
//		.requestMatchers("/**").permitAll()
//		.and().formLogin()
//		.and().logout()
//		.and().exceptionHandling();
//		
//		return http.build();
//	}
	
	
	
	@Bean
	databaseuserDetailService userDetailService() {
		return new databaseuserDetailService();
	}
	
	@Bean
	PasswordEncoder passwordEnoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests((requests) -> requests
	                .requestMatchers("/ticket/**").authenticated()
	                .anyRequest().permitAll()
	            )
	            .formLogin((form) -> form
	                .loginPage("/login")
	                .permitAll()
	            )
	            .logout((logout) -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout")
	                .permitAll()
	            );

	        return http.build();
	    }
}
