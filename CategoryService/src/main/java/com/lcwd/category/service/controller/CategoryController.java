package com.lcwd.category.service.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.category.service.payloads.ApiResponse;
import com.lcwd.category.service.payloads.CategoryDto;
import com.lcwd.category.service.service.CategoryServiceImpl;
@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl service;
    
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto){
	CategoryDto createCategory=	this.service.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable int catId){
	CategoryDto updatedCategory=	this.service.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse>deleteCategory(@PathVariable int catId){
	this.service.deleteCategory(catId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("category delete successfully!!",true),HttpStatus.OK);
		
	}
	//getById
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable int catId){
	CategoryDto categoryDto=this.service.getCategoryById(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
		
	}
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getCategory(){
	List<CategoryDto>categories=this.service.getCategories();
	return ResponseEntity.ok(categories);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
