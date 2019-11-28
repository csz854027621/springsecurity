package com.csz.service;

import com.csz.domain.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public void save(Product product);
}
