package com.lcwd.category.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.lcwd.category.service.Exception.ResourceNotFoundException;
import com.lcwd.category.service.model.Category;
import com.lcwd.category.service.payloads.CategoryDto;
import com.lcwd.category.service.repository.CategoryRepository;

@Service
@CrossOrigin(origins="http://localhost:3000")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;
    
    private Logger logger=LoggerFactory.getLogger(CategoryServiceImpl.class);
    
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = modelMapper.map(categoryDto, Category.class);
        Category addedCat = categoryRepository.save(cat);
        return modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category cat = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        cat.setCategoryName(categoryDto.getCategoryName());
        //cat.setCategoryBrand(categoryDto.getCategoryBrand());
        Category updatedCat = categoryRepository.save(cat);
        return modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(int  categoryId) {
        Category cat = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        categoryRepository.delete(cat);
    }

   @Override
    public CategoryDto getCategoryById(int categoryId) {
        Category cat = categoryRepository.findById(categoryId)
             .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
    //get product of the above category
    ArrayList forObject=    restTemplate.getForObject("http://localhost:8080/category/"+cat.getCategoryId(), ArrayList.class);
        logger.info("{}",forObject);
        return modelMapper.map(cat, CategoryDto.class);
    }

   
   //**************************Get Category by category name **********************************

	@Override
	public CategoryDto getCategoryByName(String categoryName) {
		// TODO Auto-generated method stub
		Category cat=categoryRepository.findBycategoryName(categoryName);
		//get product of the above category
		
		 ArrayList forObject=    restTemplate.getForObject("http://localhost:8080/category/"+cat.getCategoryName(), ArrayList.class);
	        logger.info("{}",forObject);
	        return modelMapper.map(cat, CategoryDto.class);
	}

	
       
   
   
    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
       
        return categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
    }

}
