package libm.backend.lib.service.service.impl;

import libm.backend.lib.entity.User;
import libm.backend.lib.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Include both roles and permissions
        var authorities = user.getRoles().stream()
                .flatMap(role -> {
                    Stream<SimpleGrantedAuthority> roleAuthority =
                            Stream.of(new SimpleGrantedAuthority(role.getName()));
                    Stream<SimpleGrantedAuthority> permAuthority = role.getPermissions() != null
                            ? role.getPermissions().stream().map(p -> new SimpleGrantedAuthority(p.getCode()))
                            : Stream.empty();
                    return Stream.concat(roleAuthority, permAuthority);
                })
                .collect(Collectors.toSet());

        System.out.println("UserDetails loaded → " + user.getEmail());
        System.out.println("Authorities: " + authorities);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}

