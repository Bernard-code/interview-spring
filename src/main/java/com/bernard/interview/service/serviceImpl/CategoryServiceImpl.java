package com.bernard.interview.service.serviceImpl;

import com.bernard.interview.entity.Category;
import com.bernard.interview.exception.ResourceNotFoundException;
import com.bernard.interview.payload.CategoryDTO;
import com.bernard.interview.repository.CategoryRepository;
import com.bernard.interview.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setPosition(categoryDTO.getPosition());

        Category newCategory = categoryRepository.save(category);
        return mapEntityToDTO(newCategory);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream().map(this::mapEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(long id) {
        return mapEntityToDTO(this.getRawCategoryById(id));
    }

    @Override
    public CategoryDTO updateCategory(long id, CategoryDTO categoryDTO) {
        Category category = this.getRawCategoryById(id);
        category.setName(categoryDTO.getName());
        category.setPosition(categoryDTO.getPosition());
        Category updateCategory = categoryRepository.save(category);
        return mapEntityToDTO(updateCategory);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.delete(getRawCategoryById(id));
    }

    private CategoryDTO mapEntityToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    private Category getRawCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }
}
