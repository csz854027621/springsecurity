package com.csz.controller;


import com.csz.domain.Permission;
import com.csz.domain.Role;
import com.csz.service.PermissionService;
import com.csz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView model = new ModelAndView();
        List<Role> all = roleService.findAll();
        model.addObject("roleList", all);
        model.setViewName("role-list");
        return model;
    }


    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect: findAll.do";
    }

    @RequestMapping("/findRoleByRid.do")
    public ModelAndView findRoleByRid(String id, String roleName) throws Exception {
        ModelAndView model = new ModelAndView();
        Role role = roleService.findRoleByRid(id);
        model.addObject("roles", role);
        model.addObject("roleName", roleName);
        model.setViewName("role-show");
        return model;
    }

    @RequestMapping("/addPermission.do")  //添加角色
    public String addPermission(String roleId, @RequestParam(name="ids",required = true) String[] permissionIds){
        ModelAndView  model=new ModelAndView();
        roleService.addPermissions(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("/addPermissionOne.do")  //添加角色
    public String addPermissionOne(String roleId, String permissionId){
        ModelAndView  model=new ModelAndView();
        roleService.addPermission(roleId,permissionId);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findNotPermissionesByRId.do")  //查询用户缺少哪些些角色权限
    public ModelAndView findNotPermissionesByRId(String id){
        ModelAndView  model=new ModelAndView();
        List<Permission> listP = permissionService.findNotPermissionesByRId(id);
        model.addObject("permissionList",listP);
        model.addObject("roleId",id);
        model.setViewName("role-add-permission");
        return model;
    }


}
