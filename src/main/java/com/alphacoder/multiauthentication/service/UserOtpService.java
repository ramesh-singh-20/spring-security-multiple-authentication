package com.alphacoder.multiauthentication.service;

import com.alphacoder.multiauthentication.repository.OtpRepository;
import com.alphacoder.multiauthentication.security.model.SecurityUserOtp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserOtpService implements UserDetailsService {
    private final OtpRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var otpOptional= this.repository.findUserOtpByUsername(username);

        var userOtpEntity= otpOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return new SecurityUserOtp(userOtpEntity);
    }
}
