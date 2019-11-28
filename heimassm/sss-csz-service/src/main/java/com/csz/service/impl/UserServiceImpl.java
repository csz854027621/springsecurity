package com.csz.service.impl;

import com.csz.dao.RoleDao;
import com.csz.dao.UserInfoDao;
import com.csz.domain.Role;
import com.csz.domain.UserInfo;
import com.csz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoDao.findOneByUsername(username);

        User user = new User(
                userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus() == 1 ? true : false, true, true, true,
                getAuthorities(userInfo.getRoles()));

        return user;
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {

        List<SimpleGrantedAuthority> ls = new ArrayList<>();
        for (Role role : roles) {
            ls.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }

        return ls;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userInfoDao.findAll();
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoDao.findById(id);
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    @Override
    public void addRoles(String userId, String[] roleIds)  {
            for (String roleId:roleIds){
               addRole(userId,roleId);
            }
    }

    @Override
    public void addRole(String userId, String roleId)  {
       userInfoDao.addRole(userId,roleId);
    }

    @Override
    public List<Role> findNotUserRoleById(String id) {
        return roleDao.findNotUsersByUId(id);
    }


}
