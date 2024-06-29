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

    @GetMapping("/findById/{userId}")
    public User findById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @GetMapping("/findUserWithDetailedAccounts/{userId}")
    public User findUserWithDetailedAccounts(@PathVariable Long userId){
        return userService.findUserWithDetailedAccounts(userId);
    }

    @PostMapping("/create")
    public User create(){
        return userService.create();
    }

    @PostMapping("/deactivate/{userId}")
    public User deactivate(@PathVariable Long userId){
        return userService.deactivate(userId);
    }

    @PostMapping("/activate/{userId}")
    public User activate(@PathVariable Long userId){
        return userService.activate(userId);
    }


}
