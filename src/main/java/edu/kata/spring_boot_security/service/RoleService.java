package edu.kata.spring_boot_security.service;

import java.util.List;

import edu.kata.spring_boot_security.entity.Role;

public interface RoleService {
    List<Role> getRolesList();
}
