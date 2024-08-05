package com.smart_system_academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_system_academy.model.entity.Role;
import com.smart_system_academy.utils.enumerate.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
  Optional<Role> findByRole(ERole role);
}
