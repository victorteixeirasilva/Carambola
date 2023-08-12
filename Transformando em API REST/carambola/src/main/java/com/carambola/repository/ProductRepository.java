package com.carambola.repository;

import com.carambola.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByIdAndActiveTrue(Long id);
}
