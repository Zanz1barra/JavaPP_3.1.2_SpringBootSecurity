package edu.kata.spring_boot_security.service;

import java.util.List;
import java.util.Optional;

import edu.kata.spring_boot_security.entity.User;

public interface UserService {
    Optional<User> getUserById(Long id);
    List<User> getUsersList();
    void deleteUserById(Long id);
    User addUser(User user);
    User updateUser(User user);
}
