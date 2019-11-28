package com.csz.service;

import com.csz.domain.Role;
import com.csz.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<UserInfo> findAll() throws Exception;

    public UserInfo findById(String id);

    public void save(UserInfo userInfo);

    public void addRole(String userId,String roleId);

    public List<Role> findNotUserRoleById(String id);

    public void addRoles(String userId, String[] roleIds);
}
