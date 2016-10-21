package com.springmvc.bcart.services.SrvImpl;

import com.springmvc.bcart.model.Product;
import com.springmvc.bcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    MongoTemplate mongoTemplate;

    public static String PRODUCT_COLLECTION_NAME = "Product";

    @Override
    public List<Product> getProducts() {
        return mongoTemplate.findAll(Product.class, PRODUCT_COLLECTION_NAME);
    }

    @Override
    public Product save(String productId, String name, Double price, String categoryId) {
        Product product = new Product(productId, name, price, categoryId);
        mongoTemplate.save(product, PRODUCT_COLLECTION_NAME);
        return product;
    }

    @Override
    public Product getProduct(String productId) {
        return mongoTemplate.findOne(findProductQuery(productId), Product.class, PRODUCT_COLLECTION_NAME);
    }

    private Query findProductQuery(String productId) {
        return new Query(Criteria.where("productId").is(productId));
    }

    @Override
    public List<Product> findProductsWithCategoryId(String categoryId) {
        return mongoTemplate.find(findProductsWithCategoryIdQuery(categoryId), Product.class, PRODUCT_COLLECTION_NAME);
    }

    private Query findProductsWithCategoryIdQuery(String categoryId) {
        return new Query(Criteria.where("categoryId").is(categoryId));
    }

    @Override
    public void updateProduct(String productId, String updateKey, Object updateValue) {
        Update update = new Update();
        update.set(updateKey, updateValue);
        mongoTemplate.updateFirst(findProductQuery(productId), update, PRODUCT_COLLECTION_NAME);
    }

    @Override
    public void deleteProduct(String productId) {
        mongoTemplate.remove(findProductQuery(productId), PRODUCT_COLLECTION_NAME);
    }

}
