package com.carambola.repository;

import com.carambola.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    @Query("SELECT c FROM Catalog c WHERE c.user.userId = :userId")
    List<Catalog> findCatlogsByIdEstablishment(Long userId);
}
