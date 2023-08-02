package com.carambola.repository;

import com.carambola.model.Catalog;
import com.carambola.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    public Iterable<Category> findByCatalogId(Long idCatalog);

}
