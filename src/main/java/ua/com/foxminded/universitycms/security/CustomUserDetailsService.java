package ua.com.foxminded.universitycms.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.foxminded.universitycms.model.UserRole;
import ua.com.foxminded.universitycms.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login)
                .map(user -> new User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRole())))
                .orElseThrow();
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(UserRole role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
