package com.carambola.service;

import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import org.springframework.stereotype.Component;

@Component
public interface ProductService {

    public Product insert(ProductForm productForm);
}
