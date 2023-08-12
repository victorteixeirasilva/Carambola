package com.carambola.service;

import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ProductService {

    public ResponseEntity insert(ProductForm productForm);

    public ResponseEntity getProduct(Long idProduct);
}
