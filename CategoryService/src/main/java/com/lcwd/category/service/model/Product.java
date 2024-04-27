package com.lcwd.category.service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Product {
	private String id;
    private String productName;
    private String description;
    private double price;
    private String status;
    private String imageFilename;
    private int categoryId;
    private String categoryName;
}
