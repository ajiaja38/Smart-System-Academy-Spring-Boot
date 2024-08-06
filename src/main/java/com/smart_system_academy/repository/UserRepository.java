package com.smart_system_academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_system_academy.model.entity.User;
import com.smart_system_academy.utils.enumerate.ERole;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByEmail(String email);

  List<User> findByUserProfileRolesRoleAndUserProfileFirstNameIgnoreCaseContainingOrderByCreatedAtDesc(
      ERole role,
      String firstName);
}
