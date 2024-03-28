package ua.com.foxminded.universitycms.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.foxminded.universitycms.model.Admin;
import ua.com.foxminded.universitycms.model.Student;
import ua.com.foxminded.universitycms.model.Teacher;
import ua.com.foxminded.universitycms.model.User;
import ua.com.foxminded.universitycms.repository.AdminRepository;
import ua.com.foxminded.universitycms.repository.StudentRepository;
import ua.com.foxminded.universitycms.repository.TeacherRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CustomUserDetailsService(AdminRepository adminRepository,
                                    StudentRepository studentRepository,
                                    TeacherRepository teacherRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        org.springframework.security.core.userdetails.User user1 = Optional.empty()
                .or(() -> adminRepository.findByLogin(login))
                .or(() -> studentRepository.findByLogin(login))
                .or(() -> teacherRepository.findByLogin(login))
                .map(user -> (User) user)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getLogin(),
                        user.getPassword(),
                        mapRolesToAuthorities(user)))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return user1;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(User user) {
        if (user instanceof Admin) {
            return List.of(new SimpleGrantedAuthority("ADMIN"),
                    new SimpleGrantedAuthority("TEACHER"),
                    new SimpleGrantedAuthority("STUDENT")
            );
        } else if (user instanceof Teacher) {
            return List.of(new SimpleGrantedAuthority("TEACHER"));
        } else if (user instanceof Student) {
            return List.of(new SimpleGrantedAuthority("STUDENT"));
        } else {
            return List.of();
        }
    }
}

