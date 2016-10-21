package com.springmvc.bcart.services;

import com.springmvc.bcart.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product save(String ProductId, String name, Double price, String categoryId);

    Product getProduct(String ProductId);

    List<Product> findProductsWithCategoryId(String categoryId);

    void updateProduct(String ProductId, String updateKey, Object updateValue);

    void deleteProduct(String ProductId);

}