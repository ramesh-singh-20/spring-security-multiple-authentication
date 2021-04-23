package com.alphacoder.multiauthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value= "/login")
    public String login(){
        return "Success!!!";
    }

}
