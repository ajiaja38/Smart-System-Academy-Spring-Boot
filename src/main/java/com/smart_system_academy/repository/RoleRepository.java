package com.smart_system_academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_system_academy.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
