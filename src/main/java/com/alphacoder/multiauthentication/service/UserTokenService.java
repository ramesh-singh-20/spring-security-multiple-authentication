package com.alphacoder.multiauthentication.service;

import com.alphacoder.multiauthentication.entity.UserTokenEntity;
import com.alphacoder.multiauthentication.repository.TokenRepository;
import com.alphacoder.multiauthentication.security.model.SecurityUserToken;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserTokenService implements UserDetailsService {
    private final TokenRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var tokenOptional= this.repository.findById(username);
        var tokenEntity= tokenOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return new SecurityUserToken(tokenEntity);
    }
}
