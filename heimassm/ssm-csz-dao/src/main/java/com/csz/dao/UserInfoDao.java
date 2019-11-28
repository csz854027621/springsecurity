package com.csz.dao;

import com.csz.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserInfoDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,column ="id",property = "id"),
            @Result(column ="username",property = "username"),
            @Result(column ="email",property = "email"),
            @Result(column ="password",property = "password"),
            @Result(column ="phoneNum",property = "phoneNum"),
            @Result(column ="status",property = "status"),
            @Result(column = "id" ,property = "roles",javaType = List.class,
            many=@Many(fetchType = FetchType.LAZY,
                    select ="com.csz.dao.RoleDao.findByUId" )
            )
    })
    public UserInfo findOneByUsername(String username);


    @Select("select * from users ")
    public List<UserInfo> findAll() throws Exception;


    @Select("select * from users where id=#{id} ")
    @Results({
            @Result(id=true,property = "id",column="id"),
            @Result(property = "username",column="username"),
            @Result(property = "email",column="email"),
            @Result(property = "password",column="password"),
            @Result(property = "phoneNum",column="phoneNum"),
            @Result(property = "status",column="status"),
            @Result(property = "roles",column="id",javaType = List.class,many=@Many(
                    fetchType=FetchType.LAZY,select = "com.csz.dao.RoleDao.findRolesAndPermissionByUId"
            ))
    })
    public UserInfo findById(String id);


    @Insert("insert into users(email,username,password,phonenum,status) " +
            " values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);

    @Insert("insert into users_role values(#{userId},#{roleId})")
    public void addRole(@Param("userId") String userId,@Param("roleId") String roleId);

}
