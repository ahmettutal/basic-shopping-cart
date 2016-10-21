package com.springmvc.bcart.services.SrvImpl;

import com.springmvc.bcart.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.springmvc.bcart.model.Category;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    MongoTemplate mongoTemplate;
	
	public static String CATEGORY_COLLECTION_NAME = "Category";

	@Override
	public List<Category> getCategories() {
		return mongoTemplate.findAll(Category.class, CATEGORY_COLLECTION_NAME);
	}

	@Override
	public Category save(String categoryId, String name) {
		Category category = new Category(categoryId, name);
		mongoTemplate.save(category, CATEGORY_COLLECTION_NAME);
		return category;
	}

	@Override
	public Category getCategory(String categoryId) {
		return mongoTemplate.findOne(findCategoryQuery(categoryId), Category.class, CATEGORY_COLLECTION_NAME);
	}
	
	private Query findCategoryQuery(String categoryId) {
		return new Query(Criteria.where("categoryId").is(categoryId));
	}

	@Override
	public void updateCategory(String categoryId, String updateKey, Object updateValue) {
		Update update = new Update();
		update.set(updateKey, updateValue);
		mongoTemplate.updateFirst(findCategoryQuery(categoryId), update, CATEGORY_COLLECTION_NAME);
	}

	@Override
	public void deleteCategory(String categoryId) {
		mongoTemplate.remove(findCategoryQuery(categoryId), CATEGORY_COLLECTION_NAME);
	}

	@Override
	public void deleteAll() {
		mongoTemplate.remove(new Query(), CATEGORY_COLLECTION_NAME);
	}

}
