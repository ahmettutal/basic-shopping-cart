package com.springmvc.bcart.services.SrvImpl;

import com.springmvc.bcart.model.Basket;
import com.springmvc.bcart.services.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("basket")
public class BasketServiceImpl implements Strategy {

    @Autowired
    MongoTemplate mongoTemplate;

    public static String BASKET_COLLECTION_NAME = "Basket";

    @Override
    public List<Object> findAll() {
        return mongoTemplate.findAll(Object.class, BASKET_COLLECTION_NAME);
    }

    @Override
    public Object save(Object object) {
        Basket basket = (Basket) object;
        mongoTemplate.save(basket, BASKET_COLLECTION_NAME);
        return basket;
    }

    @Override
    public void deleteByID(String Id) {
        mongoTemplate.remove(findBasketQuery(Id), BASKET_COLLECTION_NAME);
    }

    @Override
    public Object findById(String Id) {
        return null;
    }

    @Override
    public List<Object> findListById(String Id) {
        return null;
    }

    private Query findBasketQuery(String productId) {
        return new Query(Criteria.where("productId").is(productId));
    }

}
