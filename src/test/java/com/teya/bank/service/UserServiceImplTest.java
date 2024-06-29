package com.teya.bank.service;

import com.teya.bank.entity.User;
import com.teya.bank.error.UserNotActiveException;
import com.teya.bank.error.UserNotFoundException;
import com.teya.bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setActive(true);

        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.create();

        assertNotNull(createdUser.getId());
    }

    @Test
    public void testFindById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
    }

    @Test
    public void findActiveUserById() {
        User user = new User();
        user.setActive(false);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        assertThrows(UserNotActiveException.class, () -> userService.findActiveUserById(1L));
    }

    @Test
    public void testDeactivate() {
        User user = new User();
        user.setId(1L);
        user.setActive(true);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.deactivate(1L);
        assertFalse(user.isActive());
    }

    @Test
    public void testActivate() {
        User user = new User();
        user.setId(1L);
        user.setActive(false);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.activate(1L);
        assertTrue(user.isActive());
    }
}
