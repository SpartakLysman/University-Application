package ua.com.foxminded.universitycms.config;

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
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests
				.requestMatchers("static/css/**", "/?continue", "/webjars/**", "/assets/**").permitAll()
				.requestMatchers("assignRole").hasAnyAuthority("ADMIN").requestMatchers("menu")
				.hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
				//
				.requestMatchers("/courses/").hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
				.requestMatchers("/courses/create").hasAnyAuthority("ADMIN", "TEACHER")
				.requestMatchers("/courses/{id}/delete").hasAnyAuthority("ADMIN")
				.requestMatchers("/courses/{id}/update").hasAnyAuthority("ADMIN", "TEACHER")
				.requestMatchers("courses/getById/{id}").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
				.requestMatchers("courses/{id}/assignTeacher").hasAnyAuthority("ADMIN")
				.requestMatchers("courses/{id}/assignGroup").hasAnyAuthority("ADMIN")
				//
				.requestMatchers("/groups/").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
				.requestMatchers("/groups/create").hasAnyAuthority("ADMIN").requestMatchers("/groups/{id}/delete")
				.hasAnyAuthority("ADMIN").requestMatchers("/groups/{id}/update").hasAnyAuthority("ADMIN")
				.requestMatchers("/groups/getById/{id}").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
				.requestMatchers("/groups/{id}/assignStudent").hasAnyAuthority("ADMIN")
				//
				.requestMatchers("/teachers").hasAnyAuthority("ADMIN", "TEACHER").requestMatchers("/teachers/create")
				.hasAnyAuthority("ADMIN").requestMatchers("/teachers/{id}/delete").hasAnyAuthority("ADMIN")
				.requestMatchers("/teachers/{id}/update").hasAnyAuthority("ADMIN")
				.requestMatchers("/teachers/getById/{id}").hasAnyAuthority("ADMIN", "TEACHER")
				//
				.requestMatchers("/students/").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
				.requestMatchers("/students/create").hasAnyAuthority("ADMIN").requestMatchers("/students/{id}/delete")
				.hasAnyAuthority("ADMIN").requestMatchers("/students/{id}/update").hasAnyAuthority("ADMIN")
				.requestMatchers("/students/getById/{id}").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
				.requestMatchers("/students/{studentId}/assignCourse").hasAnyAuthority("ADMIN", "STUDENT")

				.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/menu")
						.permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/logout").permitAll());

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().requestMatchers("/css/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}