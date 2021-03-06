package com.csz.controller;

import com.csz.domain.SysLog;
import com.csz.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class LogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView model=new ModelAndView();
        List<SysLog> all = sysLogService.findALl();
        model.addObject("sysLogs",all);
        model.setViewName("syslog-list");
        return model;
    }

}
