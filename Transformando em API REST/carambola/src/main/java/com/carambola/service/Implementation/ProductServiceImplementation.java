package com.carambola.service.Implementation;

import com.carambola.model.Category;
import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import com.carambola.repository.CategoryRepository;
import com.carambola.repository.ProductRepository;
import com.carambola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product insert(ProductForm productForm) {
        Product product = new Product(productForm);

        Optional<Category> category = categoryRepository.findById(product.getCategory().getId());
        if(category.isPresent()){
            product.setCategory(category.get());
            productRepository.save(product);
            return product;
        } else {
            product.setCategory(categoryRepository.findCategoriesByCatalogIdAndName(category.get().getCatalog().getId()));
            productRepository.save(product);
            return product;
        }
    }
}
