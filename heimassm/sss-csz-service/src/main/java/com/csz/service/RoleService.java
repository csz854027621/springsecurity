package com.csz.service;

import com.csz.domain.Permission;
import com.csz.domain.Role;
import java.util.List;

public interface RoleService {


    public List<Role> findAll() throws Exception;

    public void save(Role role);

    public Role findRoleByRid(String id);

    public void  addPermission(String roleId,String permissionId);
    public void  addPermissions(String roleId,String [] permissionIds);

}
