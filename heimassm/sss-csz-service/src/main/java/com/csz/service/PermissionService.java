package com.csz.service;

import com.csz.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionService {


    public void save(Permission permission);

    public List<Permission> findAll();

    public List<Permission> findNotPermissionesByRId(String id);




}
