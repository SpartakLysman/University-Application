package ua.com.foxminded.universitycms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(requests -> requests.requestMatchers("/css/**", "/webjars/**", "/assets/**", "/",
				"/admins", "/courses", "/groups", "/students", "/teachers").permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login.html").loginProcessingUrl("/perform_login")
						.defaultSuccessUrl("/homepage.html", true).failureUrl("/login.html?error=true").permitAll())
				.logout(logout -> logout.permitAll().logoutSuccessUrl("/"));

		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails student = User.withUsername("student").password(passwordEncoder().encode("studentPass"))
				.roles("STUDENT").build();
		UserDetails teacher = User.withUsername("teacher").password(passwordEncoder().encode("teacherPass"))
				.roles("TEACHER").build();
		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("adminPass"))
				.roles("ADMIN", "TEACHER", "STUDENT").build();
		return new InMemoryUserDetailsManager(student, teacher, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}