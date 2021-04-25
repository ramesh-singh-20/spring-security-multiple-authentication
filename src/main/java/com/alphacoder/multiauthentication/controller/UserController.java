package com.alphacoder.multiauthentication.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value= "/hello")
    public String hello(){
        return "Hello!!!" + " "+ SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
