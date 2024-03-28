package ua.com.foxminded.universitycms.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/css/**", "/webjars/**", "/assets/**", "/",
				"/admins", "/courses", "/groups", "/students", "/teachers").permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login.html").loginProcessingUrl("/process-login")
						.defaultSuccessUrl("/menu.html").failureUrl("/login.html?error=true").permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true)
						.permitAll());

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
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).build();
	}

	@Bean
	public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}