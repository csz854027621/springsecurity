package com.csz.dao;

import com.csz.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SysLogDao {




    @Insert( "insert into sysLog(Visittime,Username,Ip,Url,Executiontime,Method) "+
            "values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog);

    @Select("select * from syslog ")
    public List<SysLog> findALl();

}
