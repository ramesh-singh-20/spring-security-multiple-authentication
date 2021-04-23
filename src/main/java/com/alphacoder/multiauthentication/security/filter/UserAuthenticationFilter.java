package com.alphacoder.multiauthentication.security.filter;

import com.alphacoder.multiauthentication.entity.UserOtpEntity;
import com.alphacoder.multiauthentication.repository.OtpRepository;
import com.alphacoder.multiauthentication.security.authentication.UsernameOtpAuthentication;
import com.alphacoder.multiauthentication.security.authentication.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var username= request.getHeader("username");
        var password= request.getHeader("password");
        var otp= request.getHeader("otp");

        if(null== otp){
            Authentication authentication= new UsernamePasswordAuthentication(username, password);
            authentication= manager.authenticate(authentication);
            if(authentication.isAuthenticated()){
                String code= String.valueOf(new Random().nextInt(9999)+1000);
                UserOtpEntity entity= new UserOtpEntity();
                entity.setUsername(username);
                entity.setOtp(code);
                otpRepository.save(entity);
            }
        }else{
            Authentication authentication= new UsernameOtpAuthentication(username, otp);
            authentication= manager.authenticate(authentication);
            if(authentication.isAuthenticated()){
                response.setHeader("Authorization", UUID.randomUUID().toString());
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }


}
