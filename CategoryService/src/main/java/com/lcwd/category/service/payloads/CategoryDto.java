package com.lcwd.category.service.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private int categoryId;
    
    
    @NotBlank
    @Size(min = 4)
    private String categoryName;
    
    @NotBlank
    @Size(min = 4)
    private String categoryBrand;
}
