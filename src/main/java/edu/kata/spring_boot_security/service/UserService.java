package edu.kata.spring_boot_security.service;

import java.util.List;
import java.util.Optional;

import edu.kata.spring_boot_security.entity.User;

public interface UserService {
    User addUser(User user);
    List<User> getUserList();
    Optional<User> getUserById(Long userId);
    User updateUser(User user);
    void deleteUser(User user);
}
