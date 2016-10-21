package com.springmvc.bcart.services;

import com.springmvc.bcart.model.Category;

import java.util.List;

public interface CategoryService {

	List<Category> getCategories();

	Category save(String categoryId, String name);
	
	Category getCategory(String categoryId);
	
	void updateCategory(String categoryId, String updateKey, Object updateValue);
	
	void deleteCategory(String categoryId);
}