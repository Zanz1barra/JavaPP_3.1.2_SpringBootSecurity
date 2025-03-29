package edu.kata.spring_boot_security.service;

import edu.kata.spring_boot_security.entity.UserData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import edu.kata.spring_boot_security.repository.UserDataRepository;


@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository userDataRepository;

    public UserDataServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public List<UserData> getUserDataList() {
        return userDataRepository.findAll();
    }

    @Override
    public Optional<UserData> getUserDataById(Long id) {
        return userDataRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteUserDataById(Long id) {
        Optional<UserData> deletingUser = getUserDataById(id);
        deletingUser.ifPresent(userDataRepository::delete);
    }

    @Override
    @Transactional
    public UserData addUserData(UserData userData) {
        return userDataRepository.save(userData);
    }

    @Override
    @Transactional
    public UserData updateUserData(UserData userData) {
        return userDataRepository.save(userData);
    }
}
