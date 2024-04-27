package com.lcwd.category.service.service;

import java.util.List;

import com.lcwd.category.service.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	
	//delete
	void deleteCategory(int categoryId);
	
	//get
	//CategoryDto getCategoryById(int categoryId);
	
	//getAll
	List<CategoryDto>getCategories();

	CategoryDto getCategoryById(int categoryId);
	
	CategoryDto getCategoryByName(String categoryName);
	
	//get productby category Id
	

	
	
}
