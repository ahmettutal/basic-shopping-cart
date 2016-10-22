package com.springmvc.bcart.services.SrvImpl;

import com.springmvc.bcart.model.Category;
import com.springmvc.bcart.services.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("category")
public class CategoryServiceImpl implements Strategy {

    @Autowired
    MongoTemplate mongoTemplate;

    public static String CATEGORY_COLLECTION_NAME = "Category";

    @Override
    public List<Object> findAll() {
        return mongoTemplate.findAll(Object.class, CATEGORY_COLLECTION_NAME);
    }

    @Override
    public Object save(Object object) {
        Category category = (Category) object;
        mongoTemplate.save(category, CATEGORY_COLLECTION_NAME);
        return category;
    }

    @Override
    public Object findById(String Id) {
        return mongoTemplate.findOne(findCategoryQuery(Id), Object.class, CATEGORY_COLLECTION_NAME);
    }

    private Query findCategoryQuery(String categoryId) {
        return new Query(Criteria.where("categoryId").is(categoryId));
    }

    @Override
    public List<Object> findListById(String Id) {
        return null;
    }

    @Override
    public void deleteByID(String Id) {
    }

}
