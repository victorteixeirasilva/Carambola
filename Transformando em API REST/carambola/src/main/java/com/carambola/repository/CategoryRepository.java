package com.carambola.repository;

import com.carambola.model.Catalog;
import com.carambola.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.catalog.id = :catalogId AND c.name = 'Todos os Produtos'")
    public Category findCategoriesByCatalogIdAndName(@Param("catalogId") Long catalogId);


    public Iterable<Category> findByCatalogId(Long idCatalog);


}
