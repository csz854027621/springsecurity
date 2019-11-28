package com.csz.service.impl;

import com.csz.dao.RoleDao;
import com.csz.domain.Permission;
import com.csz.domain.Role;
import com.csz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findRoleByRid(String id) {
        return roleDao.findRoleByRid(id);
    }

    @Override
    public void addPermission(String roleId, String permissionId) {
        roleDao.addPermission(roleId,permissionId);
    }

    @Override
    public void addPermissions(String roleId, String[] permissionIds) {
            for (String permissionId:permissionIds){
                roleDao.addPermission(roleId,permissionId);
            }
    }
}
