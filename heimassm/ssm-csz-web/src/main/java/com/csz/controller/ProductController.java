package com.csz.controller;


import com.csz.domain.Product;
import com.csz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;



    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")  //jsr250
    public ModelAndView findAll(){
        List<Product> all = productService.findAll();
        ModelAndView model=  new ModelAndView();
        model.addObject("productList",all);
        model.setViewName("product-list");
        return  model;
    }

    @RequestMapping("/save.do")
    public String save(Product product)
    {
        productService.save(product);
        return  "forward:findAll.do";
    }



    @RequestMapping("/StringToDateD.do")
    public String Demo(Date date){
        System.out.println(date);
        return "success";
    }


}
