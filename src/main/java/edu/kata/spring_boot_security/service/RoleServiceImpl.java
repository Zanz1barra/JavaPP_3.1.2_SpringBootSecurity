package edu.kata.spring_boot_security.service;

import org.springframework.stereotype.Service;

import java.util.List;

import edu.kata.spring_boot_security.entity.Role;
import edu.kata.spring_boot_security.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRolesList() {
        return roleRepository.findAll();
    }
}
