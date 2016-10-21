package com.springmvc.bcart.services;

import com.springmvc.bcart.model.Basket;

import java.util.List;

public interface BasketService {

    List<Basket> getBaskets();

    Basket save(String productId, Double count);

}