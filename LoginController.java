package com.nikhil.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showMyLohinPage(){
        return "fancy-login";
    }

    //Adding request mapping for Access-denied
    @GetMapping("/access-denied")
    public String accessDenied(){
        return "accessDenied";
    }
}
