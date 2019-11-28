package com.csz.service;

import com.csz.dao.OrdersDao;
import com.csz.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrdersService {

   public List<Orders> findAll();
   public List<Orders> findByPage(Integer curPage,Integer pageSize);
   public Orders findOneById(String id);
}
