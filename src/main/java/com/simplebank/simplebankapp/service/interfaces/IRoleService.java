package com.simplebank.simplebankapp.service.interfaces;

import com.simplebank.simplebankapp.persistence.entity.Role;

import java.util.List;

public interface IRoleService {
    Role findByRoleName(String roleName);
    List<Role> findAllRole();
    Role createRole(RoleDTO roleDTO);
    Role updateRole(RoleDTO roleDTO);
    void deleteRole(String roleName);
}
