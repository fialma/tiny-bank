package com.teya.bank.service;

import com.teya.bank.error.UserNotActiveException;
import com.teya.bank.error.UserNotFoundException;
import com.teya.bank.entity.User;
import com.teya.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User create() {
        User user = new User();
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public User findActiveUserById(Long id) {
        User user = findById(id);
        if(user.isActive())
            return user;
        throw new UserNotActiveException(id);
    }

    @Override
    public User findUserWithDetailedAccounts(Long id) {
        Optional<User> userOptional = userRepository.findUserWithDetailedAccounts(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public User deactivate(Long id) {
        User user = findById(id);
        if (user !=null) {
            user.setActive(false);
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public User activate(Long id) {
        User user = findById(id);
        if (user !=null) {
            user.setActive(true);
            user = userRepository.save(user);
        }
        return user;
    }
}
