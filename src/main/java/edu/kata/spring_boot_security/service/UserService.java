package edu.kata.spring_boot_security.service;

import edu.kata.spring_boot_security.entity.User;

public interface UserService {
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    void deleteUserByUserDataId(Long id);
}
