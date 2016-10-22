package com.springmvc.bcart.services.SrvImpl;

import com.springmvc.bcart.model.Product;
import com.springmvc.bcart.services.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("product")
public class ProductServiceImpl implements Strategy {


    @Autowired
    MongoTemplate mongoTemplate;

    public static String PRODUCT_COLLECTION_NAME = "Product";

    @Override
    public List<Object> findAll() {
        return mongoTemplate.findAll(Object.class, PRODUCT_COLLECTION_NAME);
    }

    @Override
    public Object save(Object object) {
        Product product = (Product) object;
        mongoTemplate.save(product, PRODUCT_COLLECTION_NAME);
        return product;
    }

    @Override
    public void deleteByID(String id) {
    }

    @Override
    public Object findById(String Id) {
        return mongoTemplate.findOne(findProductQuery(Id), Object.class, PRODUCT_COLLECTION_NAME);
    }

    @Override
    public List<Object> findListById(String Id) {
        return mongoTemplate.find(findProductsWithCategoryIdQuery(Id), Object.class, PRODUCT_COLLECTION_NAME);
    }
    private Query findProductQuery(String productId) {
        return new Query(Criteria.where("productId").is(productId));
    }

    private Query findProductsWithCategoryIdQuery(String categoryId) {
        return new Query(Criteria.where("categoryId").is(categoryId));
    }

}
