package com.csz.dao;

import com.csz.domain.Member;
import com.csz.domain.Orders;
import com.csz.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrdersDao {

    @Select("select * from orders")
    @Results(id="ord",
            value={
                @Result(id = true, column = "id", property = "id"),
                @Result(column = "orderNum", property = "orderNum"),
                @Result(column = "orderTime", property = "orderTime"),
                @Result(column = "orderStatus", property = "orderStatus"),
                @Result(column = "peopleCount", property = "peopleCount"),
                @Result(column = "payType", property = "payType"),
                @Result(column = "orderDesc", property = "orderDesc"),
                @Result(column = "productId", property = "product",
                        javaType= Product.class, //返回值！
                        one=@One(
                                fetchType= FetchType.LAZY, //延迟加载
                                select = "com.csz.dao.ProductDao.findById"
                        )

                )
    })
    public List<Orders> findAll();


    @Select("select * from orders where id=#{id} ")
    @Results(id="ordAll",
            value={
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "orderNum", property = "orderNum"),
                    @Result(column = "orderTime", property = "orderTime"),
                    @Result(column = "orderStatus", property = "orderStatus"),
                    @Result(column = "peopleCount", property = "peopleCount"),
                    @Result(column = "payType", property = "payType"),
                    @Result(column = "orderDesc", property = "orderDesc"),
                    @Result(column = "productId", property = "product",
                    javaType= Product.class, //返回值！
                    one=@One(fetchType= FetchType.LAZY, //延迟加载
                            select = "com.csz.dao.ProductDao.findById")),
                    @Result(column = "memberId", property = "member", javaType= Member.class, //返回值！
                    one=@One(fetchType= FetchType.LAZY, //延迟加载
                            select = "com.csz.dao.MemberDao.findById")),
                    @Result(column = "id",property = "travellers" ,javaType = List.class
                    ,many = @Many(
                            fetchType= FetchType.LAZY,
                            select = "com.csz.dao.TravellerDao.findByOrderId")
                    )
            })
    public Orders findOneById(String id);


}
