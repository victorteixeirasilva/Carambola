package com.carambola.repository;

import com.carambola.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByIdAndActiveTrue(Long id);

    @Query( "SELECT p " +
            "  FROM Product p " +
            "WHERE p.category.id = :idCategory " +
            "  AND p.haveStock = true " +
            "  AND p.active = true")
    public List<Product> findByCategory(Long idCategory);

    @Query("SELECT p\n" +
            "  FROM Product p\n" +
            "   JOIN p.category c\n" +
            "   JOIN c.catalog cat\n" +
            "  WHERE (cat.id = :idCatalog OR c.id = :idCategoryAllProduct)\n" +
            "    AND p.haveStock = true\n" +
            "    AND p.active = true")
    public List<Product> findByCatalogAndAllProduct(Long idCatalog, Long idCategoryAllProduct);


    @Query("SELECT p\n" +
            "  FROM Product p\n" +
            "   JOIN p.category c\n" +
            "   JOIN c.catalog cat\n" +
            "  WHERE (cat.id = :idCatalog OR c.id = :idCategoryAllProduct)\n" +
            "    AND p.haveStock = false\n" +
            "    AND p.active = true")
    public List<Product> findByAllProductHaveStockFalse(Long idCatalog, Long idCategoryAllProduct);
}
