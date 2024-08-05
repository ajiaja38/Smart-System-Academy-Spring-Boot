package com.smart_system_academy.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;

import com.smart_system_academy.utils.date.DateTimeUtil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mst_user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE mst_user SET is_deleted = true WHERE user_id = ?")
public class User {

  @Id
  @Column(name = "user_id")
  private String id;

  @Column
  private String username;

  @Column
  private String email;

  @Column
  private String password;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_profile_id", referencedColumnName = "user_profile_id")
  private UserProfile userProfile;

  @PrePersist
  public void onCreate() {
    this.id = "User-" + UUID.randomUUID().toString();
    this.createdAt = DateTimeUtil.getTimeZoneNow();
    this.updatedAt = DateTimeUtil.getTimeZoneNow();
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = DateTimeUtil.getTimeZoneNow();
  }
}
