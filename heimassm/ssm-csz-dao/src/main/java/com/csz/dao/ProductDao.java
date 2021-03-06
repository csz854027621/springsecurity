package com.csz.dao;

import com.csz.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ProductDao {


    @Select("select * from product where id=#{id} ")
    public Product findById(String id);

    @Select("select * from product")
    public List<Product> findAll();

    @Insert(
            "insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus )" +
                    " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus}) "
    )
    public void save(Product product);


}
