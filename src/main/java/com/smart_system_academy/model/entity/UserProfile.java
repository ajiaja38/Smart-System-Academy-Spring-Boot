package com.smart_system_academy.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.smart_system_academy.utils.date.DateTimeUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_profile")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

  @Id
  @Column(name = "user_profile_id")
  private String id;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column
  private String address;

  @Column(name = "phone_number", unique = true, nullable = false)
  private String phoneNumber;

  @Column
  private String avatar;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "trx_user_role", joinColumns = @JoinColumn(name = "user_profile_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;

  @OneToOne(mappedBy = "userProfile")
  @JoinColumn(name = "user_id")
  private User user;

  @PrePersist
  public void onCreate() {
    this.id = "Profile-" + UUID.randomUUID().toString();
    this.createdAt = DateTimeUtil.getTimeZoneNow();
    this.updatedAt = DateTimeUtil.getTimeZoneNow();
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = DateTimeUtil.getTimeZoneNow();
  }
}
