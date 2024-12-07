package com.klef.jfsd.sdp.models;

import com.klef.jfsd.sdp.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("serial")
public class UserPrincipal extends User implements UserDetails {

    public UserPrincipal(User user) {
        // Copy properties from the original User object
        super.setId(user.getId());
        super.setFname(user.getFname());
        super.setLname(user.getLname());
        super.setUsername(user.getUsername());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setRole(user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the user's role into a SimpleGrantedAuthority collection
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+getRole().name()));
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify based on business rules
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify based on business rules
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify based on business rules
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify based on business rules
    }

    // Optionally override toString() or any additional methods if needed
}
