package com.teya.bank.controller;

import com.teya.bank.entity.User;
import com.teya.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/findUserWithDetailedAccounts/{id}")
    public User findUserWithDetailedAccounts(@PathVariable Long id){
        return userService.findUserWithDetailedAccounts(id);
    }

    @PutMapping("/create")
    public User create(){
        return userService.create();
    }

    @PostMapping("/deactivate/{id}")
    public User deactivate(@PathVariable Long id){
        return userService.deactivate(id);
    }

    @PostMapping("/activate/{id}")
    public User activate(@PathVariable Long id){
        return userService.activate(id);
    }


}
