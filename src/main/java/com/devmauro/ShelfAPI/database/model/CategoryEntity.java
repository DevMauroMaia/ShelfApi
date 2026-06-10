package com.devmauro.ShelfAPI.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Marca essa classe como uma tabela no banco de dados
@Entity
// Lombok: gera getters, setters, equals, hashCode e toString automaticamente
@Data
// Lombok: gera um construtor vazio — obrigatório para o JPA funcionar
@NoArgsConstructor
// Lombok: gera um construtor com todos os campos — obrigatório para o @Builder funcionar
@AllArgsConstructor
// Lombok: permite criar objetos com o padrão builder (CategoryEntity.builder().name("x").build())
@Builder
public class CategoryEntity {

    // Marca como chave primária
    @Id
    // O banco gera o ID automaticamente (auto increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Não permite salvar categoria sem nome
    @Column(nullable = false)
    private String name;

    // Uma categoria tem muitos produtos
    // mappedBy = "category" → o dono do relacionamento é o campo "category" no ProductEntity
    // cascade = ALL → salvar/deletar categoria afeta os produtos automaticamente
    // orphanRemoval = true → produto removido da lista é deletado do banco
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products;

}
