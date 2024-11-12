package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.persistence.entity.Role;
import com.simplebank.simplebankapp.service.interfaces.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    @Override
    public Role findByRoleName(String roleName) {
        return null;
    }

    @Override
    public List<Role> findAllRole() {
        return null;
    }

    @Override
    public Role createRole(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public Role updateRole(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public void deleteRole(String roleName) {

    }
}
