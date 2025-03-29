package edu.kata.spring_boot_security.service;

import java.util.List;
import java.util.Optional;

import edu.kata.spring_boot_security.entity.UserData;

public interface UserDataService {
    Optional<UserData> getUserDataById(Long id);
    List<UserData> getUserDataList();
    void deleteUserDataById(Long id);
    UserData addUserData(UserData userData);
    UserData updateUserData(UserData userData);
}
