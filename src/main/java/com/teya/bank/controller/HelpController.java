package com.teya.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/help")
public class HelpController {

    @GetMapping("/")
    public String help(){
        return """
                <h2>General Help to use the application:</h2>
                
                """;
    }






}
