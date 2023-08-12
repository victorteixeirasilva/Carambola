package com.carambola.service;

import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import com.carambola.model.form.establishment.ProductUpdateForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ProductService {

    public ResponseEntity insert(ProductForm productForm);

    public ResponseEntity getProduct(Long idProduct);

    public ResponseEntity getProductsByCategory(Long idCategory);

    public ResponseEntity getProductsByCatalog(Long idCatalog);

    public ResponseEntity getProductsNotStock(Long idCatalog);

    public ResponseEntity update(Long idProduct, ProductUpdateForm productUpdateForm);

    public ResponseEntity delete(Long idProduct);
}
