package com.devmauro.ShelfAPI.service;

import com.devmauro.ShelfAPI.database.model.CategoryEntity;
import com.devmauro.ShelfAPI.database.model.ProductEntity;
import com.devmauro.ShelfAPI.database.repository.ICategoryRepository;
import com.devmauro.ShelfAPI.database.repository.IProductRepository;
import com.devmauro.ShelfAPI.dto.ProductDto;
import com.devmauro.ShelfAPI.dto.ProductDtoResponse;
import com.devmauro.ShelfAPI.exception.ResourceAlreadyExistsException;
import com.devmauro.ShelfAPI.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public List<ProductDtoResponse> findAll() {
        return productRepository.findAll().stream()
                .map(produto -> ProductDtoResponse.builder()
                        .id(produto.getId())
                        .name(produto.getName())
                        .price(produto.getPrice())
                        .quantity(produto.getQuantity())
                        .category(produto.getCategory() != null ? produto.getCategory().getName() : null)
                        .build())
                .toList();



    }


    @Transactional(readOnly = true)
    public ProductDtoResponse findById(Integer id) {
        ProductEntity produto = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Esse produto não existe"));

        return ProductDtoResponse.builder()
                .id((produto.getId()))
                .name(produto.getName())
                .price(produto.getPrice())
                .quantity(produto.getQuantity())
                .category(produto.getCategory() != null ? produto.getCategory().getName() : null)
                .build();
    }

    @Transactional
    public void saveProduct(ProductDto productDto) {
        ProductEntity produto = productRepository.findByName(productDto.getName());
        if (produto != null) {
            throw new ResourceAlreadyExistsException("Produto já existe");
        }

        CategoryEntity category = categoryRepository.findByName(productDto.getCategory());
        if (category == null) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        productRepository.save(ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .category(category)
                .build());
    }

    @Transactional
    public void updateProduct(Integer id, ProductDto productDto) {
        ProductEntity produto = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado")
        );

        CategoryEntity category = categoryRepository.findByName(productDto.getCategory());
        if (category == null) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        produto.setName(productDto.getName());
        produto.setPrice(productDto.getPrice());
        produto.setQuantity(productDto.getQuantity());
        produto.setCategory(category);
        productRepository.save(produto);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        ProductEntity produto = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        productRepository.delete(produto);

    }



}
