package com.csz.service.impl;

import com.csz.dao.OrdersDao;
import com.csz.domain.Orders;
import com.csz.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl  implements OrdersService {

    @Autowired
    private OrdersDao od;

    @Override
    public List<Orders> findAll() {

        return od.findAll();
    }

    @Override
    public List<Orders> findByPage(Integer curPage,Integer pageSize) {
        PageHelper.startPage(curPage,pageSize);
        return od.findAll();
    }

    @Override
    public Orders findOneById(String id){

        Orders oneById = od.findOneById(id);
        return oneById;
    }
}
