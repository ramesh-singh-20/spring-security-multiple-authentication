package com.alphacoder.multiauthentication.service;

import com.alphacoder.multiauthentication.entity.UserEntity;
import com.alphacoder.multiauthentication.repository.UserRepository;
import com.alphacoder.multiauthentication.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional= this.userRepository.findById(username);
        var userEntity= userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return new SecurityUser(userEntity);
    }
}
