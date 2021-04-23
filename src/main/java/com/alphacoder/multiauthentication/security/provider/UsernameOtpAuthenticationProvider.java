package com.alphacoder.multiauthentication.security.provider;

import com.alphacoder.multiauthentication.security.authentication.UsernameOtpAuthentication;
import com.alphacoder.multiauthentication.service.UserOtpService;
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
public class UsernameOtpAuthenticationProvider implements AuthenticationProvider {
    private final UserOtpService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username= authentication.getName();
        var otp= String.valueOf(authentication.getCredentials());

        var userOtp= service.loadUserByUsername(username);

        if(otp.equals(userOtp.getPassword())){
            return new UsernameOtpAuthentication(username, otp, userOtp.getAuthorities());
        }
        throw new BadCredentialsException("Incorrect OTP.");

    }

    @Override
    public boolean supports(Class<?> authType) {
        return UsernameOtpAuthentication.class.equals(authType);
    }
}
