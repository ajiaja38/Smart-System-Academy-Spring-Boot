package com.smart_system_academy.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AppUser implements UserDetails {

  private String id;
  private String email;
  private String password;
  private List<Role> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

    for (Role role : roles) {
      simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
    }

    return simpleGrantedAuthorities;

  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

}
