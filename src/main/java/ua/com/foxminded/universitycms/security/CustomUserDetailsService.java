package ua.com.foxminded.universitycms.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.repository.AdminRepository;
import ua.com.foxminded.universitycms.repository.StudentRepository;
import ua.com.foxminded.universitycms.repository.TeacherRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final AdminRepository adminRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;

	public CustomUserDetailsService(AdminRepository adminRepository, StudentRepository studentRepository,
			TeacherRepository teacherRepository) {
		this.adminRepository = adminRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Admin> admin = adminRepository.findByLogin(login);
		if (admin.isPresent()) {
			return new User(admin.get().getLogin(), admin.get().getPassword(), mapRolesToAuthorities(admin));
		}

		Optional<Student> student = studentRepository.findByLogin(login);
		if (student.isPresent()) {
			return new User(student.get().getLogin(), student.get().getPassword(), mapRolesToAuthorities(student));
		}

		Optional<Teacher> teacher = teacherRepository.findByLogin(login);
		if (teacher.isPresent()) {
			return new User(teacher.get().getLogin(), teacher.get().getPassword(), mapRolesToAuthorities(teacher));
		}

		throw new UsernameNotFoundException("Invalid username or password.");
	}
//		org.springframework.security.core.userdetails.User user1 = Optional.empty()
//				.or(() -> adminRepository.findByLogin(login)).or(() -> studentRepository.findByLogin(login))
//				.or(() -> teacherRepository.findByLogin(login)).map(user -> (User) user)
//				.map(user -> new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
//						mapRolesToAuthorities(user)))
//				.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
//		return user1;

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Object user) {
		if (user instanceof Admin) {
			return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("TEACHER"),
					new SimpleGrantedAuthority("STUDENT"));
		} else if (user instanceof Teacher) {
			return List.of(new SimpleGrantedAuthority("TEACHER"));
		} else if (user instanceof Student) {
			return List.of(new SimpleGrantedAuthority("STUDENT"));
		} else {
			return List.of();
		}
	}
}
