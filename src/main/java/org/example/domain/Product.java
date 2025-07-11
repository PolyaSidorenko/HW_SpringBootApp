package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "products")
@Data
public class Product {

    /**
     * Уникальный идентификатор продукта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название продукта
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Цена продукта
     */
    @Column(nullable = false)
    private Double price;

    /**
     * Количество продуктов на складе
     */
    @Column(nullable = false)
    private Integer quantity;

}
