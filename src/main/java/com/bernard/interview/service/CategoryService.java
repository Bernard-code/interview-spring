package com.bernard.interview.service;

import com.bernard.interview.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getCategories();
    CategoryDTO getCategoryById(long id);
    CategoryDTO updateCategory(long id, CategoryDTO categoryDTO);
    void deleteCategory(long id);
}
