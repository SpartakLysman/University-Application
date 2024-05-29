package ua.com.foxminded.universitycms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityCmsApplication {

	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("admin"));
		SpringApplication.run(UniversityCmsApplication.class, args);
	}
}
