package com.alphacoder.multiauthentication.security.provider;

import com.alphacoder.multiauthentication.security.authentication.UsernamePasswordAuthentication;
import com.alphacoder.multiauthentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username= authentication.getName();
        var password= String.valueOf(authentication.getCredentials());

        var user= this.userService.loadUserByUsername(username);

        if(this.passwordEncoder.matches(password, user.getPassword())){
            return new UsernamePasswordAuthentication(username, password, user.getAuthorities());
        }
        throw new BadCredentialsException("Login failed.");
    }

    @Override
    public boolean supports(Class<?> authType) {
        return UsernamePasswordAuthentication.class.equals(authType);
    }
}
