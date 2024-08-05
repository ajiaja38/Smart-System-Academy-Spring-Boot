package com.smart_system_academy.service;

import com.smart_system_academy.model.entity.Role;
import com.smart_system_academy.utils.enumerate.ERole;

public interface RoleService {

  Role getOrSave(ERole role);

}
