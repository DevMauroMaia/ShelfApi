package com.devmauro.ShelfAPI.database.repository;

import com.devmauro.ShelfAPI.database.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByName(String name);
}
