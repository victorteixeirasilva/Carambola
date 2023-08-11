package com.carambola.controller.establishment;

import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import com.carambola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/establishment/catalog/category/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductForm productForm){
        return ResponseEntity.ok(productService.insert(productForm));
    }


}
