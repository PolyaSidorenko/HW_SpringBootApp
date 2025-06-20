package org.example.dao;

import org.example.domain.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DAO для работы с сущностью Product
 * Предоставляет методы для создания, получения, обновления и удаления товаров с использованием Hibernate
 */
@Repository
public class ProductDAO {

    private final ProductRepository productRepository;

    public ProductDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Создаёт новый продукт в базе данных
     * Если товар с таким именем уже существует, его количество увеличивается на 1
     * В противном случае создаётся новый товар с количеством 1
     */
    public void create(Product product) {
        Optional<Product> existing = productRepository.findByName(product.getName());
        if (existing.isPresent()) {
            Product existingProduct = existing.get();
            existingProduct.setQuantity(existingProduct.getQuantity() + 1);
            productRepository.save(existingProduct);
        } else {
            product.setQuantity(1);
            productRepository.save(product);
        }
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void update(Product product) {
        productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public Product getById(long id) {
        return productRepository.findById(id).orElse(null);
    }
}
