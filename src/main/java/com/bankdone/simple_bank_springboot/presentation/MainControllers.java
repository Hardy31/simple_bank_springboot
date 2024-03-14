package com.bankdone.simple_bank_springboot.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainControllers {
    @GetMapping("/")
    public String hello(){
        return "<h2> Hello ! <h2>";
    }
}
