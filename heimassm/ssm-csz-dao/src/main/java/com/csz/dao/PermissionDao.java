package com.csz.dao;

import com.csz.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id}) ")
    public List<Permission> findAllByRid(String id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id}) ")
    public List<Permission> findNotPermissionesByRId(String id);

    @Insert("insert into permission(permissionname,url) values(#{permissionName},#{url}) ")
    public void save(Permission permission);

    @Select("select * from permission ")
    public List<Permission> findAll();


}
