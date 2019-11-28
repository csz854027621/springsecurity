package com.csz.dao;

import com.csz.domain.Role;
import com.csz.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {


    @Select("select * from role where id in (select roleId from users_role where userId=#{id} ) ")
    public List<Role> findByUId(String id);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{id} ) ")
    public List<Role> findNotUsersByUId(String id);


    @Select("select * from role where id in (select roleId from users_role where userId=#{id} ) ")
    @Results(id="listRoles",value={
            @Result(id=true,column = "id",property="id"),
            @Result(column = "roleName",property="roleName"),
            @Result(column = "roleDesc",property="roleDesc"),
            @Result(column = "id",property="permissions",javaType = List.class,
                    many = @Many(fetchType= FetchType.LAZY,
                            select = "com.csz.dao.PermissionDao.findAllByRid"))
    })
    public List<Role> findRolesAndPermissionByUId(String id);

    @Select("select * from role where id=#{id} ")
    @ResultMap("listRoles")
    public Role findRoleByRid(String id);

    @Select("select * from role ")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) " +
            " values(#{roleName},#{roleDesc})")
    public void save(Role role);


    @Insert("insert into role_permission(permissionId,roleId) " +
            " values(#{permissionId},#{roleId})")
    public void addPermission(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
