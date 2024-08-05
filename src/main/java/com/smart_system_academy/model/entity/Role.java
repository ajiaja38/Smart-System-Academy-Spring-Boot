package com.smart_system_academy.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;

import com.smart_system_academy.utils.date.DateTimeUtil;
import com.smart_system_academy.utils.enumerate.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@SQLDelete(sql = "UPDATE role SET is_deleted = true WHERE role_id = ?")
public class Role {

  @Id
  @Column(name = "role_id")
  private String id;

  @Enumerated(EnumType.STRING)
  @Column(name = "role_name")
  private ERole role;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  public void onCreate() {
    this.id = "role-" + UUID.randomUUID().toString();
    this.createdAt = DateTimeUtil.getTimeZoneNow();
    this.updatedAt = DateTimeUtil.getTimeZoneNow();
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = DateTimeUtil.getTimeZoneNow();
  }
}
