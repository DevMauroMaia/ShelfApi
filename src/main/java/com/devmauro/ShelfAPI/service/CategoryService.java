package com.devmauro.ShelfAPI.service;

import com.devmauro.ShelfAPI.database.model.CategoryEntity;
import com.devmauro.ShelfAPI.database.repository.ICategoryRepository;
import com.devmauro.ShelfAPI.dto.CategoryDto;
import com.devmauro.ShelfAPI.dto.CategoryDtoResponse;
import com.devmauro.ShelfAPI.exception.ResourceAlreadyExistsException;
import com.devmauro.ShelfAPI.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ICategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDtoResponse> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> CategoryDtoResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryDtoResponse findByName(String categoryName) {
        CategoryEntity categoria = categoryRepository.findByName(categoryName);
        if (categoria == null) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        return CategoryDtoResponse.builder()
                .id(categoria.getId())
                .name(categoria.getName())
                .build();
    }

    @Transactional(readOnly = true)
    public CategoryDtoResponse findById(Integer id) {
        CategoryEntity categoria = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        return CategoryDtoResponse.builder()
                .id(categoria.getId())
                .name(categoria.getName())
                .build();
    }

    @Transactional
    public void save(CategoryDto categoryDto) {
        CategoryEntity categoryValidation = categoryRepository.findByName(categoryDto.getName());
        if (categoryValidation != null) {
            throw new ResourceAlreadyExistsException("Essa categoria já existente");
        }
        categoryRepository.save(CategoryEntity.builder()
                .name(categoryDto.getName())
                .build());
    }

    @Transactional
    public void delete(Integer id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        categoryRepository.delete(category);
    }

    @Transactional
    public void update(Integer id, CategoryDto categoryDto) {
        categoryRepository.findById(id).ifPresent(categoryEntity -> {
            CategoryEntity categoria = categoryRepository.findByName(categoryDto.getName());
            if (categoria == null) {
                categoryEntity.setName(categoryDto.getName());
                categoryRepository.save(categoryEntity);
            } else {
                throw new ResourceAlreadyExistsException("Esse nome já existe");
            }
        });
    }

}


