package com.smart_system_academy.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart_system_academy.model.entity.Role;
import com.smart_system_academy.repository.RoleRepository;
import com.smart_system_academy.service.RoleService;
import com.smart_system_academy.utils.enumerate.ERole;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role getOrSave(ERole role) {
    Optional<Role> currentRole = this.roleRepository.findByRole(role);

    if (currentRole.isPresent()) {
      return currentRole.get();
    }

    Role newRole = Role.builder()
        .role(role)
        .build();

    return this.roleRepository.save(newRole);
  }

}
