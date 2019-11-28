package com.csz.service.impl;

import com.csz.dao.PermissionDao;
import com.csz.domain.Permission;
import com.csz.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findNotPermissionesByRId(String id) {
        return permissionDao.findNotPermissionesByRId(id);
    }
}
