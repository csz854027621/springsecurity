package com.csz.service.impl;

import com.csz.dao.SysLogDao;
import com.csz.domain.SysLog;
import com.csz.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service()
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findALl() {
        return sysLogDao.findALl();
    }
}
