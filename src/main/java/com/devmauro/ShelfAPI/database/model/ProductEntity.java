package com.devmauro.ShelfAPI.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// Marca essa classe como uma tabela no banco de dados
@Entity
// Lombok: gera getters, setters, equals, hashCode e toString automaticamente
@Data
// Lombok: gera um construtor com todos os campos — obrigatório para o @Builder funcionar
@AllArgsConstructor
// Lombok: gera um construtor vazio — obrigatório para o JPA funcionar
@NoArgsConstructor
// Define o nome da tabela no banco como "produtos"
@Table(name = "produtos")
// Lombok: permite criar objetos com o padrão builder (ProductEntity.builder().name("x").build())
@Builder
public class ProductEntity {

    // Marca como chave primária
    @Id
    // O banco gera o ID automaticamente (autoincrement)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Não permite salvar produto sem nome
    @Column(nullable = false)
    private String name;

    // Não permite salvar produto sem preço (BigDecimal para valores monetários com precisão)
    @Column(nullable = false)
    private BigDecimal price;

    // Não permite salvar produto sem quantidade (Integer, pois quantidade é número inteiro)
    @Column(nullable = false)
    private Integer quantity;

    // Muitos produtos pertencem a uma categoria
    // @JoinColumn cria a coluna "category_id" na tabela "produtos" como chave estrangeira
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}
