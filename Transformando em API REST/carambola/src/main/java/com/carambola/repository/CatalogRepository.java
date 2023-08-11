package com.carambola.repository;

import com.carambola.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Iterable<Catalog> findByUserIdAndActiveTrue(Long userId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Category c WHERE c.catalog.id = :catalogId AND c.name != 'Todos os Produtos'")
    boolean hasCategories(Long catalogId);

    public Optional<Catalog> findByIdAndActiveTrue(Long catalogId);
}
