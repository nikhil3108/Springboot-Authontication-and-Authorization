package com.nikhil.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome() {

        return "home";
    }
    @GetMapping("/leaders")
    public String showleaders() {

        return "leaders";
    }
    @GetMapping("/admin")
    public String showadmin() {

        return "admin";
    }
}
