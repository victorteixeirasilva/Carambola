package com.carambola.service.Implementation;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Category;
import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import com.carambola.repository.CategoryRepository;
import com.carambola.repository.ProductRepository;
import com.carambola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity insert(ProductForm productForm) {
        Product product = new Product(productForm);

        Optional<Category> category = categoryRepository.findById(product.getCategory().getId());
        if (productForm.getIdCategory()!=0){
            if(category.isPresent()){
                product.setCategory(category.get());
                productRepository.save(product);
                return ResponseEntity.ok(product);
            } else {
                ResponseModel responseModel = new ResponseModel(
                        404,
                        "Não foi possível cadastrar o produto, pois a categoria informada não existe."
                );
                return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
            }
        } else {
            product.setCategory(categoryRepository.findCategoryAllProduct(category.get().getCatalog().getId()));
            productRepository.save(product);
            return ResponseEntity.ok(product);
        }
    }

    @Override
    public ResponseEntity getProduct(Long idProduct) {
        Optional<Product> product = productRepository.findByIdAndActiveTrue(idProduct);
        if(product.isPresent()){
            return ResponseEntity.ok(product);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Produto (id:" + idProduct + "), não foi encontrado."
                    );
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }
}
