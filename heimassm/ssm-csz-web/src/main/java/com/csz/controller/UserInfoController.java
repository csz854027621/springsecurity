package com.csz.controller;


import com.csz.domain.Role;
import com.csz.domain.UserInfo;
import com.csz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView model=new ModelAndView();
        List<UserInfo> all = userService.findAll();
        model.addObject("userList",all);
        model.setViewName("user-list");
        return model;
    }

    @RequestMapping("/findById.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  //spel表达式 判断是否有ADMIN权限  ，测试无效
    public ModelAndView findById(String id){
        ModelAndView  model=new ModelAndView();
        UserInfo byId = userService.findById(id);
        model.addObject("user",byId);
        model.setViewName("user-show");
        return model;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='csz'")
    public String save(UserInfo userInfo){
        ModelAndView  model=new ModelAndView();
        userService.save(userInfo);

        return "redirect:findAll.do";
    }


    @RequestMapping("/addRole.do")  //添加角色
    public String save(String userId, @RequestParam(name="ids",required = true) String[] roleIds){
        ModelAndView  model=new ModelAndView();
        userService.addRoles(userId,roleIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("/addRoleOne.do")  //添加角色
    public String saveOne(String userId, String roleId){
        ModelAndView  model=new ModelAndView();
        userService.addRole(userId,roleId);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findNotRoleByUid.do")  //查询用户缺少哪些些角色权限
    public ModelAndView findNotUserRoleById(String id){
        ModelAndView  model=new ModelAndView();
        List<Role> notUserRoleById = userService.findNotUserRoleById(id);
        model.addObject("roleList",notUserRoleById);
        model.addObject("userId",id);
        model.setViewName("user-add-role");
        return model;
    }




}
