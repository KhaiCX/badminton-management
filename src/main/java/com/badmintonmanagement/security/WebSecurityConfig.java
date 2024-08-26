package com.badmintonmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public BadmintonUserDetailsService badmintonUserDetailsService() {
		return new BadmintonUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
         .authorizeHttpRequests((auth) -> auth
				 .requestMatchers("/", "/articles/**", "/members/**", "/schedules/**", "/login").permitAll()
				 .requestMatchers("/admin/**").hasAuthority("admin")
         ).formLogin(form -> form.loginPage("/login")
						 .loginProcessingUrl("/login")
						 .usernameParameter("account")
								 .defaultSuccessUrl("/admin", true)
						 .permitAll())
				 .logout((logout -> logout
						 .logoutUrl("/logout")
						 .logoutSuccessUrl("/")
						 .invalidateHttpSession(true)
						 .deleteCookies("JSESSIONID")
						 .permitAll()))
				 .userDetailsService(badmintonUserDetailsService());
		 return http.build();
	}
	@Bean
	WebSecurityCustomizer configureWebSecurity() {
		return (web) -> web.ignoring().requestMatchers("/client/**", "/webjars/**");
	}
}
