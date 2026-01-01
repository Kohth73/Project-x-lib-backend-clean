package libm.backend.lib.security;

import libm.backend.lib.entity.Permission;
import libm.backend.lib.entity.Role;
import libm.backend.lib.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private final User user;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public CustomUserDetails(User user) {
        this.user = user;

        // initialize roles set if null
        Set<Role> roles = user.getRoles() != null ? user.getRoles() : new HashSet<>();

        for (Role role : roles) {
            if (role.getName() != null) {
                // no need to prepend "ROLE_" if already in DB
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            Set<Permission> permissions = role.getPermissions() != null ? role.getPermissions() : new HashSet<>();
            for (Permission permission : permissions) {
                if (permission.getName() != null) {
                    authorities.add(new SimpleGrantedAuthority(permission.getName()));
                }
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}

