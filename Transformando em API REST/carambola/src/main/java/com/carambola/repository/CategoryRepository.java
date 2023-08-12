package com.carambola.repository;

import com.carambola.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.catalog.id = :catalogId AND c.name = 'Todos os Produtos'")
    public Category findCategoryAllProduct(@Param("catalogId") Long catalogId);
    @Query("SELECT c FROM Category c WHERE c.catalog.id = :catalogId AND c.name != 'Todos os Produtos'")
    public List<Category> findCategoriesByCatalogId(@Param("catalogId") Long catalogId);


    public List<Category> findByCatalogId(Long idCatalog);

    public Optional<Category> findByIdAndActiveTrue(Long idCategory);



}
