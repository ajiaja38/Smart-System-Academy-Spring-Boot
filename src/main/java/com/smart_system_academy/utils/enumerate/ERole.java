package com.smart_system_academy.utils.enumerate;

public enum ERole {
  ROLE_SUPER_ADMIN("Super Admin"),
  ROLE_ADMIN("Admin"),
  ROLE_REVIEWER("Reviewer"),
  ROLE_USER("User");

  private final String value;

  ERole(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
