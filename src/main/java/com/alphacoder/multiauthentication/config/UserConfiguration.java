package com.alphacoder.multiauthentication.config;

import com.alphacoder.multiauthentication.security.filter.UserAuthenticationFilter;
import com.alphacoder.multiauthentication.security.filter.UserTokenAuthenticationFilter;
import com.alphacoder.multiauthentication.security.provider.UserTokenAuthenticationProvider;
import com.alphacoder.multiauthentication.security.provider.UsernameOtpAuthenticationProvider;
import com.alphacoder.multiauthentication.security.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public UserTokenAuthenticationFilter userTokenFilter() throws Exception {
        return new UserTokenAuthenticationFilter(this.authenticationManagerBean());
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

    @Bean
    public InitializingBean initializingBean(){
        return () -> {
            SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);

        };
    }


}
