package com.csz.controller;


import com.csz.domain.Permission;
import com.csz.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView model = new ModelAndView();
        List<Permission> all = permissionService.findAll();
        model.addObject("permissionList", all);
        model.setViewName("permission-list");
        return model;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect: findAll.do";
    }


}
