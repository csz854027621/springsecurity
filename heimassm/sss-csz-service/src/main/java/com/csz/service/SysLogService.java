package com.csz.service;

import com.csz.domain.SysLog;


import java.util.List;

public interface SysLogService {

   public void save(SysLog sysLog);

   public List<SysLog> findALl();

}
