package com.alphacoder.multiauthentication.config;

import com.alphacoder.multiauthentication.security.filter.UserAuthenticationFilter;
import com.alphacoder.multiauthentication.security.filter.UserTokenFilter;
import com.alphacoder.multiauthentication.security.provider.UserTokenAuthenticationProvider;
import com.alphacoder.multiauthentication.security.provider.UsernameOtpAuthenticationProvider;
import com.alphacoder.multiauthentication.security.provider.UsernamePasswordAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class UserConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Autowired
    private UsernameOtpAuthenticationProvider usernameOtpAuthenticationProvider;

    @Autowired
    private UserTokenAuthenticationProvider userTokenAuthenticationProvider;

    @Bean
    public UserAuthenticationFilter userAuthenticationFilter(){
        return new UserAuthenticationFilter();
    };

    @Bean
    public UserTokenFilter userTokenFilter() throws Exception {
        return new UserTokenFilter(this.authenticationManagerBean());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(this.usernamePasswordAuthenticationProvider)
            .authenticationProvider(this.usernameOtpAuthenticationProvider)
            .authenticationProvider(this.userTokenAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(this.userAuthenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(this.userTokenFilter(), UserAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
