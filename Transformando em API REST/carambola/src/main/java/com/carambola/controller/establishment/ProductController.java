package com.carambola.controller.establishment;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import com.carambola.model.form.establishment.ProductUpdateForm;
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

    @GetMapping("/category/{idCategory}")
    public ResponseEntity getProductsByCategory(@PathVariable Long idCategory) {
        try {
            return productService.getProductsByCategory(idCategory);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possível encontrar produtos nessa categoria!");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/catalog/{idCatalog}")
    public ResponseEntity getProductsByCatalog(@PathVariable Long idCatalog){
        try {
            return productService.getProductsByCatalog(idCatalog);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possível retornar todos os produtos desse catalogo!"
            );
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/establishment/{idCatalog}")
    public ResponseEntity getProductNotStock(@PathVariable Long idCatalog){
        try {
            return productService.getProductsNotStock(idCatalog);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possível retornar os produtos sem estoque, desse catalogo!"
            );
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity update(@PathVariable Long idProduct, @RequestBody ProductUpdateForm productUpdateForm) {
        try {
            return productService.update(idProduct, productUpdateForm);
        } catch (Exception e) {
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possível editar o produto!"
            );
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity delete(@PathVariable Long idProduct){
        try {
            return productService.delete(idProduct);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possível deletar esse produto!"
            );
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }









































}
