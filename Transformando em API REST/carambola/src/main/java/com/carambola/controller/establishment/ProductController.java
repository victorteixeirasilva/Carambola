package com.carambola.controller.establishment;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import com.carambola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/establishment/catalog/category/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity insert(@RequestBody ProductForm productForm){
        try {
            return productService.insert(productForm);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(
                500,
                "Não foi possível inserir o produto!"
            );
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity getProduct(@PathVariable Long idProduct){
        try {
            return productService.getProduct(idProduct);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possível encontrar o produto!");
            return new ResponseEntity(responseModel , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}
