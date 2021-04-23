package com.alphacoder.multiauthentication.security.model;

import com.alphacoder.multiauthentication.entity.UserOtpEntity;
import com.alphacoder.multiauthentication.service.UserOtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class SecurityUserOtp implements UserDetails {
    private final UserOtpEntity userOtpEntity;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return this.userOtpEntity.getOtp();
    }

    @Override
    public String getUsername() {
        return this.userOtpEntity.getUsername();
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
}
