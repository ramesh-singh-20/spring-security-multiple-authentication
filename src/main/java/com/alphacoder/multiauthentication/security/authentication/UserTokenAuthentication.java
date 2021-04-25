package com.alphacoder.multiauthentication.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserTokenAuthentication extends UsernamePasswordAuthenticationToken {
    public UserTokenAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UserTokenAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
