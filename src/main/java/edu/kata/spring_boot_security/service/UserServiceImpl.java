package edu.kata.spring_boot_security.service;

import edu.kata.spring_boot_security.entity.User;
import edu.kata.spring_boot_security.entity.UserData;
import edu.kata.spring_boot_security.repository.UserDataRepository;
import edu.kata.spring_boot_security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserDataRepository userDataRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDataRepository.save(user.getUserData());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        userDataRepository.save(user.getUserData());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDataRepository.delete(user.getUserData());
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteUserByUserDataId(Long id) {
        Optional<UserData> userData = userDataRepository.findById(id);
        if (userData.isPresent()) {
            if (userData.get().getUser() == null) {
                userDataRepository.delete(userData.get());
            } else {
                deleteUser(userData.get().getUser());
            }
        }
    }
}
