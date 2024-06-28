package com.teya.bank.service;

import com.teya.bank.entity.User;

import java.util.List;

public interface UserService {
    User create();
    List<User> findAll();
    User findById(Long id);
    User findUserWithDetailedAccounts(Long id);
    User findActiveUserById(Long id);
    User deactivate(Long id);
    User activate(Long id);
}
