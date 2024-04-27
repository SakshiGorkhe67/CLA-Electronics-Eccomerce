package com.lcwd.category.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.lcwd.category.service.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
   public Category findBycategoryName(String categoryName);
}
