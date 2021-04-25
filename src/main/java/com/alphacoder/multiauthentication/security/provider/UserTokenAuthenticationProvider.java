package com.alphacoder.multiauthentication.security.provider;

import com.alphacoder.multiauthentication.security.authentication.UserTokenAuthentication;
import com.alphacoder.multiauthentication.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserTokenAuthenticationProvider implements AuthenticationProvider {
    private final UserTokenService service;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username= authentication.getName();
        var token= authentication.getCredentials();
        var tokenUser= this.service.loadUserByUsername(username);

        if(token.equals(tokenUser.getPassword())){
            Authentication tokenAuthentication= new UserTokenAuthentication(username, token, tokenUser.getAuthorities());
            return tokenAuthentication;
        }

        throw new BadCredentialsException("Token not valid.");

    }

    @Override
    public boolean supports(Class<?> authType) {
        return UserTokenAuthentication.class.equals(authType);
    }
}
