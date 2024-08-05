package com.smart_system_academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_system_academy.model.entity.UserProfile;

@Repository
public interface UserProfileRespository extends JpaRepository<UserProfile, String> {
  Optional<UserProfile> findByPhoneNumber(String phoneNumber);
}
