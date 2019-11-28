package com.csz.controller;

import com.csz.domain.Orders;
import com.csz.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService os;


    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(){
        ModelAndView model=new ModelAndView();
        List<Orders> all = os.findAll();
        model.addObject("list",all);
        model.setViewName("orders-list");
        return model;
    }

    @RequestMapping("/findByPage.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findByPage(@RequestParam(name="curPage" ,required=true ,defaultValue="1") Integer curPage,
                                   @RequestParam(name="pageSize" ,required=true ,defaultValue="4") Integer pageSize){
        ModelAndView model=new ModelAndView();
        List<Orders> all = os.findByPage(curPage,pageSize);
        PageInfo<Orders> info=new PageInfo<>(all);
        model.addObject("lst",info);
        model.setViewName("orders-list");
        return model;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findOneById(String id){

        ModelAndView model=new ModelAndView();
        Orders oneById = os.findOneById(id);
        System.out.println(oneById);
        model.addObject("orders",oneById);
        model.setViewName("orders-show");

        return model;
    }

}
