package com.carambola.repository;

import com.carambola.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Iterable<Catalog> findByUserId(Long userId);
}
