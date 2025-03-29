package edu.kata.spring_boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.kata.spring_boot_security.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

}
