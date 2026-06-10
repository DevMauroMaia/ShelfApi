package com.devmauro.ShelfAPI.database.repository;

import com.devmauro.ShelfAPI.database.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {

    ProductEntity findByName(String name);
}
